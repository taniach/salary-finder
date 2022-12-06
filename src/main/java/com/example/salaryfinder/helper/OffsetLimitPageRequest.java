package com.example.salaryfinder.helper;

import com.example.salaryfinder.domain.CustomSortOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetLimitPageRequest implements Pageable {
    private final long offset;
    private final long limit;
    private final Sort sort;

    public OffsetLimitPageRequest(long offset, long limit, CustomSortOrder sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset must not be less than 0");
        }

        if (limit < 0) {
            throw new IllegalArgumentException("Limit must not be less than 0");
        }

        this.offset = offset;
        this.limit = limit;
        this.sort = sort == null ? Sort.unsorted() : Sort.by(sort.name().toLowerCase());
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return (int) limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return this;
    }

    @Override
    public Pageable first() {
        return this;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
