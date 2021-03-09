package com.dmitriyabdurazakov.springboot.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageRequestProvider {

    public Pageable getPageRequest(int pageNum, int elementCount, String sortBy) {
        return PageRequest.of(pageNum, elementCount, Sort.by(sortBy));
    }

    public Pageable getFirstPageWithTwoElements() {
        return PageRequest.of(1, 2);
    }

    public Pageable getSecondPageWithFourElements() {
        return PageRequest.of(2,4);
    }

    public Pageable getFirstPageWithTwoElementsSortedByName() {
        return PageRequest.of(1, 2, Sort.by("name"));
    }

    public Pageable getSecondPageWithFourElementsSortedById() {
        return PageRequest.of(2, 4, Sort.by("id"));
    }
}
