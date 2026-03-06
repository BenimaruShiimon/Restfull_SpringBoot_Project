package com.github.benimarushiimon.webservise.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Long phone;

    /** Age in years, computed from birthDate. Not a persisted field. */
    public Integer getAge() {
        return birthDate == null ? null : Period.between(birthDate, LocalDate.now()).getYears();
    }
}
