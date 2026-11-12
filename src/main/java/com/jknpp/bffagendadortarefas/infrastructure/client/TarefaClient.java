package com.jknpp.bffagendadortarefas.infrastructure.client;


import com.jknpp.bffagendadortarefas.business.dto.Request.TarefaDtoRequest;
import com.jknpp.bffagendadortarefas.business.dto.Response.TarefaDtoResponse;
import com.jknpp.bffagendadortarefas.infrastructure.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefa", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDtoResponse cadastrarTarefa(@RequestHeader("Authorization") String token,
                                      @RequestBody TarefaDtoRequest dto);

    @GetMapping("/eventos")
    List<TarefaDtoResponse> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                          @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaDtoResponse> buscarTarefaPorEmail(@RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDtoResponse modificarTarefa(@RequestParam("id") String id, @RequestBody TarefaDtoRequest dto,
                                      @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDtoResponse modificarStatusTarefa(@RequestParam("id") String id, @RequestParam("status") Status status,
                                            @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefa(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

}
