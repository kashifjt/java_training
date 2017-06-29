package com.tutorialspoint.stateless;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class LibrarySessionBean
 */
@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {
	List<String> bookShelf;
    /**
     * Default constructor. 
     */
    public LibrarySessionBean() {
    	bookShelf = new ArrayList<String>();
    }

	@Override
	public void addBook(String bookName) {
		bookShelf.add(bookName);
		
	}

	@Override
	public List<String> getBooks() {
		return bookShelf;
	}

}
