package com.jwj.dao;

import com.jwj.pojo.Book;

import java.util.List;

public interface BookMapper {

    //query All book
    List<Book> queryAllBook();

    //add a new book
    int addBook(Book book);

    //get book author by its name
    String getAuthorByName(String bookName);

    //check whether this book is in db
    int checkBookExist(String bookName);

    //delete a book by BookId
    int deleteBookById(int bookId);

    //update a book
    int updateBook(Book book);

    //query book by id
    Book queryBookById(int bookId);

    //query book by name
    List<Book> queryBookByName(String bookName);
}

