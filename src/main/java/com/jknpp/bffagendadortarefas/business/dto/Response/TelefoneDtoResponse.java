package com.jknpp.bffagendadortarefas.business.dto.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneDtoResponse {

    private Long id;
    private String numero;
    private String ddd;
}
