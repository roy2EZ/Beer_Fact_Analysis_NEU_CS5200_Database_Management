package beer.tools;

import beer.dal.*;
import beer.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		BreweriesDao breweriesDao = BreweriesDao.getInstance();
		BeersDao beersDao = BeersDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		SmellsDao smellsDao = SmellsDao.getInstance();
		LooksDao looksDao = LooksDao.getInstance();
		FeelsDao feelsDao = FeelsDao.getInstance();
		TastesDao tastesDao = TastesDao.getInstance();
		OverallsDao overallsDao = OverallsDao.getInstance();
		ScoresDao scoresDao = ScoresDao.getInstance();
		
		// INSERT.
		System.out.println("****************************************INSERT****************************************");
		Users user1 = new Users("Tom", 1000);
		user1 = usersDao.create(user1);
		System.out.format("Inserting user: userName:%s userId:%s \n", user1.getUserName(), user1.getUserId());
		Users user2 = new Users("Jack", 1001);
		user2 = usersDao.create(user2);
		System.out.format("Inserting user: userName:%s userId:%s \n", user2.getUserName(), user2.getUserId());
		Users user3 = new Users("Steve", 1002);
		user3 = usersDao.create(user3);
		System.out.format("Inserting user: userName:%s userId:%s \n", user3.getUserName(), user3.getUserId());
		
		Breweries brewery1 = new Breweries("101", "First Brewery", "US", "WA");
		brewery1 = breweriesDao.create(brewery1);
		System.out.format("Inserting brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
			brewery1.getBreweryId(), brewery1.getBreweryName(), brewery1.getBreweryCountry(), 
			brewery1.getBreweryState());
		Breweries brewery2 = new Breweries("102", "Blue Brewery", "US", "CA");
		brewery2 = breweriesDao.create(brewery2);
		System.out.format("Inserting brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
			brewery2.getBreweryId(), brewery2.getBreweryName(), brewery2.getBreweryCountry(), 
			brewery2.getBreweryState());
		Breweries brewery3 = new Breweries("103", "Red Brewery", "US", "WA");
		brewery3 = breweriesDao.create(brewery3);
		System.out.format("Inserting brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
			brewery3.getBreweryId(), brewery3.getBreweryName(), brewery3.getBreweryCountry(), 
			brewery3.getBreweryState());
		
		Beers beer1 = new Beers("10000", "First Wine", "US", "WA", 45.1, brewery1);
		beer1 = beersDao.create(beer1);
		System.out.format("Inserting beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
			beer1.getBeerId(), beer1.getBeerName(), beer1.getBeerCountry(), beer1.getBeerState(), 
			beer1.getBeerABV(), beer1.getBrewery().getBreweryId());
		Beers beer2 = new Beers("10001", "Best Wine", "US", "WA", 23.0, brewery1);
		beer2 = beersDao.create(beer2);
		System.out.format("Inserting beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
			beer2.getBeerId(), beer2.getBeerName(), beer2.getBeerCountry(), beer2.getBeerState(), 
			beer2.getBeerABV(), beer2.getBrewery().getBreweryId());
		Beers beer3 = new Beers("10002", "Just Wine", "France", "Paris", 45.1, brewery2);
		beer3 = beersDao.create(beer3);
		System.out.format("Inserting beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
			beer3.getBeerId(), beer3.getBeerName(), beer3.getBeerCountry(), beer3.getBeerState(), 
			beer3.getBeerABV(), beer3.getBrewery().getBreweryId());
		
		Reviews review1 = new Reviews(new Date("08/04/2010"), "Very good1", beer1, user1);
		review1 = reviewsDao.create(review1);
		System.out.format("Inserting review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
			review1.getReviewId(), review1.getReviewDate().toString(), review1.getReviewText(), 
			review1.getBeer().getBeerId(), review1.getUser().getUserName());
		Reviews review2 = new Reviews(new Date("08/04/2010"), "Very good2", beer2, user1);
		review2 = reviewsDao.create(review2);
		System.out.format("Inserting review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
			review2.getReviewId(), review2.getReviewDate().toString(), review2.getReviewText(), 
			review2.getBeer().getBeerId(), review2.getUser().getUserName());
		Reviews review3 = new Reviews(new Date("08/04/2010"), "Very good3", beer1, user2);
		review3 = reviewsDao.create(review3);
		System.out.format("Inserting review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
			review3.getReviewId(), review3.getReviewDate().toString(), review3.getReviewText(), 
			review3.getBeer().getBeerId(), review3.getUser().getUserName());
		Reviews review4 = new Reviews(new Date("03/16/2016"), "Very good4", beer2, user2);
		review4 = reviewsDao.create(review4);
		System.out.format("Inserting review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
			review4.getReviewId(), review4.getReviewDate().toString(), review4.getReviewText(), 
			review4.getBeer().getBeerId(), review4.getUser().getUserName());
		
		Smells smell1 = new Smells("3.5", user1, beer1);
		smell1 = smellsDao.create(smell1);
		System.out.format("Inserting smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
			smell1.getSmellId(), smell1.getSmellScore(), smell1.getUser().getUserName(), smell1.getBeer().getBeerId());
		Smells smell2 = new Smells("4.0", user1, beer2);
		smell2 = smellsDao.create(smell2);
		System.out.format("Inserting smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
			smell2.getSmellId(), smell2.getSmellScore(), smell2.getUser().getUserName(), smell2.getBeer().getBeerId());
		Smells smell3 = new Smells("5.0", user2, beer1);
		smell3 = smellsDao.create(smell3);
		System.out.format("Inserting smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
			smell3.getSmellId(), smell3.getSmellScore(), smell3.getUser().getUserName(), smell3.getBeer().getBeerId());
		
		Looks look1 = new Looks("3.5", user1, beer1);
		look1 = looksDao.create(look1);
		System.out.format("Inserting look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
			look1.getLookId(), look1.getLookScore(), look1.getUser().getUserName(), look1.getBeer().getBeerId());
		Looks look2 = new Looks("4.0", user1, beer2);
		look2 = looksDao.create(look2);
		System.out.format("Inserting look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
			look2.getLookId(), look2.getLookScore(), look2.getUser().getUserName(), look2.getBeer().getBeerId());
		Looks look3 = new Looks("5.0", user2, beer1);
		look3 = looksDao.create(look3);
		System.out.format("Inserting look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
			look3.getLookId(), look3.getLookScore(), look3.getUser().getUserName(), look3.getBeer().getBeerId());
		
		Feels feel1 = new Feels("3.5", user1, beer1);
		feel1 = feelsDao.create(feel1);
		System.out.format("Inserting feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
			feel1.getFeelId(), feel1.getFeelScore(), feel1.getUser().getUserName(), feel1.getBeer().getBeerId());
		Feels feel2 = new Feels("4.0", user1, beer2);
		feel2 = feelsDao.create(feel2);
		System.out.format("Inserting feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
			feel2.getFeelId(), feel2.getFeelScore(), feel2.getUser().getUserName(), feel2.getBeer().getBeerId());
		Feels feel3 = new Feels("5.0", user2, beer1);
		feel3 = feelsDao.create(feel3);
		System.out.format("Inserting feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
			feel3.getFeelId(), feel3.getFeelScore(), feel3.getUser().getUserName(), feel3.getBeer().getBeerId());
		
		Tastes taste1 = new Tastes("3.5", user1, beer1);
		taste1 = tastesDao.create(taste1);
		System.out.format("Inserting taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
			taste1.getTasteId(), taste1.getTasteScore(), taste1.getUser().getUserName(), taste1.getBeer().getBeerId());
		Tastes taste2 = new Tastes("4.0", user1, beer2);
		taste2 = tastesDao.create(taste2);
		System.out.format("Inserting taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
			taste2.getTasteId(), taste2.getTasteScore(), taste2.getUser().getUserName(), taste2.getBeer().getBeerId());
		Tastes taste3 = new Tastes("5.0", user2, beer1);
		taste3 = tastesDao.create(taste3);
		System.out.format("Inserting taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
			taste3.getTasteId(), taste3.getTasteScore(), taste3.getUser().getUserName(), taste3.getBeer().getBeerId());
		
		Overalls overall1 = new Overalls("3.5", user1, beer1);
		overall1 = overallsDao.create(overall1);
		System.out.format("Inserting overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
			overall1.getOverallId(), overall1.getOverallScore(), overall1.getUser().getUserName(), overall1.getBeer().getBeerId());
		Overalls overall2 = new Overalls("4.0", user1, beer2);
		overall2 = overallsDao.create(overall2);
		System.out.format("Inserting overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
			overall2.getOverallId(), overall2.getOverallScore(), overall2.getUser().getUserName(), overall2.getBeer().getBeerId());
		Overalls overall3 = new Overalls("5.0", user2, beer1);
		overall3 = overallsDao.create(overall3);
		System.out.format("Inserting overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
			overall3.getOverallId(), overall3.getOverallScore(), overall3.getUser().getUserName(), overall3.getBeer().getBeerId());
		
		Scores score1 = new Scores("3.5", user1, beer1);
		score1 = scoresDao.create(score1);
		System.out.format("Inserting score: scoreId:%s score:%s userName:%s beerId:%s \n", 
			score1.getScoreId(), score1.getScore(), score1.getUser().getUserName(), score1.getBeer().getBeerId());
		Scores score2 = new Scores("4.0", user1, beer2);
		score2 = scoresDao.create(score2);
		System.out.format("Inserting score: scoreId:%s score:%s userName:%s beerId:%s \n", 
			score2.getScoreId(), score2.getScore(), score2.getUser().getUserName(), score2.getBeer().getBeerId());
		Scores score3 = new Scores("5.0", user2, beer1);
		score3 = scoresDao.create(score3);
		System.out.format("Inserting score: scoreId:%s score:%s userName:%s beerId:%s \n", 
			score3.getScoreId(), score3.getScore(), score3.getUser().getUserName(), score3.getBeer().getBeerId());
		
		System.out.println();
		
		// READ.
		System.out.println("****************************************READ****************************************");
		Users userRead1 = usersDao.getUserByUserName("Tom");
		System.out.format("Reading user: userName:%s userId:%s \n", userRead1.getUserName(), userRead1.getUserId());
		Users userRead2 = usersDao.getUserByUserId(1000);
		System.out.format("Reading user: userName:%s userId:%s \n", userRead2.getUserName(), userRead2.getUserId());
		
		Breweries breweries0 = breweriesDao.getBreweryById("101");
		System.out.format("Reading brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
			breweries0.getBreweryId(), breweries0.getBreweryName(), breweries0.getBreweryCountry(), 
			breweries0.getBreweryState());
		
		Breweries breweries1 = breweriesDao.getBreweryByName("First Brewery");
		System.out.format("Reading brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
			breweries1.getBreweryId(), breweries1.getBreweryName(), breweries1.getBreweryCountry(), 
			breweries1.getBreweryState());
		
		List<Breweries> breweries2 = breweriesDao.getBreweryByBreweryCountry("US");
		for(Breweries brewery: breweries2){
			System.out.format("Looping brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
				brewery.getBreweryId(), brewery.getBreweryName(), brewery.getBreweryCountry(), 
				brewery.getBreweryState());
		}
		
		List<Breweries> breweries3 = breweriesDao.getBreweryByBreweryState("WA");
		for(Breweries brewery: breweries3){
			System.out.format("Looping brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
				brewery.getBreweryId(), brewery.getBreweryName(), brewery.getBreweryCountry(), 
				brewery.getBreweryState());
		}
		
		Beers beers0 = beersDao.getBeerByBeerId("10000");
		System.out.format("Reading beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
			beers0.getBeerId(), beers0.getBeerName(), beers0.getBeerCountry(), beers0.getBeerState(), 
			beers0.getBeerABV(), beers0.getBrewery().getBreweryId());
	
		Beers beers1 = beersDao.getBeerByBeerName("First Wine");
		System.out.format("Reading beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
			beers1.getBeerId(), beers1.getBeerName(), beers1.getBeerCountry(), beers1.getBeerState(), 
			beers1.getBeerABV(), beers1.getBrewery().getBreweryId());
		
		List<Beers> beers2 = beersDao.getBeerByBeerCountry("US");
		for(Beers beer: beers2){
			System.out.format("Looping beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
				beer.getBeerId(), beer.getBeerName(), beer.getBeerCountry(), beer.getBeerState(), 
				beer.getBeerABV(), beer.getBrewery().getBreweryId());
		}
		
		List<Beers> beers3 = beersDao.getBeerByBeerState("WA");
		for(Beers beer: beers3){
			System.out.format("Looping beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
				beer.getBeerId(), beer.getBeerName(), beer.getBeerCountry(), beer.getBeerState(), 
				beer.getBeerABV(), beer.getBrewery().getBreweryId());
		}
		
		List<Beers> beers4 = beersDao.getBeerByBeerABV(45.1);
		for(Beers beer: beers4){
			System.out.format("Looping beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
				beer.getBeerId(), beer.getBeerName(), beer.getBeerCountry(), beer.getBeerState(), 
				beer.getBeerABV(), beer.getBrewery().getBreweryId());
		}
		
		Reviews review0 = reviewsDao.getReviewByReviewId(1);
		System.out.format("Reading review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
			review0.getReviewId(), review0.getReviewDate().toString(), review0.getReviewText(), 
			review0.getBeer().getBeerId(), review0.getUser().getUserName());
		
		List<Reviews> reviews1 = reviewsDao.getReviewByReviewDate(new Date("08/04/2010"));
		for(Reviews review: reviews1){
			System.out.format("Looping review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
				review.getReviewId(), review.getReviewDate().toString(), review.getReviewText(), 
				review.getBeer().getBeerId(), review.getUser().getUserName());
		}
		
		List<Reviews> reviews2 = reviewsDao.getReviewByBeerId("10000");
		for(Reviews review: reviews2){
			System.out.format("Looping review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
				review.getReviewId(), review.getReviewDate().toString(), review.getReviewText(), 
				review.getBeer().getBeerId(), review.getUser().getUserName());
		}
		
		List<Reviews> reviews3 = reviewsDao.getReviewByUserName("Tom");
		for(Reviews review: reviews3){
			System.out.format("Looping review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
				review.getReviewId(), review.getReviewDate().toString(), review.getReviewText(), 
				review.getBeer().getBeerId(), review.getUser().getUserName());
		}
		
		Smells smellRead = smellsDao.getSmellById(1);
		System.out.format("Reading smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
			smellRead.getSmellId(), smellRead.getSmellScore(), smellRead.getUser().getUserName(), 
			smellRead.getBeer().getBeerId());
		
		List<Smells> smellsListRead1 = smellsDao.getSmellByUserName("Tom");
		for (Smells s : smellsListRead1) {
			System.out.format("Looping smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
				s.getSmellId(), s.getSmellScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		List<Smells> smellsListRead2 = smellsDao.getSmellByBeerId("10000");
		for (Smells s : smellsListRead2) {
			System.out.format("Looping smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
				s.getSmellId(), s.getSmellScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		Looks lookRead = looksDao.getLookById(1);
		System.out.format("Reading look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
			lookRead.getLookId(), lookRead.getLookScore(), lookRead.getUser().getUserName(), 
			lookRead.getBeer().getBeerId());
		
		List<Looks> looksListRead1 = looksDao.getLookByUserName("Tom");
		for (Looks s : looksListRead1) {
			System.out.format("Looping look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
				s.getLookId(), s.getLookScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		List<Looks> looksListRead2 = looksDao.getLookByBeerId("10000");
		for (Looks s : looksListRead2) {
			System.out.format("Looping look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
				s.getLookId(), s.getLookScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		Feels feelRead = feelsDao.getFeelById(1);
		System.out.format("Reading feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
			feelRead.getFeelId(), feelRead.getFeelScore(), feelRead.getUser().getUserName(), 
			feelRead.getBeer().getBeerId());
		
		List<Feels> feelsListRead1 = feelsDao.getFeelByUserName("Tom");
		for (Feels s : feelsListRead1) {
			System.out.format("Looping feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
				s.getFeelId(), s.getFeelScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		List<Feels> feelsListRead2 = feelsDao.getFeelByBeerId("10000");
		for (Feels s : feelsListRead2) {
			System.out.format("Looping feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
				s.getFeelId(), s.getFeelScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		Tastes tasteRead = tastesDao.getTasteById(1);
		System.out.format("Reading taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
			tasteRead.getTasteId(), tasteRead.getTasteScore(), tasteRead.getUser().getUserName(), 
			tasteRead.getBeer().getBeerId());
		
		List<Tastes> tastesListRead1 = tastesDao.getTasteByUserName("Tom");
		for (Tastes s : tastesListRead1) {
			System.out.format("Looping taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
				s.getTasteId(), s.getTasteScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		List<Tastes> tastesListRead2 = tastesDao.getTasteByBeerId("10000");
		for (Tastes s : tastesListRead2) {
			System.out.format("Looping taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
				s.getTasteId(), s.getTasteScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		Overalls overallRead = overallsDao.getOverallById(1);
		System.out.format("Reading overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
			overallRead.getOverallId(), overallRead.getOverallScore(), overallRead.getUser().getUserName(), 
			overallRead.getBeer().getBeerId());
		
		List<Overalls> overallsListRead1 = overallsDao.getOverallByUserName("Tom");
		for (Overalls s : overallsListRead1) {
			System.out.format("Looping overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
				s.getOverallId(), s.getOverallScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		List<Overalls> overallsListRead2 = overallsDao.getOverallByBeerId("10000");
		for (Overalls s : overallsListRead2) {
			System.out.format("Looping overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
				s.getOverallId(), s.getOverallScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		Scores scoreRead = scoresDao.getScoreById(1);
		System.out.format("Reading score: scoreId:%s score:%s userName:%s beerId:%s \n", 
			scoreRead.getScoreId(), scoreRead.getScore(), scoreRead.getUser().getUserName(), 
			scoreRead.getBeer().getBeerId());
		
		List<Scores> scoresListRead1 = scoresDao.getScoreByUserName("Tom");
		for (Scores s : scoresListRead1) {
			System.out.format("Looping score: scoreId:%s score:%s userName:%s beerId:%s \n", 
				s.getScoreId(), s.getScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		List<Scores> scoresListRead2 = scoresDao.getScoreByBeerId("10000");
		for (Scores s : scoresListRead2) {
			System.out.format("Looping score: scoreId:%s score:%s userName:%s beerId:%s \n", 
				s.getScoreId(), s.getScore(), s.getUser().getUserName(), s.getBeer().getBeerId());
		}
		
		System.out.println();
		
		// UPDATE.
		System.out.println("****************************************UPDATE****************************************");
		Users userUpdate = usersDao.updateUserId(user1, 999);
		System.out.format("Updating user: userName:%s userId:%s \n", userUpdate.getUserName(), userUpdate.getUserId());
		
		Breweries newBrewery1 = breweriesDao.updateBreweries(brewery1, "Fashion Brewery", "US2", "WA2");
		System.out.format("Updating brewery: breweryId:%s breweryName:%s breweryCountry:%s breweryState:%s \n", 
			newBrewery1.getBreweryId(), newBrewery1.getBreweryName(), newBrewery1.getBreweryCountry(), 
			newBrewery1.getBreweryState());

		Beers newBeer = beersDao.updateBeer(beer1, "Highest Wine", "China", "BJ", 62.0);
		System.out.format("Updating beer: beerId:%s beerName:%s beerCountry:%s beerState:%s beerABV:%s breweryId:%s \n", 
			newBeer.getBeerId(), newBeer.getBeerName(), newBeer.getBeerCountry(), newBeer.getBeerState(), 
			newBeer.getBeerABV(), newBeer.getBrewery().getBreweryId());

		Reviews newReivew = reviewsDao.updateReview(review1, new Date("11/07/2019"), "Fantastic");
		System.out.format("Updating review: reviewId:%s reviewDate:%s reviewText:%s beerId:%s userName:%s \n", 
			newReivew.getReviewId(), newReivew.getReviewDate().toString(), newReivew.getReviewText(), 
			newReivew.getBeer().getBeerId(), newReivew.getUser().getUserName());
		
		Smells smellUpdate = smellsDao.updateSmellScore(smell1, "4.5");
		System.out.format("Updating smell: smellId:%s smellScore:%s userName:%s beerId:%s \n", 
			smellUpdate.getSmellId(), smellUpdate.getSmellScore(), smellUpdate.getUser().getUserName(), 
			smellUpdate.getBeer().getBeerId());
		
		Looks lookUpdate = looksDao.updateLookScore(look1, "4.5");
		System.out.format("Updating look: lookId:%s lookScore:%s userName:%s beerId:%s \n", 
			lookUpdate.getLookId(), lookUpdate.getLookScore(), lookUpdate.getUser().getUserName(), 
			lookUpdate.getBeer().getBeerId());
		
		Feels feelUpdate = feelsDao.updateFeelScore(feel1, "4.5");
		System.out.format("Updating feel: feelId:%s feelScore:%s userName:%s beerId:%s \n", 
			feelUpdate.getFeelId(), feelUpdate.getFeelScore(), feelUpdate.getUser().getUserName(), 
			feelUpdate.getBeer().getBeerId());
		
		Tastes tasteUpdate = tastesDao.updateTasteScore(taste1, "4.5");
		System.out.format("Updating taste: tasteId:%s tasteScore:%s userName:%s beerId:%s \n", 
			tasteUpdate.getTasteId(), tasteUpdate.getTasteScore(), tasteUpdate.getUser().getUserName(), 
			tasteUpdate.getBeer().getBeerId());
		
		Overalls overallUpdate = overallsDao.updateOverallScore(overall1, "4.5");
		System.out.format("Updating overall: overallId:%s overallScore:%s userName:%s beerId:%s \n", 
			overallUpdate.getOverallId(), overallUpdate.getOverallScore(), overallUpdate.getUser().getUserName(), 
			overallUpdate.getBeer().getBeerId());
		
		Scores scoreUpdate = scoresDao.updateScore(score1, "4.5");
		System.out.format("Updating score: scoreId:%s score:%s userName:%s beerId:%s \n", 
			scoreUpdate.getScoreId(), scoreUpdate.getScore(), scoreUpdate.getUser().getUserName(), 
			scoreUpdate.getBeer().getBeerId());
		
		System.out.println();
		
		// DELETE.
		System.out.println("****************************************DELETE****************************************");
		Users userDelete = usersDao.delete(user3);
		if (usersDao.getUserByUserName("Steve") == null) {
			System.out.println("Deleting user: user3 is successfully deleted");
		} else {
			System.out.println("Error in deleting user: user3 still exists");
		}
		
		Breweries breweriesDelete = breweriesDao.delete(brewery3);
		if (breweriesDao.getBreweryByName("Red Brewery") == null) {
			System.out.println("Deleting brewery: brewery3 is successfully deleted");
		} else {
			System.out.println("Error in deleting brewery: brewery3 still exists");
		}
		
		Beers BeersDelete = beersDao.delete(beer3);
		if (beersDao.getBeerByBeerName("Just Wine") == null) {
			System.out.println("Deleting beer: beer3 is successfully deleted");
		} else {
			System.out.println("Error in deleting beer: beer3 still exists");
		}
		
		Reviews reviewsDelete = reviewsDao.delete(review4);
		if (reviewsDao.getReviewByReviewId(4) == null) {
			System.out.println("Deleting review: review4 is successfully deleted");
		} else {
			System.out.println("Error in deleting review: review4 still exists");
		}
		
		Smells smellDelete = smellsDao.delete(smell3);
		if (smellsDao.getSmellById(3) == null) {
			System.out.println("Deleting smell: smell3 is successfully deleted");
		} else {
			System.out.println("Error in deleting smell: smell3 still exists");
		}
		
		Looks lookDelete = looksDao.delete(look3);
		if (looksDao.getLookById(3) == null) {
			System.out.println("Deleting look: look3 is successfully deleted");
		} else {
			System.out.println("Error in deleting look: look3 still exists");
		}
		
		Feels feelDelete = feelsDao.delete(feel3);
		if (feelsDao.getFeelById(3) == null) {
			System.out.println("Deleting feel: feel3 is successfully deleted");
		} else {
			System.out.println("Error in deleting feel: feel3 still exists");
		}
		
		Tastes tasteDelete = tastesDao.delete(taste3);
		if (tastesDao.getTasteById(3) == null) {
			System.out.println("Deleting taste: taste3 is successfully deleted");
		} else {
			System.out.println("Error in deleting taste: taste3 still exists");
		}
		
		Overalls overallDelete = overallsDao.delete(overall3);
		if (overallsDao.getOverallById(3) == null) {
			System.out.println("Deleting overall: overall3 is successfully deleted");
		} else {
			System.out.println("Error in deleting overall: overall3 still exists");
		}
		
		Scores scoreDelete = scoresDao.delete(score3);
		if (scoresDao.getScoreById(3) == null) {
			System.out.println("Deleting score: score3 is successfully deleted");
		} else {
			System.out.println("Error in deleting score: score3 still exists");
		}
	}
}
