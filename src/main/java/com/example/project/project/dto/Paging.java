package com.example.project.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
    private Integer page;
    private Integer size;
    private Integer total;
}
