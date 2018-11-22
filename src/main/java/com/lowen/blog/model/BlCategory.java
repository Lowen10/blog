package com.lowen.blog.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlCategory {
    
    private Integer id;

    private String category;

    private Integer categoryId;

    private Integer articleCount;

}