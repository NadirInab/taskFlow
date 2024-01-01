package com.example.flow.repositories;

import com.example.flow.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Long> {
    List<Tag> findByNameIn(List<String> names);
}
