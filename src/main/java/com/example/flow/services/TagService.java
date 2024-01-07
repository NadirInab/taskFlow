package com.example.flow.services;

import com.example.flow.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    List<Tag> findByNameIn(List<String> names);

    List<Tag> getAllTags();

    List<Tag> findTagsByName(List<String> names);

    Tag addTag(Tag tag);

    void deleteTagById(Long tagId);
}
