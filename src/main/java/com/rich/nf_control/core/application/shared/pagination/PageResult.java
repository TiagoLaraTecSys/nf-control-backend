package com.rich.nf_control.core.application.shared.pagination;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResult<T> {

    private final List<T> items;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
}