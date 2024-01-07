package com.example.flow.dtosop;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto implements Serializable {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private LocalDateTime startDate;

    private LocalDateTime assignedDate;


    private boolean completed;

    private boolean hasChanged;

    @NotEmpty
    private List<TagDto> tags;

    private String createdBy;

    private String assignedTo;
}