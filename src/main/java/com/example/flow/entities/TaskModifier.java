package com.example.flow.entities;

import jakarta.persistence.*;

import com.example.flow.entities.enums.RequestStatus;
import com.example.flow.entities.enums.TokenType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskModifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateRequest;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @OneToOne
    private Task task;

    private Long oldOwnerId;
}
