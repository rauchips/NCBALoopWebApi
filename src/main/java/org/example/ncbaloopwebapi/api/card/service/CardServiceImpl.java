package org.example.ncbaloopwebapi.api.card.service;

import org.example.ncbaloopwebapi.api.card.model.CardDto;
import org.example.ncbaloopwebapi.api.card.model.CardEntity;
import org.example.ncbaloopwebapi.api.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;
    CardDto cardDto;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, CardDto cardDto) {
        this.cardRepository = cardRepository;
        this.cardDto = cardDto;
    }

    @Override
    public List<CardDto> findAllCards() {
        var cards = cardRepository.findAll();

        if(!cards.isEmpty()){
            List<CardDto> cardDtoList = cards.stream()
                    .map(card -> {
                        var cardDtoCopy = cardDto.copy();
                        cardDtoCopy.setAlias(card.getAlias());
                        cardDtoCopy.setType(card.getType());
                        return cardDtoCopy;
                    })
                    .collect(Collectors.toList());

            return cardDtoList;
        }

        return null;
    }

    @Override
    public CardDto createCard(CardEntity cardEntity) {

        var cardByAlias = cardRepository.getCardEntitiesByAlias(cardEntity.getAlias());
        var cardByAccount = cardRepository.getCardEntitiesByAccount(cardEntity.getAccount());

        if(cardByAlias == null && cardByAccount == null){
            cardRepository.save(cardEntity);

            cardDto.setAlias(cardEntity.getAlias());

            cardDto.setType(cardEntity.getType());

            return cardDto;
        }

        return null;
    }

    @Override
    public CardDto getCardByCardId(UUID id) {
        var card = cardRepository.getCardEntitiesById(id);

        if(card != null){
            cardDto.setAlias(card.getAlias());
            cardDto.setType(card.getType());
            return cardDto;
        }

        return null;
    }

    @Override
    public CardDto getCardByAccountId(UUID id) {
        var card = cardRepository.getCardEntitiesByAccount(id);

        if(card != null){
            cardDto.setAlias(card.getAlias());
            cardDto.setType(card.getType());
            return cardDto;
        }

        return null;
    }

    @Override
    public void updateCardByCardId(UUID id, String alias) {
        var card = cardRepository.getCardEntitiesById(id);
        if(card != null){
            cardRepository.updateCardEntitiesById(id, alias);
        }
    }

    @Override
    public void deleteCardByCardId(UUID id) {
        var card = cardRepository.getCardEntitiesById(id);
        if(card != null){
            cardRepository.deleteCardEntitiesById(id);
        }
    }
}
