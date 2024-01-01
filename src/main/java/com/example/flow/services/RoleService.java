package com.example.flow.services;

import com.example.flow.entities.Role;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Role save(Role role) throws ValidationException;
    Optional<Role> findByName(String name) ;
    List<Role> getALlRoles();
    void delete(Long id);
    Role findById(Long id);

}