package dev.rafaelbarragan.api.finanza.domain.user.entity;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String passwordHash;

    private LocalDateTime createdAt;

    public User(UserCreate create, String passWordHash) {
        this.userName = create.userName();
        this.email = create.email();
        this.passwordHash = passWordHash;
        this.createdAt = LocalDateTime.now();
    }
}
