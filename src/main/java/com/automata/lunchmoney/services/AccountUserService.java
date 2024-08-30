package com.automata.lunchmoney.services;

import com.automata.lunchmoney.dtos.AccountUserDTO;
import com.automata.lunchmoney.entities.AccountUser;
import com.automata.lunchmoney.mappers.AccountUserMapper;
import com.automata.lunchmoney.repositories.AccountUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountUserService {

    private final AccountUserRepository accountUserRepository;

    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    public AccountUserDTO createUser(AccountUserDTO accountUserDTO) {
        AccountUser accountUser = AccountUserMapper.toEntity(accountUserDTO);
        AccountUser savedUser = accountUserRepository.save(accountUser);
        return AccountUserMapper.toDTO(savedUser);
    }

    public AccountUserDTO getUserById(UUID userId) {
        AccountUser accountUser = accountUserRepository.findById(userId).orElse(null);
        return AccountUserMapper.toDTO(accountUser);
    }

    public AccountUserDTO updateUser(UUID userId, AccountUserDTO accountUserDTO) {
        AccountUser existingUser = accountUserRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(accountUserDTO.getFirstName());
            existingUser.setLastName(accountUserDTO.getLastName());
            existingUser.setAccountBalanceId(accountUserDTO.getAccountBalanceId());
            existingUser.setTransactions(accountUserDTO.getTransactions());
            AccountUser updatedUser = accountUserRepository.save(existingUser);
            return AccountUserMapper.toDTO(updatedUser);
        }
        return null;
    }

    public void deleteUser(UUID userId) {
        accountUserRepository.deleteById(userId);
    }

    public List<AccountUserDTO> getAllUsers() {
        List<AccountUser> users = accountUserRepository.findAll();
        return users.stream()
                .map(AccountUserMapper::toDTO)
                .collect(Collectors.toList());
    }
}