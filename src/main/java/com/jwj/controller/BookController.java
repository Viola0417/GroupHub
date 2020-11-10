package com.jwj.controller;

import com.jwj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;
}
