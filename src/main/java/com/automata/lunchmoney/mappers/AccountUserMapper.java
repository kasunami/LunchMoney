package com.automata.lunchmoney.mappers;

import com.automata.lunchmoney.entities.AccountUser;
import com.automata.lunchmoney.dtos.AccountUserDTO;

public class AccountUserMapper {

    public static AccountUserDTO toDTO(AccountUser accountUser) {
        if (accountUser == null) {
            return null;
        }

        AccountUserDTO dto = new AccountUserDTO();
        dto.setFirstName(accountUser.getFirstName());
        dto.setLastName(accountUser.getLastName());
        dto.setAccountBalanceId(accountUser.getAccountBalanceId());
        dto.setTransactions(accountUser.getTransactions());

        return dto;
    }

    public static AccountUser toEntity(AccountUserDTO accountUserDTO) {
        if (accountUserDTO == null) {
            return null;
        }

        AccountUser accountUser = new AccountUser();
        accountUser.setFirstName(accountUserDTO.getFirstName());
        accountUser.setLastName(accountUserDTO.getLastName());
        accountUser.setAccountBalanceId(accountUserDTO.getAccountBalanceId());
        accountUser.setTransactions(accountUserDTO.getTransactions());

        return accountUser;
    }
}