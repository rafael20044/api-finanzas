package dev.rafaelbarragan.api.finanza.controller;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserDelete;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserEdit;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserResponse;
import dev.rafaelbarragan.api.finanza.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Transactional
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable Long id){
        var response = service.find(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable){
        var pages = service.findAll(pageable);
        return ResponseEntity.ok(pages);
    }

    @PutMapping
    public ResponseEntity<UserResponse> edit(@RequestBody @Valid UserEdit edit){
        var response = service.edit(edit);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/perma/{id}")
    public ResponseEntity<UserDelete> delete(@PathVariable Long id){
        var delete = service.delete(id);
        return ResponseEntity.ok(delete);
    }
}
