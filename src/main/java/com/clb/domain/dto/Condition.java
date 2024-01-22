package com.clb.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询条件
 */
@Data
public class Condition implements Serializable {
    private String bookName;
    private String author;
    private String isbn;
    private Integer number;
    private Integer currentPage;
    private Integer pageSize;
}
