package org.example.ncbaloopwebapi.api.card.service;

import org.example.ncbaloopwebapi.api.card.model.CardDto;
import org.example.ncbaloopwebapi.api.card.model.CardEntity;

import java.util.List;
import java.util.UUID;

public interface CardService {
    /**
     * Retrieves a list of all CardDto objects.
     *
     * @return A list of CardDto instances or null if no cards are found.
     */
    List<CardDto> findAllCards();
    /**
     * Creates a new Card from the CardEntity object
     *
     * @return An CardDto instance
     */
    CardDto createCard(CardEntity cardEntity);
    /**
     * Retrieves an CardDto object.
     *
     * @param id card id
     * @return An CardDto instance or null if no card is found
     */
    CardDto getCardByCardId(UUID id);
    /**
     * Retrieves an CardDto object.
     *
     * @param id account id
     * @return An CardDto instance or null if no card is found
     */
    CardDto getCardByAccountId(UUID id);
    /**
     * Updates a Card object
     * @param id card id
     * @param alias card alias
     */
    void updateCardByCardId(UUID id, String alias);
    /**
     * Deletes a Card object
     * @param id account id
     */
    void deleteCardByCardId(UUID id);
}
