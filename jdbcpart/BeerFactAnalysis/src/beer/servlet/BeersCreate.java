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


@WebServlet("/beercreate")
public class BeersCreate extends HttpServlet {
	
	protected BeersDao beersDao;
	
	@Override
	public void init() throws ServletException {
		beersDao = BeersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/BeerCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String beerId = req.getParameter("beerId");
        if (beerId == null || beerId.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	String beerName = req.getParameter("beername");
        	String beerCountry = req.getParameter("beercountry");
        	String beerState = req.getParameter("beerstate");
        	String beerABV = req.getParameter("beerabv");
        	String breweryId = req.getParameter("breweryId");
        	Breweries brewery;
        	try {
        		brewery = BreweriesDao.getInstance().getBreweryById(breweryId);
        	} catch (SQLException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	
	        try {
	        	Beers beer = new Beers(beerId, beerName, beerCountry, beerState, Double.parseDouble(beerABV), brewery);
	        	messages.put("success", "Successfully created " + beerName);
	        	beer = beersDao.create(beer);
	        	messages.put("success", "Successfully created " + beerId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BeerCreate.jsp").forward(req, resp);
    }
}
