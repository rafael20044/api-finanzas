package dev.rafaelbarragan.api.finanza.domain.user.service;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserDelete;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserEdit;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserResponse;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import dev.rafaelbarragan.api.finanza.domain.user.repository.UserRepositoty;
import dev.rafaelbarragan.api.finanza.exception.CorreoException;
import dev.rafaelbarragan.api.finanza.exception.ExistenciaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final UserRepositoty repositoty;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepositoty repositoty){
        this.repositoty = repositoty;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserResponse create(UserCreate create) {

        Optional<User> optional = repositoty.findByEmail(create.email());
        if (optional.isEmpty()) {
            String passWordHash = passwordEncoder.encode(create.password());
            User user = new User(create, passWordHash);
            repositoty.save(user);
            return new UserResponse(user);
        }
        throw new ExistenciaException("Usuario existente");
    }

    @Override
    public User findEntity(Long id) {
        return repositoty.findById(id).orElseThrow(()->new ExistenciaException("Usuario no existente"));
    }

    @Override
    public UserResponse find(Long id) {
        User user = repositoty.findById(id).orElseThrow(()->new ExistenciaException("Usuario no existente"));
        return new UserResponse(user);
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        return repositoty.findAll(pageable).map(UserResponse::new);
    }

    @Override
    public UserResponse edit(UserEdit edit) {
        Optional<User> optional = repositoty.findByEmail(edit.email());
        if (optional.isEmpty()) {
            String passWordHash = edit.password() != null ? passwordEncoder.encode(edit.password()) : null;
            User user = findEntity(edit.id());
            user.update(edit, passWordHash);
            repositoty.save(user);
            return new UserResponse(user);
        }
        throw new CorreoException("Correo existente");
    }

    @Override
    public UserDelete delete(Long id) {
        repositoty.deleteById(id);
        return new UserDelete("Usuario borrado", 200);
    }
}
