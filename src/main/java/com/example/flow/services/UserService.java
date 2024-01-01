package com.example.flow.services;


import com.example.flow.entities.User;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User save(User user) throws ValidationException;
    User update(User user);
    User delete(User user);
    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userId);

}
