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


@WebServlet("/breweryupdate")
public class BreweryUpdate extends HttpServlet {
	
	protected BreweriesDao breweryDao;
	
	@Override
	public void init() throws ServletException {
		breweryDao = BreweriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String breweryId = req.getParameter("breweryId");
        if (breweryId == null || breweryId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid BreweryId.");
        } else {
        	try {
        		Breweries brewery = breweryDao.getBreweryById(breweryId);
        		if(brewery == null) {
        			messages.put("success", "BreweryId does not exist.");
        		}
        		req.setAttribute("brewery", brewery);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BreweriesUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String breweryId = req.getParameter("breweryId");
        if (breweryId == null || breweryId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid BreweryId.");
        } else {
        	try {
        		Breweries brewery = breweryDao.getBreweryById(breweryId);
        		if(brewery == null) {
        			messages.put("success", "BreweryId does not exist.");
        		} else {
        			
        			//BreweryId VARCHAR(255) BreweryName VARCHAR(255) BreweryCountry VARCHAR(255) BreweryState VARCHAR(255)
        			
        			
        			String newBreweryName = req.getParameter("breweryname");
        			String newBreweryCountry = req.getParameter("brewerycountry");
        			String newBreweryState = req.getParameter("brewerystate");
        			
        			if (newBreweryName == null || newBreweryName.trim().isEmpty()) {
        				newBreweryName = brewery.getBreweryName();
        	        } 
        			if (newBreweryCountry == null || newBreweryCountry.trim().isEmpty()) {
        				newBreweryCountry = brewery.getBreweryCountry();
        	        } 
        			if (newBreweryState == null || newBreweryState.trim().isEmpty()) {
        				newBreweryState = brewery.getBreweryState();
        	        } 
        			brewery = breweryDao.updateBreweries(brewery, newBreweryName, newBreweryCountry, newBreweryState);
        			messages.put("success", "Successfully updated " + breweryId);
        	        
        		}
        		req.setAttribute("brewery", brewery);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BreweriesUpdate.jsp").forward(req, resp);
    }
}
