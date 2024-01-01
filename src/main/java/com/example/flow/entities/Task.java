package com.example.flow.entities;

import com.example.flow.entities.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime deadline;

    private LocalDateTime startDate;

    private LocalDateTime assignedDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean completed;

    private boolean hasChanged;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    private List<Tag> tags;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User user;

    @OneToOne
    private TaskModifier taskChangeRequest;
}
