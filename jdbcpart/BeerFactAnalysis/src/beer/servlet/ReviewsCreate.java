package beer.servlet;

import beer.dal.*;
import beer.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewscreate")
public class ReviewsCreate extends HttpServlet {
	
	protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ReviewsCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        // String reviewId = req.getParameter("reviewId");
        String userName = req.getParameter("username");
        String beerId = req.getParameter("beerId");
        
	    if (beerId == null || beerId.trim().isEmpty()) {
            messages.put("success", "Invalid Beer Id");
        } else if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid User Name");
        }else {
        	// Create the BlogUser.
        	
            String content = req.getParameter("content");
	        try {
	        	Beers beer = BeersDao.getInstance().getBeerByBeerId(beerId);
	        	Users user = UsersDao.getInstance().getUserByUserName(userName);
	        	// Exercise: parse the input for StatusLevel.
	        	Reviews review = new Reviews(new Date(), content, beer, user);
	        	// BlogUsers blogUser = new BlogUsers(userName, firstName, lastName, dob, BlogUsers.StatusLevel.novice);
	        	review = reviewsDao.create(review);
	        	//messages.put("success", "Successfully created " + reviewId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewsCreate.jsp").forward(req, resp);
    }
}
