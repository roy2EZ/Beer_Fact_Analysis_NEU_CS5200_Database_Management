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


@WebServlet("/beerdelete")
public class BeersDelete extends HttpServlet {
	
	protected BeersDao beerDao;
	
	@Override
	public void init() throws ServletException {
		beerDao = BeersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Beer");        
        req.getRequestDispatcher("/BeerDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        //String beerName = req.getParameter("beerName");
        String beerId = req.getParameter("beerId");
        if (beerId == null || beerId.trim().isEmpty()) {
            messages.put("title", "Invalid BeerId");
            messages.put("disableSubmit", "true");
        } else {
	        Beers beer = new Beers(beerId);
	        try {
	        	beer = beerDao.delete(beer);
	        	// Update the message.
		        if (beer == null) {
		            messages.put("title", "Successfully deleted " + beerId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + beerId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }
}
