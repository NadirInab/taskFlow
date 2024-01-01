package com.example.flow.mapper.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.example.flow.entities.Tag}
 */
public record TagDto(Long id, String name) implements Serializable {
}