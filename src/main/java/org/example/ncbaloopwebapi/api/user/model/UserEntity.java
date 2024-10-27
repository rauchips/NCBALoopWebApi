package org.example.ncbaloopwebapi.api.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "loopuser")
public class UserEntity {

    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonProperty("firstname")
    @Column(nullable = false)
    private String firstname;

    @JsonProperty("middlename")
    @Column(nullable = false)
    private String middlename;

    @JsonProperty("lastname")
    @Column(nullable = false)
    private String lastname;

    @JsonProperty("emailaddress")
    @Column(nullable = false)
    private String emailaddress;

    @JsonProperty("password")
    @Column(nullable = false)
    private String password;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}