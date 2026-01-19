package com.rich.nf_control.core.application.shared.pagination;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private final List<T> items;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
}