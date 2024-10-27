package org.example.ncbaloopwebapi.api.card.controller;

import org.example.ncbaloopwebapi.api.account.service.AccountService;
import org.example.ncbaloopwebapi.api.card.model.CardAliasDto;
import org.example.ncbaloopwebapi.api.card.model.CardDto;
import org.example.ncbaloopwebapi.api.card.model.CardEntity;
import org.example.ncbaloopwebapi.api.card.service.CardService;
import org.example.ncbaloopwebapi.api.utils.ResponseCustomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/card")
@RestController
public class CardController {
    CardService cardService;
    AccountService accountService;
    ResponseCustomEntity responseCustomEntity;
    CardAliasDto cardAliasDto;


    @Autowired
    public CardController(CardService cardService, AccountService accountService, ResponseCustomEntity responseCustomEntity, CardAliasDto cardAliasDto) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.responseCustomEntity = responseCustomEntity;
        this.cardAliasDto = cardAliasDto;
    }

    /**
     * Find all Cards
     * @return ResponseCustomEntity
     */
    @GetMapping("all")
    public ResponseEntity<ResponseCustomEntity> findAllCards(){
        try {
            List<CardDto> cardDtos = cardService.findAllCards();

            if(cardDtos == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Cards do not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully retrieved all cards.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(cardDtos);
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
     * Create new Card
     * @param id account id
     * @param cardDto card alias and card type { physical or virtual }
     * @return ResponseCustomEntity
     */
    @PostMapping(value = "create", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> createCard(@RequestParam UUID id, @RequestBody CardDto cardDto){
        try{
            //check if account exists in the database
            var account = accountService.getAccountByAccountId(id);

            if(account == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Card creation failed, account does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            var cardEntity = new CardEntity();
            cardEntity.setAlias(cardDto.getAlias());
            cardEntity.setAccount(id);
            cardEntity.setType(cardDto.getType());
            var card = cardService.createCard(cardEntity);

            if(card == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Card alias already exists or this account already linked to a card.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully created a new card.");
            responseCustomEntity.setError(true);
            responseCustomEntity.setData(card);
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
     * Retrieve Card using card id
     * @param id card id
     * @return ResponseCustomEntity
     */
    @GetMapping(value = "retrieve/card", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> retrieveCardByCard(@RequestParam UUID id){
        try{
            var cardDto = cardService.getCardByCardId(id);

            if(cardDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Card does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully retrieved card.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(cardDto);
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
     * Retrieve Card using account id
     * @param id account id
     * @return ResponseCustomEntity
     */
    @GetMapping(value = "retrieve/account", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> retrieveCardByAccount(@RequestParam UUID id){
        try{
            var cardDto = cardService.getCardByAccountId(id);

            if(cardDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Card does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully retrieved card.");
            responseCustomEntity.setError(false);
            responseCustomEntity.setData(cardDto);
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
     * Update alias for a Card
     * @param id card id
     * @param alias card alias
     * @return ResponseCustomEntity
     */
    @PatchMapping(value = "update/alias", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> updateCardAlias(@RequestParam UUID id, @RequestBody CardAliasDto alias) {
        try {
            var cardDto = cardService.getCardByCardId(id);

            if(cardDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Card does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            cardService.updateCardByCardId(id, alias.getAlias());

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully updated card alias.");
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
     * Delete a Card
     * @param id card id
     * @return ResponseCustomEntity
     */
    @DeleteMapping(value = "delete", produces = "application/json")
    public ResponseEntity<ResponseCustomEntity> deleteCard(@RequestParam UUID id){
        try{
            var cardDto = cardService.getCardByCardId(id);

            if(cardDto == null){
                responseCustomEntity.setStatus(404);
                responseCustomEntity.setMessage("Card does not exist.");
                responseCustomEntity.setError(true);
                responseCustomEntity.setData(null);
                responseCustomEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));

                return ResponseEntity.ok(responseCustomEntity);
            }

            cardService.deleteCardByCardId(id);

            responseCustomEntity.setStatus(200);
            responseCustomEntity.setMessage("Successfully deleted card.");
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
