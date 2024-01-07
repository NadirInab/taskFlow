package com.example.flow.mapper;

import com.example.flow.entities.Task;
import com.example.flow.mapper.dtos.TaskDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {TagMapper.class})
public interface TaskMapper {
    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(TaskDto taskDto, @MappingTarget Task task);
}