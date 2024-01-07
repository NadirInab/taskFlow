package com.example.flow;

import com.example.flow.entities.Tag;
import com.example.flow.repositories.TagRepo;
import com.example.flow.services.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TagServiceImplTest {

    @Mock
    private TagRepo tagRepo;

    @InjectMocks
    private TagServiceImpl tagService;

    public TagServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        tagService = new TagServiceImpl(tagRepo);
    }

    @Test
    public void testFindByNameIn() {
        // Arrange
        List<String> names = Arrays.asList("java", "python", "javascript");

        Tag existingTag = Tag.builder().name("java").build();
        when(tagRepo.findByNameIn(names)).thenReturn(Arrays.asList(existingTag));

        when(tagRepo.save(any(Tag.class))).thenAnswer(invocation -> {
            Tag newTag = invocation.getArgument(0);
            newTag.setId(1L);
            return newTag;
        });


        List<Tag> resultTags = tagService.findByNameIn(names);


        assertThat(resultTags).hasSize(3);
        verify(tagRepo, times(1)).findByNameIn(names);
        verify(tagRepo, times(2)).save(any(Tag.class));
    }
}
