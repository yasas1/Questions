package com.example.userroleauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse<T> {
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private List<T> content;
}
