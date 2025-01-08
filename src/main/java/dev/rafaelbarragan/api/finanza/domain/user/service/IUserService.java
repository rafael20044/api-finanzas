package dev.rafaelbarragan.api.finanza.domain.user.service;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserDelete;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserEdit;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserResponse;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserResponse create(UserCreate create);

    User findEntity(Long id);

    UserResponse find(Long id);

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse edit(UserEdit edit);

    UserDelete delete(Long id);
}
