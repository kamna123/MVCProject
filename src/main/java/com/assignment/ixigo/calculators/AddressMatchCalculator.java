package com.assignment.ixigo.calculators;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.ixigo.model.Address;
import com.assignment.ixigo.model.Hotel;
import com.assignment.ixigo.model.LatLong;
import com.assignment.ixigo.utils.Constants;
import com.assignment.ixigo.utils.Helper;

/**
 * @author kamna
 *
 */
@Service("addressMatchCalculator")
public class AddressMatchCalculator {

	LatLongCalculator latLongCalculation;

	Hotel hotel1;
	Hotel hotel2;

	/**
	 * 
	 */
	@Autowired
	public AddressMatchCalculator(LatLongCalculator latLongCalculation, Hotel hotel1, Hotel hotel2) {
		this.hotel1 = hotel1;
		this.hotel2 = hotel2;
		this.latLongCalculation = latLongCalculation;
	}

	/**
	 * @param hotelName1
	 * @param hotelName2
	 * @return Calculating the score of hotel names provided by two providers.
	 */
	public double hotelNameScore(Address address1, Address address2) {
		double score = 0d;

		String hotelName1 = address1.getHotelName();
		String hotelName2 = address2.getHotelName();

		if (hotelName1 != null && hotelName2 != null) {
			double nameTokenScore = Helper.tokenScoreForTwoStrings(hotelName1, hotelName2);
			score += ((nameTokenScore == 0) ? 0 : (Constants.NAME_TOKEN_MATCHING_SCORE * (nameTokenScore)));

			/*
			 * for hotel name we are calculating levenshteinDistance also and it
			 * has total score 30.
			 */
			int nameDistance = StringUtils.getLevenshteinDistance(hotelName1, hotelName2);
			score += ((nameDistance == 0) ? Constants.NAME_LEVENSHTEIN_DISTANCE_SCORE
					: (Constants.NAME_LEVENSHTEIN_DISTANCE_SCORE
							- Math.min(Constants.NAME_LEVENSHTEIN_DISTANCE_SCORE, nameDistance)));

		}
		return score;
	}

	/**
	 * @param latLong1
	 * @param latLong2
	 * @return calculating aerial distance between the geocoded lat longs for
	 *         two hotels and if the difference if less than 1500 meters then we
	 *         are calculating the aerial score and adding it to total score
	 */

	public double aerialDistanceScore(LatLong latLong1, LatLong latLong2) {
		double aerialDistance = AerialDistanceCalculator.distance(latLong1.getLatitude(), latLong1.getLongitude(),
				latLong2.getLatitude(), latLong2.getLongitude(), 'm');

		double aerialScore = 0;
		if (aerialDistance < Constants.AERIAL_DISTANCE_CUTOFF) {
			aerialScore = (aerialDistance == 0) ? Constants.AERIAL_DISTANCE_SCORE
					: (Constants.AERIAL_DISTANCE_SCORE - (aerialDistance / 75));
		} // TODO
		return aerialScore;
	}

	/**
	 * @param address1
	 * @param address2
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 *             It gives us the score based on matching of address of two
	 *             Hotel objects. Here total score is 140
	 */
	public double addressScore(Address address1, Address address2) {

		double score = 0d; // to store the total matching score.
		boolean geoWeight = false;
		/*
		 * It returns how many words in both the address except the hotel name
		 * have exact match or substring match. It has total 20 score.
		 */
		double addressTokenMatch = Helper.tokenScoreForTwoStrings(address1.toString(), address2.toString());
		score += ((addressTokenMatch == 0) ? 0 : (Constants.ADDRESS_TOKEN_MATCHING_SCORE * (addressTokenMatch)));

		/*
		 * for street name we are calculating levenshteinDistance and it has
		 * total score 10.
		 */
		if (address1.getStreet() != null && address2.getStreet() != null) {
			int streetDistance = StringUtils.getLevenshteinDistance(address1.getStreet(), address2.getStreet());
			score += Helper.decidingScore(Constants.STREET_LEVENSHTEIN_DISTANCE_SCORE, streetDistance);
		}
		/*
		 * for locality we are calculating levenshteinDistance and it has total
		 * score 10.
		 */
		if (address1.getLocality() != null && address2.getLocality() != null) {
			int localityDistance = StringUtils.getLevenshteinDistance(address1.getLocality(), address2.getLocality());
			score += Helper.decidingScore(Constants.LOCALITY_LEVENSHTEIN_DISTANCE_SCORE, localityDistance);
		}
		// lat long calculation using google map API
		HashMap<String, LatLong> hashMap = latLongCalculation.getLatLongUsingGoogleApi(address1, address2);
		LatLong latLong1 = hashMap.get(Constants.ADDRESS_1);
		LatLong latLong2 = hashMap.get(Constants.ADDRESS_2);

		/*
		 * check if the lat long given by provider matches with the geocoded
		 * lat-long(calculated using google API), if yes then their weight has
		 * to be boosted.
		 */
		if (address1.getLatLong() != null && address2.getLatLong() != null) {
			if ((AerialDistanceCalculator.distance(latLong1.getLatitude(), latLong1.getLongitude(),
					address1.getLatLong().getLatitude(), address1.getLatLong().getLongitude(), 'm') < 400)
					&& (AerialDistanceCalculator.distance(latLong2.getLatitude(), latLong2.getLongitude(),
							address1.getLatLong().getLatitude(), address1.getLatLong().getLongitude(), 'm') < 400)) {
				geoWeight = true;
			}
		}

		/*
		 * calculating aerial distance between the geocoded lat longs for two
		 * hotels
		 */
		double aerialScore = aerialDistanceScore(latLong1, latLong2);

		if (geoWeight) {
			aerialScore *= 2;
		}
		score += aerialScore;
		return score;
	}

}
