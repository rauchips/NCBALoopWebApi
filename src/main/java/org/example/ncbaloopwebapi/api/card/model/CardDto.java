package org.example.ncbaloopwebapi.api.card.model;

import org.springframework.stereotype.Component;


@Component
public class CardDto {
    private String alias;
    CardTypeEnum type;
    public void setAlias(String alias){
        this.alias = alias;
    }
    public String getAlias(){
        return  alias;
    }

    public void setType(CardTypeEnum type){
        this.type = type;
    }
    public CardTypeEnum getType(){
        return type;
    }
    public CardDto copy() {
        CardDto copy = new CardDto();
        copy.setAlias(this.alias);
        copy.setType(this.type);
        return copy;
    }
}
