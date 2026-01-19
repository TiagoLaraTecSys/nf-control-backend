package com.rich.nf_control.adapter.input.http.shared;

import com.rich.nf_control.core.application.shared.pagination.PageResult;

import java.util.List;
import java.util.function.Function;

public record PageResponse<T>(
        int page,
        int size,
        long totalElements,
        int totalPages,
        List<T> items
) {

    public static <T, R> PageResponse<R> from(
            PageResult<T> page,
            Function<T, R> mapper
    ) {
        return new PageResponse<>(
                page.getPage(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getItems()
                        .stream()
                        .map(mapper)
                        .toList()
        );
    }
}