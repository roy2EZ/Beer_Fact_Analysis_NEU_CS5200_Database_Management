package beer.servlet;

import beer.dal.*;
import beer.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewsupdate")
public class ReviewUpdate extends HttpServlet {
	
	protected ReviewsDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String reviewId = req.getParameter("reviewId");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Review Id.");
        } else {
        	try {
        		Reviews review = reviewDao.getReviewByReviewId(Integer.parseInt(reviewId));// reviewId);
        		if(review == null) {
        			messages.put("success", "review id does not exist.");
        		}
        		req.setAttribute("review", review);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewsUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String reviewId = req.getParameter("reviewId");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Review Id.");
        } else {
        	try {
        		Reviews review = reviewDao.getReviewByReviewId(Integer.parseInt(reviewId));// reviewId);
        		if(review == null) {
        			messages.put("success", "review id does not exist.");
        		} else {
        			// ReviewId INT
        			// ReviewDate DATE
        			// ReviewText LONGTEXT
        			// BeerId VARCHAR(255) (FK) UserName VARCHAR(255) (FK)
        			String newReviewText = req.getParameter("reviewtext");
        			Date newDate = new Date();
        			
        			if (newReviewText == null || newReviewText.trim().isEmpty()) {
        				newReviewText = review.getReviewText();
        	        }
        			// updateReview(Reviews review, Date newReviewDate, String newReviewText)
        			/*
        			if (newLastName == null || newLastName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	blogUser = blogUsersDao.updateLastName(blogUser, newLastName);
        	        	messages.put("success", "Successfully updated " + userName);
        	        }
        	        */
        			review = reviewDao.updateReview(review, newDate, newReviewText);
        			
        		}
        		req.setAttribute("review", review);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewsUpdate.jsp").forward(req, resp);
    }
}
