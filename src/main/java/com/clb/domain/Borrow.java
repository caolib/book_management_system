package com.clb.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * 借阅记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrow implements Serializable {
    @TableId
    private Integer id;
    private String isbn;
    @TableField("reader_id")
    private String readerId;
    private Date borrowDate;
    private Date returnDate;
    private Date dueDate;
}
