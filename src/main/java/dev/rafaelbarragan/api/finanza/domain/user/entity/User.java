package dev.rafaelbarragan.api.finanza.domain.user.entity;

import dev.rafaelbarragan.api.finanza.domain.account.entity.Account;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserEdit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String passwordHash;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accountList;

    public User(UserCreate create, String passWordHash) {
        this.userName = create.userName();
        this.email = create.email();
        this.passwordHash = passWordHash;
        this.createdAt = LocalDateTime.now();
    }

    public void update(UserEdit edit, String passwordHash) {
        if (edit.userName() != null) {
            this.userName = edit.userName();
        }
        if (edit.email() != null) {
            this.email = edit.email();
        }
        if (passwordHash != null) {
            this.passwordHash = passwordHash;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
