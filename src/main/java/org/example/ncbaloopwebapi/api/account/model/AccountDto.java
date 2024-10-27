package org.example.ncbaloopwebapi.api.account.model;

import org.springframework.stereotype.Component;

@Component
public class AccountDto {
    private String iban;
    private String bicswift;
    public void setIban(String iban){
        this.iban = iban;
    }
    public String getIban() { return iban; }
    public void setBicswift(String bicswift){
        this.bicswift = bicswift;
    }
    public String getBicswift() { return bicswift; }

    public AccountDto copy() {
        AccountDto copy = new AccountDto();
        copy.setIban(this.iban);
        copy.setBicswift(this.bicswift);
        return copy;
    }


}
