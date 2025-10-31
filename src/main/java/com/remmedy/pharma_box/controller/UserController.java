package com.remmedy.pharma_box.controller;

import com.remmedy.pharma_box.dto.user.UserDto;
import com.remmedy.pharma_box.dto.user.UserNew;
import com.remmedy.pharma_box.dto.user.UserUpdate;
import com.remmedy.pharma_box.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto userRegistration(@Valid @RequestBody UserNew userNew) {
        log.info("Creating a new user with email: {}", userNew.email());
        return userService.userRegistration(userNew);
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(defaultValue = "0")
                                     @PositiveOrZero(message = "Page index must be 0 or greater") int page,
                                     @RequestParam(defaultValue = "10")
                                     @Positive(message = "Page size must be positive") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUserById(@PathVariable UUID userId,
                                  @RequestBody @Valid UserUpdate userUpdate) {
        log.info("Updating a user by ID {}.", userId);
        return userService.updateUserById(userId, userUpdate);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable UUID userId) {
        log.info("Deleting a user by ID {}.", userId);
        userService.deleteUserById(userId);
    }
}
