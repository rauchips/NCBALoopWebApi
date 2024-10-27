package org.example.ncbaloopwebapi.api.user.model;

public class UserDto {
    public String firstname;
    public String middlename;
    public String lastname;
    public String emailaddress;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setMiddlename(String middlename){
        this.middlename = middlename;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}
