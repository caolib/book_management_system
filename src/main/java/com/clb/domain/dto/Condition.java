package com.clb.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Condition implements Serializable {
    private String bookName;
    private String author;
    private String isbn;
    private Integer number;
    private Integer currentPage;
    private Integer pageSize;
}
