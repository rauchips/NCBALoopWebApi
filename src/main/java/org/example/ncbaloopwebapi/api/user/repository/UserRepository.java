package org.example.ncbaloopwebapi.api.user.repository;

import org.example.ncbaloopwebapi.api.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findUserEntitiesById(UUID id);
    List<UserEntity> findAll();
}
