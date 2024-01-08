package com.clb.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrow {
  private String isbn;
  @TableField("reader_id")
  private String readerId;
  private Date borrowDate;
  private Date returnDate;
  private Date dueDate;
}
