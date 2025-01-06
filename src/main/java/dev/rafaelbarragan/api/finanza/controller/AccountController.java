package dev.rafaelbarragan.api.finanza.controller;

import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountCreate;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountEdit;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountFind;
import dev.rafaelbarragan.api.finanza.domain.account.dto.AccountResponse;
import dev.rafaelbarragan.api.finanza.domain.account.service.AccountService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cuenta")
@Transactional
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountCreate create, UriComponentsBuilder builder){
        var respuesta = service.crear(create);
        URI uri = builder.path("/cuenta/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountFind> find(@PathVariable Long id){
        var respuesta = service.find(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> findAll(Pageable pageable){
        var page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<AccountResponse> edit(@RequestBody @Valid AccountEdit accountEdit){
        var respuesta = service.edit(accountEdit);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/perma/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        var mensaje = service.delete(id);
        return ResponseEntity.ok(mensaje);
    }
}
