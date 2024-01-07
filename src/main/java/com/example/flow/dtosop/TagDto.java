package com.example.flow.dtosop;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDto implements Serializable {
    Long id;
    String name;
}