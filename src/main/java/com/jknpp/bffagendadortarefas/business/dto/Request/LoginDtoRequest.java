package com.jknpp.bffagendadortarefas.business.dto.Request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDtoRequest {
    private String email;
    private String senha;
}
