package org.example.ncbaloopwebapi.api.account.repository;

import org.example.ncbaloopwebapi.api.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    List<AccountEntity> findAll();
    AccountEntity getAccountEntitiesById(UUID id);
    @Modifying
    @Transactional
    @Query("update AccountEntity a set a.iban = :iban, a.bicswift = :bicswift where a.id = :id")
    void updateAccountEntitiesById(@Param("id") UUID id, @Param("iban") String Iban, @Param("bicswift") String BicSwift);
    @Modifying
    @Transactional
    @Query("delete AccountEntity a where a.id = :id")
    void deleteAccountEntitiesBy(@Param("id") UUID id);
}
