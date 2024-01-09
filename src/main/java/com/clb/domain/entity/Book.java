package com.clb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @TableId
    private String isbn;
    private String title;
    private String cover;
    private String introduction;
    private Integer number;
    private String author;
}
