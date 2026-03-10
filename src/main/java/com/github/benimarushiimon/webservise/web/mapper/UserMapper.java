package com.github.benimarushiimon.webservise.web.mapper;

import com.github.benimarushiimon.webservise.model.User;
import com.github.benimarushiimon.webservise.web.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthDate(),
                user.getPhone()
        );
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users == null
                ? List.of()
                : users.stream()
                        .filter(Objects::nonNull)
                        .map(this::toDto)
                        .collect(Collectors.toList());
    }

    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getBirthDate(),
                dto.getPhone()
        );
    }
}

