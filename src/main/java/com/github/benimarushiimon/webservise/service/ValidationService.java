package com.github.benimarushiimon.webservise.service;

import com.github.benimarushiimon.webservise.repository.UserRepository;
import com.github.benimarushiimon.webservise.web.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidationService {
    private final UserRepository userRepository;

    public ValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateCreateUser(UserDto userDto) {
        if (userDto == null
                || !StringUtils.hasText(userDto.getName())
                || !StringUtils.hasText(userDto.getEmail())
                || userDto.getBirthDate() == null
                || userDto.getPhone() == null) {
            throw new IllegalArgumentException("Обязательные поля не заполнены: имя, email, дата рождения, телефон.");
        }
        if (userRepository.existsUsersByEmailIgnoreCase(userDto.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует: " + userDto.getEmail()
                    + ". Проверьте корректность вашего email.");
        }
    }

    public void validateDeleteUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Поле ID не может быть null");
        }
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Пользователь c id: " + id + " не найден");
        }
    }

    public void validateDeleteUserByName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Поле Имя не может быть пустым!");
        }
        if (!userRepository.existsByName(name)) {
            throw new IllegalArgumentException("Пользователь с именем: " + name + " не найден!");
        }
    }
}