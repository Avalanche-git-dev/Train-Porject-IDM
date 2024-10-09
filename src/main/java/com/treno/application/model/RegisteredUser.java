package com.treno.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User {

    @Column(name="stato")
    private boolean stato;
    
}