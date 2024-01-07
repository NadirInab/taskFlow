package com.example.flow.mapper;


import com.example.flow.entities.Tag;
import com.example.flow.mapper.dtos.TagDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagMapper {
    Tag toEntity(TagDto tagDto);

    TagDto toDto(Tag tag);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tag partialUpdate(TagDto tagDto, @MappingTarget Tag tag);

}