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


@WebServlet("/brewerycreate")
public class BreweriesCreate extends HttpServlet {
	
	protected BreweriesDao breweriesDao;
	
	@Override
	public void init() throws ServletException {
		breweriesDao = BreweriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/BreweriesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String breweryId = req.getParameter("breweryId");
        
        if (breweryId == null || breweryId.trim().isEmpty()) {
            messages.put("success", "Invalid Brewery Id");
        } else {
        	// Create the BlogUser.
        	String breweryName = req.getParameter("breweryname");
        	String breweryCountry = req.getParameter("brewerycountry");
        	String breweryState = req.getParameter("brewerystate");
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Breweries brewery = new Breweries(breweryId, breweryName, breweryCountry, breweryState);
	        	brewery = breweriesDao.create(brewery);
	        	messages.put("success", "Successfully created " + breweryId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BreweriesCreate.jsp").forward(req, resp);
    }
}
