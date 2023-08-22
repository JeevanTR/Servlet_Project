package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;

@WebServlet("/login")
@MultipartConfig
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Receive values from Front-End
		String Username = req.getParameter("username");
		String Password = req.getParameter("password");

		// Verify if email exists
		MyDao dao = new MyDao();
		if (Username.equals("admin@jsp.com") && Password.equals("admin")) {
			resp.getWriter().print("<h1>Admin Logged In Successfully</h1>");

			// This is logic to send to next page
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		} else {
			Customer customer = dao.fetchByEmail(Username);
			if (customer == null) {
				resp.getWriter().print("<h1 style='color:red'>Invalid Username</h1>");
				// going to the required page
				req.getRequestDispatcher("login.html").include(req, resp);
			} else {
				if (Password.equals(customer.getPassword())) {
					resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
					// going to the required page
					req.getRequestDispatcher("customerhome.html").include(req, resp);
				} else {
					resp.getWriter().print("<h1 style='color:red'>Invalid Password</h1>");
					// going to the required page
					req.getRequestDispatcher("login.html").include(req, resp);
				}
			}
		}
	}
}
