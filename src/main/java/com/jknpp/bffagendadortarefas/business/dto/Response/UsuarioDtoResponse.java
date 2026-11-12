package com.jknpp.bffagendadortarefas.business.dto.Response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDtoResponse {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDtoResponse> enderecos;
    private List<TelefoneDtoResponse> telefones;
}