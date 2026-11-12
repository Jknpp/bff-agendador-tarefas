package com.jknpp.bffagendadortarefas.infrastructure.client;


import com.jknpp.bffagendadortarefas.business.dto.Request.EnderecoDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.LoginDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.TelefoneDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.UsuarioDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.EnderecoDtoResponse;
import com.jknpp.bffagendadortarefas.business.dto.Response.TelefoneDtoResponse;
import com.jknpp.bffagendadortarefas.business.dto.Response.UsuarioDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDtoResponse buscarUsuario(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDtoResponse salvaUsuario(@RequestBody UsuarioDtoRequest usuarioDto);

    @PostMapping("/login")
    String login(@RequestBody LoginDtoRequest dto);

    @PostMapping("/endereco")
    EnderecoDtoResponse adicionarEndereco(@RequestHeader("Authorization")String token,
                                          @RequestBody EnderecoDtoRequest dto);

    @PostMapping("/telefone")
    TelefoneDtoResponse adicionarTelefone(@RequestHeader("Authorization")String token,
                                          @RequestBody TelefoneDtoRequest dto);

    @PutMapping
    UsuarioDtoResponse atualizaDadosUsuario(@RequestHeader("Authorization") String token,
                                            @RequestBody UsuarioDtoRequest dto);

    @PutMapping("/endereco")
    EnderecoDtoResponse atualizaEnderecoUsuario(@RequestParam("id") Long id, @RequestBody EnderecoDtoRequest dto,
                                                @RequestHeader("Authorization")String token);

    @PutMapping("/telefone")
    TelefoneDtoResponse atualizaTelefoneUsuario(@RequestParam("id") Long id, @RequestBody TelefoneDtoRequest dto,
                                                @RequestHeader("Authorization")String token);

    @DeleteMapping("/{email}")
    void deletarUsuario(@PathVariable String email,
                        @RequestHeader("Authorization")String token);

}
