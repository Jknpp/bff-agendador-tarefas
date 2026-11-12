package com.jknpp.bffagendadortarefas.controller;


import com.jknpp.bffagendadortarefas.business.UsuarioService;
import com.jknpp.bffagendadortarefas.business.dto.Request.EnderecoDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.LoginDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.TelefoneDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Request.UsuarioDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.EnderecoDtoResponse;
import com.jknpp.bffagendadortarefas.business.dto.Response.TelefoneDtoResponse;
import com.jknpp.bffagendadortarefas.business.dto.Response.UsuarioDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDtoResponse> salvaUsuario(@RequestBody UsuarioDtoRequest usuarioDto){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDto));
    }


    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginDtoRequest dto){
        return usuarioService.login(dto);
    }

    @PostMapping("/endereco")
    @Operation(summary = "Adiciona Endereço", description = "Adicionar endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Endereço já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDtoResponse> adicionarEndereco(@RequestHeader("Authorization")String token,
                                                                 @RequestBody EnderecoDtoRequest dto){
        return ResponseEntity.ok(usuarioService.adicionarEnderecoUsuario(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Adiciona Telefone", description = "Adicionar telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Telefone já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDtoResponse> adicionarTelefone(@RequestHeader("Authorization")String token,
                                                                 @RequestBody TelefoneDtoRequest dto){
        return ResponseEntity.ok(usuarioService.adicionarTelefoneUsuario(token, dto));
    }

    @GetMapping
    @Operation(summary = "Busca Usuario", description = "Busca usuário por email")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDtoResponse> buscarUsuario(@RequestParam("email") String email, @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.buscarUsuario(email, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza Usuario", description = "Atualiza dados usuário")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDtoResponse> atualizaDadosUsuario(@RequestHeader("Authorization") String token,
                                                                   @RequestBody UsuarioDtoRequest dto){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDtoResponse> atualizaEnderecoUsuario(@RequestParam("id") Long id, @RequestBody EnderecoDtoRequest dto,
                                                                       @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizarEnderecoUsuario(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDtoResponse> atualizaTelefoneUsuario(@RequestParam("id") Long id, @RequestBody TelefoneDtoRequest dto,
                                                                       @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefoneUsuario(id, dto, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuário", description = "Deleta usuário por email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public void deletarUsuario(@PathVariable String email, @RequestHeader("Authorization")String token){
        usuarioService.deletarUsuario(email, token);
    }

}
