package com.github.benimarushiimon.webservise.controller;

import com.github.benimarushiimon.webservise.exception.UserNotFoundException;
import com.github.benimarushiimon.webservise.model.User;
import com.github.benimarushiimon.webservise.service.UserService;
import com.github.benimarushiimon.webservise.web.dto.UserDto;
import com.github.benimarushiimon.webservise.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping
    public List<UserDto> getAll(){
        return userService.findAllDto();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        User user = userService.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Пользователь с ID: " + id + "не найден"));
        return userMapper.toDto(user);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto){
        return userMapper.toDto(userService.create(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.delete(id);
}
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserDto dto){
        userService.update(id, dto.getName(), dto.getEmail(), dto.getBirthDate(), dto.getPhone());
    }
}