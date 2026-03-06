package com.github.benimarushiimon.webservise.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "username", nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Column(name = "phone")
    private Long phone;

    /** Age in years, computed from birth_date. Not persisted. */
    public Integer getAge() {
        return birthDate == null ? null : Period.between(birthDate, LocalDate.now()).getYears();
    }


    public User(Long id,String name, String email, LocalDate birthDate, Long phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birthDate +
                ", age=" + Period.between(birthDate, LocalDate.now()).getYears() +
                ", phone number =" + phone +
                '}';
    }
}
