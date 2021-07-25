package com.lms.serviceimpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lms.dao.BookDAO;
import com.lms.daoimpl.BookDAOImpl;
import com.lms.entity.Book;
import com.lms.model.BookOwned;
import com.lms.model.dtos.BookOwnedDto;
import com.lms.service.BookService;

public class BookServiceImpl implements BookService{

	private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());

	private BookDAO bookDao=new BookDAOImpl();

	@Override
	public boolean save(Book book) throws SQLException {

		return bookDao.save(book);
	}

	@Override
	public List<Book> findAll() throws SQLException {

		return bookDao.findAll();
	}

	@Override
	public Book get(int id) throws SQLException{

		return bookDao.get(id);
	}

	@Override
	public boolean update(Book book) throws SQLException {

		return bookDao.update(book);
	}

	@Override
	public boolean delete(int id) throws SQLException{

		return bookDao.delete(id);
	}

	@Override
	public List<BookOwnedDto> getBookOwnedByUser(int userId) throws SQLException {
		List<BookOwnedDto> bookOwnedDtos=new ArrayList<>();
		List<BookOwned> bookOwned=bookDao.getBookOwnedByUser(userId);
		bookOwned.parallelStream().forEach(bookowned->{
			BookOwnedDto bookOwnedDto=new BookOwnedDto();
			try {
				Long remainingDays=bookowned.getBookTakenFor()-getDays(bookowned.getBookTakenAt());
				if(bookowned.getBookOwnedStatus().equals("Onloan")) {
					System.out.print("fine   "+bookowned.getFine());
					bookOwnedDto.setFineCalibrated(String.valueOf(calibrateFine(remainingDays,bookowned.getFine())));
					bookOwnedDto.setRemainingDays(remainingDays);	
				}
				
			} catch (ParseException e) {
				LOGGER.info("Date cannot be Parsed");
			}
			bookOwnedDto.setBookUserId(bookowned.getId());
			bookOwnedDto.setUserId(bookowned.getUserId());
			bookOwnedDto.setBookOwnedStatus(bookowned.getBookOwnedStatus());
			bookOwnedDto.setBookId(bookowned.getBookId());
			bookOwnedDto.setBookAuthor(bookowned.getBookAuthor());
			bookOwnedDto.setBookTitle(bookowned.getBookTitle());
			bookOwnedDto.setBookTakenAt(bookowned.getBookTakenAt());
			bookOwnedDto.setBookTakenFor(bookowned.getBookTakenFor());
			bookOwnedDtos.add(bookOwnedDto);
		});
		return bookOwnedDtos;
	}

	public long getDays(String date) throws ParseException {
		String replace = date.replace('-', '/');
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.parse(replace,formatter);
		LocalDate secondDate = LocalDate.now();
		Period period=Period.between(localDate, secondDate);
		return period.getDays();
	}
	
	private float calibrateFine(long remainingDays,float fine) {
		return remainingDays>0?0 :(Math.abs(remainingDays))*fine;
		
	}

	@Override
	public Book getByBookTitle(String title) throws SQLException {
		
		return bookDao.getByBookTitle(title);
	}


}
