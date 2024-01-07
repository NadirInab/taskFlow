package com.example.flow.controllers;

import com.example.flow.entities.Tag;
import com.example.flow.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<Tag>> getTagsByName(@RequestParam List<String> names) {
        List<Tag> tags = tagService.findTagsByName(names);
        return ResponseEntity.ok(tags);
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        Tag savedTag = tagService.addTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTagById(tagId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
