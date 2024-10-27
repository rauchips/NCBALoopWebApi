package org.example.ncbaloopwebapi.api.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Component
@Table(name = "loopaccount")
public class AccountEntity {
    @Id
    @Column(name = "accountid", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonProperty("iban")
    @Column(nullable = false)
    private String iban;
    @JsonProperty("bicswift")
    @Column(nullable = false)
    private String bicswift;
    @JsonProperty("clientid")
    @Column(nullable = false)
    private UUID clientid;

    public void setId(UUID id){
        this.id = id;
    }
    public UUID getId(){
        return id;
    }
    public void setIban(String iban){
        this.iban = iban;
    }
    public String getIban(){
        return iban;
    }
    public void setBicswift(String bicswift){
        this.bicswift = bicswift;
    }
    public String getBicswift(){
        return bicswift;
    }
    public void setClientid(UUID clientid){
        this.clientid = clientid;
    }
    public UUID getClientid(){
        return clientid;
    }
}
