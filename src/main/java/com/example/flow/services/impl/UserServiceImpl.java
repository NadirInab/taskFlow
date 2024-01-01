package com.example.flow.services.impl;

import com.example.flow.entities.Role;
import com.example.flow.entities.User;
import com.example.flow.repositories.RoleRepo;
import com.example.flow.repositories.UserRepo;
import com.example.flow.services.RoleService;
import com.example.flow.services.UserService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleService roleService;
    private final RoleRepo roleRepo;

    @Override
    public User save(User user) throws ValidationException  {
        if(userRepo.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");

        final List <Role> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roleService.findByName(role.getName()).ifPresent(roles::add));
        user.setRoles(roles);
        user.setNumberOfChangeTokens(2);
        user.setHasDeleteToken(true);
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByEmail(username) ;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepo.findById(userId) ;
    }

}
