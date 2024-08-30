package com.automata.lunchmoney.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "account_users")
public class AccountUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "account_id", unique = true, nullable = false)
    private String accountBalanceId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "accountUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions;
}