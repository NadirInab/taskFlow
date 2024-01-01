package com.example.flow.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private String id;
    private String fullName;
    private String email;
    private List<TaskDto> task;
    private Integer usedTokenCount;
    private Integer percentageCompleted;
    private LocalDate filterStartDate;
    private LocalDate filterEndDate;

    public UserDto() {
    }

    public UserDto(String id, String fullName, String email, List<TaskDto> task, Integer usedTokenCount, Integer percentageCompleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.task = task;
        this.usedTokenCount = usedTokenCount;
        this.percentageCompleted = percentageCompleted;
    }
}
