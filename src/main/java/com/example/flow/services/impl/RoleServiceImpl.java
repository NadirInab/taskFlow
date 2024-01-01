package com.example.flow.services.impl;

import com.example.flow.entities.Role;
import com.example.flow.repositories.RoleRepo;
import com.example.flow.services.RoleService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role save(Role role) throws ValidationException {
        Optional<Role> optionalRole = roleRepo.findByName(role.getName());
        if (optionalRole.isPresent()) return roleRepo.save(role);
        return role;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepo.findByName(name);
    }

    @Override
    public List<Role> getALlRoles()
    {
        return roleRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Role> role = roleRepo.findById(id);
        role.ifPresent(roleRepo::delete);
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> roleOptional = roleRepo.findById(id);
        if(roleOptional.isEmpty())
            throw new IllegalArgumentException("role doesn't exist with this id: " + id);
        return roleOptional.get();
    }
}
