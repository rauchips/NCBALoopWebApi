package org.example.ncbaloopwebapi.api.account.service;

import org.example.ncbaloopwebapi.api.account.model.AccountDto;
import org.example.ncbaloopwebapi.api.account.model.AccountEntity;
import org.example.ncbaloopwebapi.api.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;
    AccountDto accountDto;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountDto accountDto){
        this.accountRepository = accountRepository;
        this.accountDto = accountDto;
    }
    @Override
    public List<AccountDto> findAllAccounts() {

        var accounts = accountRepository.findAll();

        if(!accounts.isEmpty()){
            return accounts.stream()
                    .map(account -> {
                        var accountDtoCopy = accountDto.copy();
                        accountDtoCopy.setIban(account.getIban());
                        accountDtoCopy.setBicswift(account.getBicswift());
                        return accountDtoCopy;
                    })
                    .collect(Collectors.toList());
        }

        return null;
    }
    @Override
    public AccountDto createAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);

        accountDto.setIban(accountEntity.getIban());
        accountDto.setBicswift(accountEntity.getBicswift());

        return accountDto;
    }

    @Override
    public AccountDto getAccountByAccountId(UUID id) {
        var account = accountRepository.getAccountEntitiesById(id);

        if(account != null){
            accountDto.setIban(account.getIban());
            accountDto.setBicswift(account.getBicswift());
            return accountDto;
        }

        return null;
    }

    @Override
    public void updateAccountByAccountId(UUID id, String Iban, String BicSwift) {
        var account = accountRepository.getAccountEntitiesById(id);
        if(account != null){
            accountRepository.updateAccountEntitiesById(id, Iban, BicSwift);
        }
    }

    @Override
    public void deleteAccountByAccountId(UUID id){
        var account = accountRepository.getAccountEntitiesById(id);
        if(account != null){
            accountRepository.deleteAccountEntitiesBy(id);
        }
    }
}
