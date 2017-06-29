package com.tutorialspoint.stateless.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tutorialspoint.stateless.LibrarySessionBeanRemote;

public class EJBTester {
	BufferedReader brConsoleReader = null; 
	Properties jndiProperties;
	InitialContext ctx;
	{
		jndiProperties = new Properties();
		jndiProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
		jndiProperties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		jndiProperties.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		try {
			ctx = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		EJBTester ejbTester = new EJBTester();
		ejbTester.testStatelessEjb();
	}
	
	private void showGUI() {
		System.out.println("**********************");
		System.out.println("Welcome to Book Store");
		System.out.println("**********************");
		System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
	}

	private void testStatelessEjb() {
		try {
			int choice = 1;
			LibrarySessionBeanRemote libraryBean = (LibrarySessionBeanRemote) ctx.lookup("java:global/TP_EJBComponent/LibrarySessionBean!com.tutorialspoint.stateless.LibrarySessionBeanRemote");
			while (choice != 2) {
				String bookName;
				showGUI();
				String strChoice = brConsoleReader.readLine();
				choice = Integer.parseInt(strChoice);
				if (choice == 1) {
					System.out.print("Enter book name: ");
					bookName = brConsoleReader.readLine();
					libraryBean.addBook(bookName);
				} else if (choice == 2) {
					break;
				}
			}
			List<String> booksList = libraryBean.getBooks();
			System.out.println("Book(s) entered so far: " + booksList.size());
			for (int i = 0; i < booksList.size(); ++i) {
				System.out.println((i + 1) + ". " + booksList.get(i));
			}
			LibrarySessionBeanRemote libraryBean1 = (LibrarySessionBeanRemote) ctx.lookup("java:global/TP_EJBComponent/LibrarySessionBean!com.tutorialspoint.stateless.LibrarySessionBeanRemote");
			List<String> booksList1 = libraryBean1.getBooks();
			System.out.println("***Using second lookup to get library stateless object***");
			System.out.println("Book(s) entered so far: " + booksList1.size());
			for (int i = 0; i < booksList1.size(); ++i) {
				System.out.println((i + 1) + ". " + booksList1.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (brConsoleReader != null) {
					brConsoleReader.close();
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
