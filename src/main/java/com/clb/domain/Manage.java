package com.clb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manage {

    private String adminId;
    private String isbn;
    private Date manageDate;
    private String operation;
}
