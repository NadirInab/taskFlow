package com.example.flow.dtosop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    @NotBlank
    @NotNull
    private String name;
}
