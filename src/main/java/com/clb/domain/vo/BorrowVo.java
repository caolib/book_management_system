package com.clb.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class BorrowVo implements Serializable {
    private Integer id;
    private String bookName;
    private String isbn;
    private Integer readerId;
    private Date borrowDate;
    private Date returnDate;
    private Date dueDate;
    //true归还，false未归还
    private Boolean status;
}
