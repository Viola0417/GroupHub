package com.jwj.controller;

import com.jwj.pojo.Book;
import com.jwj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //controller layer invokes service layer
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //show all books;
    @RequestMapping("/toBook")
    public String toBook(Model model) {
        List<Book> bookList = bookService.queryAllBook();
        model.addAttribute("bookList", bookList);
        System.out.println("admin query book!");
        for (Book b: bookList) {
            System.out.println(b.toString());
        }
        return "adminBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddBook(Model model) {
        return "adminAddBook";
    }

    @RequestMapping("/addBook")
    public String addBook(@RequestParam("bookName") String bookName, @RequestParam("bookAuthor") String bookAuthor,
                           @RequestParam("bookDescription") String bookDescription, Model model) {
        System.out.println("name => " + bookName + ", author => " + bookAuthor + ", description => " + bookDescription);

        // if this book has been added(same name and same author)
        //prompt error msg
        String errorMsg = "";
        String msg = "";
        String thisAuthor = bookService.getAuthorByName(bookName);
        if (bookService.checkBookExist(bookName) && (thisAuthor.equalsIgnoreCase(bookAuthor))) {
            errorMsg = "This book has been added before!";
            model.addAttribute("errorMsg", errorMsg);
            return "adminAddBook";
        }
        //else add to database
        bookService.addBook(new Book(bookName, bookAuthor, bookDescription));
        msg = "Successfully add book, return";
        model.addAttribute("msg", msg);
        return "adminAddBook";
    }

    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int bookId, Model model) {
        System.out.println("bookId => " + bookId);
        Book book = bookService.queryBookById(bookId);
        //System.out.println(movie.toString());
        model.addAttribute("queryBook", book);
        return "adminUpdateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        Book originalBook = bookService.queryBookById(book.getBookId());
        book.setTotalRateNumber(originalBook.getTotalRateNumber());
        book.setTotalRateScore(originalBook.getTotalRateScore());
        bookService.updateBook(book);
        return "redirect:/book/toBook";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(int bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/book/toBook";
    }

    @RequestMapping("/toUserBook")
    public String toUserBook(Model model) {
        List<Book> bookList = bookService.queryAllBook();
        model.addAttribute("bookList", bookList);
        return "userBook";
    }

    @RequestMapping("/userQueryBook")
    public String userQueryBookByName(@RequestParam("queryBookName") String bookName, Model model) {
        List<Book> book = bookService.queryBookByName(bookName);
        model.addAttribute("bookList", book);
        return "userBook";
    }
}

