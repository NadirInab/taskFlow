package com.example.flow.mapper.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link com.example.flow.entities.Role}
 */
public record RoleDto(Long id, @NotEmpty @NotBlank String name) implements Serializable {
}