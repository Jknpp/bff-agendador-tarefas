package com.jknpp.bffagendadortarefas.business.dto.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneDtoRequest {

    private String numero;
    private String ddd;
}
