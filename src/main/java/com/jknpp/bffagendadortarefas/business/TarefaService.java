package com.jknpp.bffagendadortarefas.business;

import com.jknpp.bffagendadortarefas.business.dto.Request.TarefaDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.TarefaDtoResponse;
import com.jknpp.bffagendadortarefas.infrastructure.client.TarefaClient;
import com.jknpp.bffagendadortarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaClient tarefaClient;

    public TarefaDtoResponse cadastrarTarefa(String token, TarefaDtoRequest dto){
        return tarefaClient.cadastrarTarefa(token, dto);
    }

    public List<TarefaDtoResponse> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return tarefaClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefaDtoResponse> buscarTarefaEmail(String token){
        return tarefaClient.buscarTarefaPorEmail(token);
    }

    public TarefaDtoResponse modificarTarefa(String id, TarefaDtoRequest dto, String token){
        return tarefaClient.modificarTarefa(id, dto, token);
    }

    public TarefaDtoResponse modificarStatusTarefa(String id, Status status, String token){
        return tarefaClient.modificarStatusTarefa(id, status, token);
    }

    public void deletarTarefa(String id, String token){
        tarefaClient.deletarTarefa(id, token);
    }
}
