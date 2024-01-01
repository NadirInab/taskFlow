package com.example.flow.repositories;

import com.example.flow.entities.TaskModifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskModifierRepo extends JpaRepository<TaskModifier, Long> {
}
