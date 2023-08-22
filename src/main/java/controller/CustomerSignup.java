package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MyDao;
import dto.Customer;


@WebServlet("/signup")
@MultipartConfig
public class CustomerSignup extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String Name=req.getParameter("name");
	System.out.println(Name);
	String Password=req.getParameter("password");
	System.out.println(Password);
	long Mobile=Long.parseLong(req.getParameter("mobile"));
	System.out.println(Mobile);
	String Email=req.getParameter("email");
	System.out.println(Email);
	String gender=req.getParameter("gender");
	System.out.println(gender);
	String country=req.getParameter("country");
	System.out.println(country);
	LocalDate dob=LocalDate.parse(req.getParameter("dob"));
	System.out.println(dob);
	int age=Period.between(dob,LocalDate.now()).getYears();
	
	//Logic to Receive image and convert to byte[]
	Part Picture=req.getPart("picture");
	byte[] picture=null;
	picture=new byte[Picture.getInputStream().available()];
	Picture.getInputStream().read(picture);
	
	MyDao dao=new MyDao();
	
	//Logic to verify email and mobile is not repeated
	if(dao.fetchByEmail(Email)==null && dao.fetchByMobile(Mobile)==null)
	{
	//loading values inside object
	Customer customer=new Customer();
	customer.setAge(age);
	customer.setCountry(country);
	customer.setDob(dob);
	customer.setEmail(Email);
	customer.setGender(gender);
	customer.setMobile(Mobile);
	customer.setName(Name);
	customer.setPassword(Password);
	customer.setPicture(picture);
	
	//Persisting
	dao.save(customer);
	
	resp.getWriter().print("<h1 style='color:green'>Account Created Succesfully</h1>");
	//going to the required page
	req.getRequestDispatcher("login.html").include(req, resp);
	}else {
		resp.getWriter().print("<h1 style='color:red'>Email and Mobile should be Unique</h1>");
		//going to the required page
		req.getRequestDispatcher("signup.html").include(req, resp);
	}
}
}
