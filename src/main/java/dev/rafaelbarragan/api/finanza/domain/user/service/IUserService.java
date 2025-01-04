package dev.rafaelbarragan.api.finanza.domain.user.service;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserResponse;

public interface IUserService {

    UserResponse create(UserCreate create);

}
