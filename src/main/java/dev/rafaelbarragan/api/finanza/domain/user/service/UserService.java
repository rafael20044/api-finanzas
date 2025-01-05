package dev.rafaelbarragan.api.finanza.domain.user.service;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserResponse;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import dev.rafaelbarragan.api.finanza.domain.user.repository.UserRepositoty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final UserRepositoty repositoty;
    private final BCryptPasswordEncoder passwordEncoder;

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
        throw new RuntimeException("Usuario existente");
    }

    @Override
    public User findEntity(Long id) {
        return repositoty.findById(id).orElseThrow(()->new RuntimeException("Usuario no existente"));
    }
}
