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


@WebServlet("/beerupdate")
public class BeerUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String beerId = req.getParameter("beerId");
        if (beerId == null || beerId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid BeerId.");
        } else {
        	try {
        		Beers beer = beerDao.getBeerByBeerId(beerId);
        		if(beer == null) {
        			messages.put("success", "BeerId does not exist.");
        		}
        		req.setAttribute("beer", beer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BeerUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String beerId = req.getParameter("beerId");
        if (beerId == null || beerId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid BeerId.");
        } else {
        	try {
        		Beers beer = beerDao.getBeerByBeerId(beerId);
        		if(beer == null) {
        			messages.put("success", "BeerId does not exist. No update to perform.");
        		} else {
        			String newBeerName = req.getParameter("beername");
        			String newCountry = req.getParameter("beercountry");
        			String newState = req.getParameter("beerstate");
        			String newABV = req.getParameter("beerabv");
        			// String newBreweryId = req.getParameter("breweryId");
        			double newBeerABV = 0;
        			
        			if (newBeerName == null || newBeerName.trim().isEmpty()) {
        	            newBeerName = beer.getBeerName();
        	        }
        			if (newCountry == null || newCountry.trim().isEmpty()) {
        				newCountry = beer.getBeerCountry();
        	        }
        			if (newState == null || newState.trim().isEmpty()) {
        				newState = beer.getBeerState();
        	        }
        			if (newABV == null || newABV.trim().isEmpty()) {
        				newBeerABV = beer.getBeerABV();
        	        } else {
        	        	newBeerABV = Double.parseDouble(newABV);
        	        }
        			beer = beerDao.updateBeer(beer, newBeerName, newCountry, newState, newBeerABV);
        			messages.put("success", "Successfully updated " + beerId);
        		}
        		req.setAttribute("beer", beer);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BeerUpdate.jsp").forward(req, resp);
    }
}
