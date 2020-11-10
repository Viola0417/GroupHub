package com.jwj.service;

import com.jwj.dao.BookMapper;
import com.jwj.pojo.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    //query All book
    public List<Book> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    //add a new book
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    //get book author by its name
    public String getAuthorByName(String bookName) {
        return bookMapper.getAuthorByName(bookName);
    }

    //check whether this book is in db
    public boolean checkBookExist(String bookName) {
        return bookMapper.checkBookExist(bookName) > 0;
    }

    //delete a book by BookId
    public int deleteBookById(int bookId) {
        return bookMapper.deleteBookById(bookId);
    }

    //update a book
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    //query book by id
    public Book queryBookById(int bookId) {
        return bookMapper.queryBookById(bookId);
    }

    //query book by name
    public List<Book> queryBookByName(String bookName) {
        return bookMapper.queryBookByName(bookName);
    }
}
