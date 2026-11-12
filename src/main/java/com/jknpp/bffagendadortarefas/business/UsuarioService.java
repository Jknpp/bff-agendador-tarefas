package com.jknpp.bffagendadortarefas.business;

import com.jknpp.bffagendadortarefas.business.dto.Request.EnderecoDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.LoginDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.TelefoneDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.UsuarioDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.EnderecoDtoResponse;
import com.jknpp.bffagendadortarefas.business.dto.Response.TelefoneDtoResponse;
import com.jknpp.bffagendadortarefas.business.dto.Response.UsuarioDtoResponse;
import com.jknpp.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDtoResponse salvarUsuario(UsuarioDtoRequest usuarioDto){
        return client.salvaUsuario(usuarioDto);
    }

    public String login(LoginDtoRequest usuarioDto){
        return client.login(usuarioDto);
    }

    public UsuarioDtoResponse buscarUsuario(String email, String token){
        return client.buscarUsuario(email, token);
    }

    public UsuarioDtoResponse atualizaDadosUsuario(String token, UsuarioDtoRequest dto) {
        return client.atualizaDadosUsuario(token, dto);
    }

    public EnderecoDtoResponse atualizarEnderecoUsuario(Long id, EnderecoDtoRequest dto, String token){
        return client.atualizaEnderecoUsuario(id, dto, token);
    }

    public TelefoneDtoResponse atualizarTelefoneUsuario(Long id, TelefoneDtoRequest dto, String token){
        return client.atualizaTelefoneUsuario(id, dto, token);
    }

    public EnderecoDtoResponse adicionarEnderecoUsuario(String token, EnderecoDtoRequest dto){
        return client.adicionarEndereco(token, dto);
    }

    public TelefoneDtoResponse adicionarTelefoneUsuario(String token, TelefoneDtoRequest dto){
        return client.adicionarTelefone(token, dto);
    }

    public void deletarUsuario(String email, String token){
        client.deletarUsuario(email, token);
    }

}

