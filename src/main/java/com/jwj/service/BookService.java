package com.jwj.service;

import com.jwj.pojo.Book;

import java.util.List;

public interface BookService {

    //query All book
    List<Book> queryAllBook();

    //add a new book
    int addBook(Book movie);

    //get book author by its name
    String getAuthorByName(String bookName);

    //check whether this book is in db
    boolean checkBookExist(String bookName);

    //delete a book by bookId
    int deleteBookById(int bookId);

    //update a book
    int updateBook(Book book);

    //query book by id
    Book queryBookById(int bookId);

    //query book by name
    List<Book> queryBookByName(String bookName);
}