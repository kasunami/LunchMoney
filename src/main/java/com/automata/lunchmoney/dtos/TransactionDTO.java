package com.automata.lunchmoney.dtos;

import com.automata.lunchmoney.configuration.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String item;
    private double amount;
    private int quantity;
}
