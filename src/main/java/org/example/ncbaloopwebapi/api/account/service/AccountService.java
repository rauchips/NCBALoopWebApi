package org.example.ncbaloopwebapi.api.account.service;

import org.example.ncbaloopwebapi.api.account.model.AccountDto;
import org.example.ncbaloopwebapi.api.account.model.AccountEntity;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    /**
     * Retrieves a list of all AccountDto objects.
     *
     * @return A list of AccountDto instances or null if no accounts are found.
     */
    List<AccountDto> findAllAccounts();
    /**
     * Creates a new Account from the AccountEntity object
     *
     * @return An AccountDto instance
     */
    AccountDto createAccount(AccountEntity accountEntity);
    /**
     * Retrieves an AccountDto object.
     *
     * @param id account id
     * @return An AccountDto instance or null if no account is found
     */
    AccountDto getAccountByAccountId(UUID id);
    /**
     * Updates an Account object
     * @param id account id
     * @param Iban Iban
     * @param BicSwift BicSwift
     */
    void updateAccountByAccountId(UUID id, String Iban, String BicSwift);
    /**
     * Deletes an Account object
     * @param id account id
     */
    void deleteAccountByAccountId(UUID id);
}
