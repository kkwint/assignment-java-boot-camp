package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class BrandDTO {

    private Integer id;
    private String name;
    private String description;
    private Date createdAt;
}
