package com.jknpp.bffagendadortarefas.business.dto.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDtoResponse {

    private Long id;
    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
