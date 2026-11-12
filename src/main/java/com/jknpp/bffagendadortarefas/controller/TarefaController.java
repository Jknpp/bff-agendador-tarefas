package com.jknpp.bffagendadortarefas.controller;


import com.jknpp.bffagendadortarefas.business.TarefaService;
import com.jknpp.bffagendadortarefas.business.dto.Request.TarefaDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.TarefaDtoResponse;
import com.jknpp.bffagendadortarefas.infrastructure.enums.Status;
import com.jknpp.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Cadastra Tarefa", description = "Cria uma nova Tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Tarefa já cadastrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDtoResponse> cadastrarTarefa(@RequestHeader(name = "Authorization", required = false) String token,
                                                             @RequestBody TarefaDtoRequest dto){
    return ResponseEntity.ok(tarefaService.cadastrarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Lista de Tarefas Por Periodo", description = "Lista tarefas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDtoResponse>> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                                                 @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscarTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Lista de Tarefas Por Email", description = "Lista tarefas por email")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaDtoResponse>> buscarTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscarTarefaEmail(token));
    }

    @PutMapping
    @Operation(summary = "Modifica Tarefa", description = "Modifica tarefa por id")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDtoResponse> modificarTarefa(@RequestParam("id") String id, @RequestBody TarefaDtoRequest dto,
                                                             @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.modificarTarefa(id, dto, token));
    }

    @PatchMapping
    @Operation(summary = "Modifica Status", description = "Modifica Status da Tarefa")
    @ApiResponse(responseCode = "200", description = "Status alterado")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDtoResponse> modifcarStatusTarefa(@RequestParam("id") String id, @RequestParam("status") Status status,
                                                                  @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.modificarStatusTarefa(id, status, token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Tarefa", description = "Deleta tarefa")
    @ApiResponse(responseCode = "200", description = "Status alterado")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefa(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.deletarTarefa(id, token);
        return ResponseEntity.ok().build();
    }
}
