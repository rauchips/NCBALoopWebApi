package org.example.ncbaloopwebapi.api.user.service;

import org.example.ncbaloopwebapi.api.user.model.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /**
     * Retrieves a list of all UserDto objects.
     *
     * @return A list of UserDto instances or null if no users are found.
     */
    List<UserDto> findAllUsers();

    /**
     * Retrieves a UserDto object.
     *
     * @param id user id
     * @return A UserDto instance or null if no user is found
     */
    UserDto findByUserByUserId(UUID id);
}
