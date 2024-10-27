package org.example.ncbaloopwebapi.api.card.repository;

import org.example.ncbaloopwebapi.api.card.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    List<CardEntity> findAll();
    CardEntity getCardEntitiesById(UUID id);
    CardEntity getCardEntitiesByAlias(String alias);
    CardEntity getCardEntitiesByAccount(UUID id);
    @Modifying
    @Transactional
    @Query("update CardEntity a set a.alias = :alias where a.id = :id")
    void updateCardEntitiesById(@Param("id") UUID id, @Param("alias") String alias);
    @Modifying
    @Transactional
    @Query("delete CardEntity a where a.id = :id")
    void deleteCardEntitiesById(@Param("id") UUID id);
}
