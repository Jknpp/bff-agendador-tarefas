package com.jknpp.bffagendadortarefas.business;


import com.jknpp.bffagendadortarefas.business.dto.Response.TarefaDtoResponse;
import com.jknpp.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefaDtoResponse tarefaDtoResponse){
        emailClient.enviaEmail(tarefaDtoResponse);
    }
}
