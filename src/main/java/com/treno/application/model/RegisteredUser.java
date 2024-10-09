package com.treno.application.model;

import jakarta.persistence.*;

@Entity
public class RegisteredUser extends User {

    @Column(name="stato")
    private boolean stato;
    
}