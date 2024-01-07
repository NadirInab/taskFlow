package com.example.flow.services.impl;

import com.example.flow.entities.Tag;
import com.example.flow.repositories.TagRepo;
import com.example.flow.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepository;

 /*   @Override
    public List<Tag> findByNameIn(List<String> names) {
        List<String> tags = new ArrayList<>(names); // js, php , c
        List<Tag> existingTags = tagRepository.findByNameIn(names); // js, php
        if (existingTags.size() == tags.size()) {
            return existingTags;
        }
        existingTags.forEach(tag -> tags.remove(tag.getName()));
        tags.forEach(name -> existingTags.add(tagRepository.save(Tag.builder().name(name).build())));
        return existingTags;
    }*/

    @Override
    public List<Tag> findByNameIn(List<String> names) {
        List<Tag> existingTags = new ArrayList<>(tagRepository.findByNameIn(names)); // Create a modifiable copy
        List<String> tags = new ArrayList<>(names);

        if (existingTags.size() == tags.size()) {
            return existingTags;
        }

        existingTags.forEach(tag -> tags.remove(tag.getName()));
        tags.forEach(name -> existingTags.add(tagRepository.save(Tag.builder().name(name).build())));

        return existingTags;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findTagsByName(List<String> names) {
        return tagRepository.findByNameIn(names);
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTagById(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
