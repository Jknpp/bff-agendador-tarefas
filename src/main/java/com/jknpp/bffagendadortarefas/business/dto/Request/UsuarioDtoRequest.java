package com.jknpp.bffagendadortarefas.business.dto.Request;

import com.jknpp.bffagendadortarefas.business.dto.Response.EnderecoDtoResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDtoRequest {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDtoResponse> enderecos;
    private List<TelefoneDtoRequest> telefones;
}