package com.automata.lunchmoney.controllers;

import com.automata.lunchmoney.dtos.AccountUserDTO;
import com.automata.lunchmoney.services.AccountUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/accountUsers")
public class AccountUserController {
    private final AccountUserService accountUserService;

    public AccountUserController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<AccountUserDTO> createUser(@RequestBody AccountUserDTO accountUser) {
        AccountUserDTO createdAccountUser = accountUserService.createUser(accountUser);
        return ResponseEntity.ok(createdAccountUser);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<AccountUserDTO> getUserById(@PathVariable UUID userId) {
        AccountUserDTO accountUser = accountUserService.getUserById(userId);
        return ResponseEntity.ok(accountUser);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<AccountUserDTO> updateUser(@PathVariable UUID userId, @RequestBody AccountUserDTO accountUser) {
        AccountUserDTO updatedUser = accountUserService.updateUser(userId, accountUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        accountUserService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<AccountUserDTO>> getAllUsers() {
        List<AccountUserDTO> users = accountUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}