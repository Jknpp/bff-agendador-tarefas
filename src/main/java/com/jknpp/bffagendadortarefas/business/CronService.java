package com.jknpp.bffagendadortarefas.business;

import com.jknpp.bffagendadortarefas.business.dto.Request.LoginDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.TarefaDtoResponse;
import com.jknpp.bffagendadortarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;


    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasProximaHora(){
        log.info("Iniciada a busca de Tarefas");
        String token = login(converterParaRequestDto());

        LocalDateTime agora  = LocalDateTime.now();
        LocalDateTime inicio = agora.minusMinutes(5);   // opcional: cobre tarefa criada entre 2 execuções
        LocalDateTime limite = agora.plusHours(1);


        List<TarefaDtoResponse> listaDeTarefas = tarefaService.buscarTarefasPorPeriodo(inicio, limite, token);
        log.info("Tarefas encontradas: {}", listaDeTarefas.size());

        for (TarefaDtoResponse tarefa : listaDeTarefas) {
            try {
                emailService.enviaEmail(tarefa);
                tarefaService.modificarStatusTarefa(tarefa.getId(), Status.NOTIFICADO, token);
            } catch (Exception e) {
                log.error("Falha ao notificar tarefa {}", tarefa.getId(), e);
            }
        }
        log.info("Finalizada a busca e notificação de tarefas");

    }

    public String login(LoginDtoRequest dto){
        return usuarioService.login(dto);
    }

    public LoginDtoRequest converterParaRequestDto(){
        return LoginDtoRequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
