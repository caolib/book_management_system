package com.clb.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BorrowVo implements Serializable {
    private String bookName;
    private String isbn;
    private Integer readerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date borrowDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    //true归还，false未归还
    private Boolean status;
}
