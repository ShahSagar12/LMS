package com.lms.serviceimpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.lms.dao.UserDAO;
import com.lms.daoimpl.RoleDAOImpl;
import com.lms.daoimpl.UserDAOImpl;
import com.lms.entity.User;
import com.lms.model.BookRequest;
import com.lms.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class.getName());
	UserDAO userDao=new UserDAOImpl();

	@Override
	public boolean register(User user) throws SQLException {
		
		return userDao.save(user);
	}

	@Override
	public User isAuthenticated(String email, String password) {
		List<User> users=new ArrayList<>();
		try {
			users= userDao.findAll().stream().filter(user->user.getEmail().equalsIgnoreCase(email)).filter(u->u.getUserPassword().equalsIgnoreCase(password)).collect(Collectors.toList());
		} catch (SQLException e) {
			LOGGER.info("Error :"+e.getMessage());
		}
		if(users.size()>0) {
			return users.get(0);	
		}
		return null;
		
	}

	@Override
	public List<User> findAll() throws SQLException {
		
		return userDao.findAll();
	}

	@Override
	public User findById(int id) throws SQLException {
		
		return userDao.get(id);
	}

	@Override
	public User getByEmail(String email) throws SQLException {
		
		return userDao.getByEmail(email);
	}

	@Override
	public boolean update(User user) throws SQLException {
		
		return userDao.update(user);
	}

	@Override
	public boolean delete(int id) throws SQLException {
		
		return userDao.delete(id);
	}

	@Override
	public List<BookRequest> getBookOwnerByOfAdmin(int adminId) throws SQLException {
		List<BookRequest> bookRequests=new ArrayList<>();
		userDao.getBookOwnerByOfAdmin(adminId).parallelStream().forEach(bookRequestDtos->{
			BookRequest bookRequest=new BookRequest();
			try {
				long remainingDays=bookRequestDtos.getBookTakenFor()-getDays(bookRequestDtos.getBookTakenAt());
				if(bookRequestDtos.getBookStatus().equals("Onloan")) {
					System.out.print("fine   "+bookRequestDtos.getFine());
					bookRequest.setCalibratedFine(String.valueOf(calibrateFine(remainingDays,bookRequestDtos.getFine())));
					bookRequest.setRemainingDays(remainingDays);	
				}
			
			} catch (ParseException e) {
				LOGGER.info("Date cannot be Parsed");
			}
			bookRequest.setBookStatus(bookRequestDtos.getBookStatus());
			bookRequest.setStudentName(bookRequestDtos.getStudentName());
			bookRequest.setBookUserId(bookRequestDtos.getBookUserId());
			bookRequest.setBookAuthor(bookRequestDtos.getBookAuthor());
			bookRequest.setBookTitle(bookRequestDtos.getBookTitle());
			bookRequest.setBookTakenAt(bookRequestDtos.getBookTakenAt());
			bookRequest.setBookTakenFor(bookRequestDtos.getBookTakenFor());
			bookRequests.add(bookRequest);
		});
		return bookRequests;
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

	
	

}
