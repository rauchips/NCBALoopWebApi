package org.example.ncbaloopwebapi.api.account.controller;

import org.example.ncbaloopwebapi.api.account.model.AccountDto;
import org.example.ncbaloopwebapi.api.account.model.AccountEntity;
import org.example.ncbaloopwebapi.api.account.service.AccountService;
import org.example.ncbaloopwebapi.api.utils.ResponseCustomEntity;
import org.example.ncbaloopwebapi.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.example.ncbaloopwebapi.api.utils.StringUtils.*;

@RestController()
@RequestMapping("api/account")
public class AccountController {
    AccountService accountService;
    UserService userService;
    ResponseCustomEntity responseCustomEntity;
    AccountEntity accountEntity;

    @Autowired
    public AccountController(AccountService accountService, UserService userService, ResponseCustomEntity responseCustomEntity, AccountEntity accountEntity){
        this.accountService = accountService;
        this.userService = userService;
        this.responseCustomEntity = responseCustomEntity;
        this.accountEntity = accountEntity;
    }

    /**
     * Find all Accounts
     * @return ResponseCustomEntity
     */
    @GetMapping("all")
    public ResponseEntity<ResponseCustomEntity> findAllAccounts(){
        try {
            List<AccountDto> accountsDto = accountService.findAllAccounts();

            if(accountsDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Accounts do not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully retrieved all accounts.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(accountsDto);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(responseCustomEntity);

        }catch (Exception exception){

            responseCustomEntity.setStatus(500);
            responseCustomEntity.setMessage("An error occurred, please try again later.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(exception.getMessage());
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);
        }
    }

    /**
     * Create new Account
     * @param id client id
     * @return ResponseCustomEntity
     */
    @GetMapping(value = "create", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> createAccount(@RequestParam UUID id){

        try {
            //check if user exists in the database
            var user = userService.findByUserByUserId(id);

            if(user == null){

                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Account creation failed, user does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            String accountNumberGenerator = AccountNumberGenerator();
            String bicSwift = BicSwiftGenerator();
            String iban = bicSwift + accountNumberGenerator;

            accountEntity.setIban(iban);
            accountEntity.setBicswift(bicSwift);
            accountEntity.setClientid(id);
            AccountDto savedAccount = accountService.createAccount(accountEntity);

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Dear " + user.firstname + " " + user.lastname + " your account has been created successfully");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(savedAccount);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);

        } catch (Exception exception) {
            responseCustomEntity.setStatus(500);
            responseCustomEntity.setMessage("An error occurred, please try again later.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(exception);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);
        }
    }

    /**
     * Retrieve Account using account id
     * @param id account id
     * @return ResponseCustomEntity
     */
    @GetMapping(value = "retrieve", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> retrieveAccount(@RequestParam UUID id){
        try {
            var accountDto = accountService.getAccountByAccountId(id);

            if(accountDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Account does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully retrieved account.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(accountDto);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);

        }catch (Exception exception){
            responseCustomEntity.setStatus(500);
            responseCustomEntity.setMessage("An error occurred, please try again later.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(exception.getMessage());
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);
        }
    }

    /**
     * Update branch for an Account
     * @param id account id
     * @return ResponseCustomEntity
     */
    @GetMapping(value = "update/branch", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> updateAccountBranch(@RequestParam UUID id) {
        try {
            var accountDto = accountService.getAccountByAccountId(id);

            if(accountDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Account does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            //generate new bic swift
            String bicSwift = BicSwiftGenerator();

            String iban = accountDto.getIban();
            String accountNumber = iban.split(" ")[2];

            //update iban but persists account number
            //simulating when a user changes branches
            iban = bicSwift + accountNumber;

            accountService.updateAccountByAccountId(id, iban, bicSwift);

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully updated branch for account " + "****" + accountNumber.substring(3, accountNumber.length() - 1));
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(null);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(responseCustomEntity);


        }catch (Exception exception){
            responseCustomEntity.setStatus(500);
            responseCustomEntity.setMessage("An error occurred, please try again later.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(exception.getMessage());
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);
        }
    }

    /**
     * Delete an Account
     * @param id account id
     * @return ResponseCustomEntity
     */
    @DeleteMapping(value = "delete", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> deleteAccount(@RequestParam UUID id){
        try{
            var accountDto = accountService.getAccountByAccountId(id);

            if(accountDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Account does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            accountService.deleteAccountByAccountId(id);

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully deleted account.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(null);
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(responseCustomEntity);

        }catch (Exception exception){

            responseCustomEntity.setStatus(500);
            responseCustomEntity.setMessage("An error occurred, please try again later.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(exception.getMessage());
            responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(responseCustomEntity);
        }
    }
}
