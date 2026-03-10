package com.github.benimarushiimon.webservise.service;

import com.github.benimarushiimon.webservise.model.User;
import com.github.benimarushiimon.webservise.repository.UserRepository;
import com.github.benimarushiimon.webservise.web.dto.UserDto;
import com.github.benimarushiimon.webservise.web.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ValidationService validationService;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, ValidationService validationService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.validationService = validationService;
    }

    public User create(UserDto userDto) {
        validationService.validateCreateUser(userDto);
        User userToSave = userMapper.toEntity(userDto);
        User userSave = userRepository.save(userToSave);
        log.info("Пользователь создан! {}", userSave);
        return userSave;
    }

    public void delete(Long id) {
        validationService.validateDeleteUserById(id);
        userRepository.deleteById(id);
        log.info("Пользователь с id: {} успешно удален", id);
    }

    public void deleteForName(String name) {
        validationService.validateDeleteUserByName(name);
        userRepository.deleteByName(name);
        log.info("Пользователь с именем: {} удален", name);
    }

    @Transactional
    public void update(Long id, String name, String email, LocalDate birthDate, Long phone) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Пользователь с id {} не найден!", id);
            return new IllegalArgumentException("Проверьте корректность введенного вами Id и внесите соответствующие изменения");
        });
        boolean isChanged = false;
        if (StringUtils.hasText(email) && !email.equals(user.getEmail())) {
            if (userRepository.existsUsersByEmailIgnoreCase(email)) {
                throw new IllegalArgumentException("Пользователь с таким Email уже существует!"
                        + "\nПроверьте корректность вводимого вами Email");
            }
            user.setEmail(email);
            isChanged = true;
        }
        if (StringUtils.hasText(name) && !name.equals(user.getName())) {
            user.setName(name);
            isChanged = true;
        }
        if (phone != null && !phone.equals(user.getPhone())) {
            user.setPhone(phone);
            isChanged = true;
        }

        if (isChanged) {
            User userSave = userRepository.save(user);
            log.info("Информация о пользователе обновлена!");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserDto> findAllDto() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
