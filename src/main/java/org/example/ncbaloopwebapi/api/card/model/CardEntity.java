package org.example.ncbaloopwebapi.api.card.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "loopcard")
public class CardEntity {
    @Id
    @Column(name = "cardid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonProperty("alias")
    @Column(name = "cardalias")
    private String alias;

    @JsonProperty("account")
    @Column(name = "accountid")
    private UUID account;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    @Column(name = "cardtype")
    private CardTypeEnum type;

    public void setId(UUID id){
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }
    public String getAlias(){
        return alias;
    }
    public void setAccount(UUID account){
        this.account = account;
    }
    public UUID getAccount(){
        return account;
    }
    public void setType(CardTypeEnum type){
        this.type = type;
    }
    public CardTypeEnum getType(){
        return type;
    }
}
