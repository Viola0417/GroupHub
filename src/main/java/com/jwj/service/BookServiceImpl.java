package com.jwj.service;

import com.jwj.dao.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }
}
