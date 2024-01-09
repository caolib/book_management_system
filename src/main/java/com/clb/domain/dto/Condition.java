package com.clb.domain.dto;

import lombok.Data;

@Data
public class Condition {
    private String bookName;
    private String author;
    private String ISBN;
    private Integer number;
    private Integer currentPage;
    private Integer pageSize;
}
