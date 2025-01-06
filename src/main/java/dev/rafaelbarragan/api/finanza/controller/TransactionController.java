package dev.rafaelbarragan.api.finanza.controller;

import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionCreate;
import dev.rafaelbarragan.api.finanza.domain.transaction.dto.TransactionResponse;
import dev.rafaelbarragan.api.finanza.domain.transaction.service.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transaccion")
@Transactional
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@RequestBody @Valid TransactionCreate create,
                                                      UriComponentsBuilder builder){
        var response = service.crear(create);
        URI uri = builder.path("/transaccion/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> cacelar(@PathVariable Long id){
        var response = service.cancelar(id);
        return ResponseEntity.ok(response);
    }
}
