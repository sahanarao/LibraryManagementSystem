package com.libmgmtsys.controller;

import com.libmgmtsys.model.Book;
import com.libmgmtsys.model.Borrow;
import com.libmgmtsys.repository.BookRepository;
import com.libmgmtsys.repository.BorrowRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "searchController", description = "This enpoints returns books, makesBooking, cancellation")
public class LibmgmtsysController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;


    @RequestMapping(value = "/getBooks", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "to get total number of books in library", response = List.class)
    public List<Book> getBooks() {
        List<Book> li = new ArrayList<Book>();
        bookRepository.findAll().forEach(li::add);
        return li;
    }

    @RequestMapping(value = "/getBorrowDetails", method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(value = "to get total number of books borrowed", response = List.class)
    public List<Borrow> getBookingDetails() {
        List<Borrow> li = new ArrayList<Borrow>();
        borrowRepository.findAll().forEach(li::add);
        return li;
    }


    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "to get count of books", response = Long.class)
    public long countNoofBooks() {
        return bookRepository.count();
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "to add new book in library", response = String.class)
    public void addBooks(@RequestBody List<Book> books) {
        System.out.println(books);
        bookRepository.saveAll(books);


    }

    @RequestMapping(value = "/delBook", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "to delete book from library", response = String.class)
    public void delBooks(@RequestBody List<Book> books) {
        System.out.println(books);
        bookRepository.deleteAll(books);


    }

    @RequestMapping(value = "/borrowbooks", method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(value = "to make booking from library", response = String.class)
    public void makeBooking(@RequestBody Borrow orderDetails) {
        borrowRepository.save(orderDetails);


    }

    @RequestMapping(value = "/cancelBorrowing", method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(value = "to cancel booking from library", response = String.class)
    public void cancelBooking(@RequestBody String bookingDetails) {
        System.out.println(bookingDetails.split(":")[0]);
        borrowRepository.deleteByBorrowerId(bookingDetails);


    }

}
