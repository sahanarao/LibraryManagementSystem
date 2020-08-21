package com.libmgmtsys.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libmgmtsys.model.Book;
import com.libmgmtsys.model.Borrow;
import com.libmgmtsys.repository.BookRepository;
import com.libmgmtsys.repository.BorrowRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "searchController", description = "This enpoints returns books, makesBooking, cancellation")
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;


    @RequestMapping(value = "/getBooks", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "to get total number of books in library", response = List.class)
    public List<Book> getBooks() {
    	logger.info("Get Books");
        List<Book> li = new ArrayList<Book>();
        bookRepository.findAll().forEach(li::add);
        return li;
    }

    @RequestMapping(value = "/getBorrowDetails", method = RequestMethod.POST,
            produces = "application/json")
    @ApiOperation(value = "to get total number of books borrowed", response = List.class)
    public List<Borrow> getBookingDetails(@RequestBody String userId) {
    	logger.info("getBookingDetails invoked");
        List<Borrow> li = new ArrayList<Borrow>();        
        List<Object[]> res =borrowRepository.findByUSerId(userId);
		Iterator<Object[]> it = res.iterator();
		while(it.hasNext()){
			Borrow borrow = new Borrow();
			Object[] line = it.next();			
			borrow.setBorrow_id((String) line[0]);
			borrow.setbook_id((String) line[1]);
			borrow.setBooking_date((Date) line[2]);
			borrow.setQuantity((int) line[3]);
			li.add(borrow);
		}
        return li;
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "to add new book in library", response = String.class)
    public void addBooks(@RequestBody List<Book> books) {
    	logger.info("addBooks invoked");
        bookRepository.saveAll(books);
    }

    @RequestMapping(value = "/delBook", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "to delete book from library", response = String.class)
    public void delBooks(@RequestBody List<Book> books) {
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
        borrowRepository.deleteByBorrowerId(bookingDetails);
    }

}
