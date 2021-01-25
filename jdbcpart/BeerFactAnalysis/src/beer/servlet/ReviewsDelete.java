package beer.servlet;

import beer.dal.*;
import beer.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewsdelete")
public class ReviewsDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Review");        
        req.getRequestDispatcher("/ReviewsDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reviewId = req.getParameter("reviewId");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("title", "Invalid reviewId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Reviews review = new Reviews(Integer.parseInt(reviewId));
	        try {
	        	review = reviewDao.delete(review);
	        	// Update the message.
		        if (review == null) {
		            messages.put("title", "Successfully deleted " + reviewId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + reviewId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewsDelete.jsp").forward(req, resp);
    }
}
