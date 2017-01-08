package com.assignment.ixigo.calculators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.ixigo.model.Hotel;
import com.assignment.ixigo.utils.Helper;

/**
 * @author kamna
 *
 */
@Service("otherMatches")
public class OtherMatcher {

	/**
	 * @param hotel1
	 * @param hotel2
	 * @return It calculate the score of factors which contributes very less to
	 *         differentiate between two hotel entities. Weightage is very less
	 *         for these factors.
	 */
	public double lessImpComponentMatching(Hotel hotel1, Hotel hotel2) {
		double match = 0;
		if (hotel1.getNumberOfFloors() != null && hotel2.getNumberOfFloors() != null) {
			if (hotel1.getNumberOfFloors().equals(hotel2.getNumberOfFloors())) {
				match++;
			}
		}
		if (hotel1.getNumberOfRooms() != null && hotel2.getNumberOfRooms() != null) {
			if (hotel1.getNumberOfFloors().equals(hotel2.getNumberOfFloors())) {
				match++;
			}
		}
		if (hotel1.getCurrencyType() != null && hotel2.getCurrencyType() != null) {
			if (hotel1.getCurrencyType().equals(hotel2.getCurrencyType())) {
				match++;
			}
		}
		if (hotel1.getRating() != null && hotel2.getRating() != null) {
			if (hotel1.getRating().equals(hotel2.getRating())) {
				match++;
			}
		}
		if (hotel1.getAmenities() != null && hotel2.getAmenities() != null) {
			match += Helper.tokenScoreForTwoStrings(hotel1.getAmenities(), hotel2.getAmenities());
		}
		if (hotel1.getDistanceFromAirport() != null && hotel2.getDistanceFromAirport() != null) {
			match += Helper.diffBetweenTwoDistance(hotel1.getDistanceFromAirport(), hotel2.getDistanceFromAirport());
		}
		if (hotel1.getDistanceFromStation() != null && hotel2.getDistanceFromStation() != null) {
			match += Helper.diffBetweenTwoDistance(hotel1.getDistanceFromStation(), hotel2.getDistanceFromStation());
		}
		if (hotel1.getNearByAttraction() != null && hotel2.getNearByAttraction() != null) {
			match += Helper.tokenScoreForTwoStrings(hotel1.getNearByAttraction(), hotel2.getNearByAttraction());
		}
		if (hotel1.getPhoneNumber() != null && hotel2.getPhoneNumber() != null) {
			if (hotel1.getPhoneNumber().equals(hotel2.getPhoneNumber())) {
				match++;
			}
		}

		return match;

	}


}
