package com.clb.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrow {
    @TableId
    private Integer id;
    private String isbn;
    @TableField("reader_id")
    private String readerId;
    private Date borrowDate;
    private Date returnDate;
    private Date dueDate;
}
