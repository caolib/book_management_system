package com.clb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 书籍类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book implements Serializable {
    @TableId
    private String isbn;
    private String title;
    private String cover;
    private String introduction;
    private Integer number;
    private String author;
}
