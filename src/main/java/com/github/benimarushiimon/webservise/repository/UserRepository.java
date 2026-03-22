package com.github.benimarushiimon.webservise.repository;

import com.github.benimarushiimon.webservise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUsersByEmailIgnoreCase(String email);

    boolean existsByName(String name);

    void deleteByName(String name);

    boolean existsById(Long id);
}
