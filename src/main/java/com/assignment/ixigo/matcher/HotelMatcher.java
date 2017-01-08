package com.assignment.ixigo.matcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.ixigo.calculators.AddressMatchCalculator;
import com.assignment.ixigo.calculators.OtherMatcher;
import com.assignment.ixigo.calculators.ScreeningStep;
import com.assignment.ixigo.model.Hotel;
import com.assignment.ixigo.utils.Constants;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kamna It is main entry class in service,where we match all the
 *         components of two hotel objects
 *
 */

public class HotelMatcher {

	@Autowired
	ScreeningStep screeningStep;

	@Autowired
	AddressMatchCalculator addressMatchCalculator;

	@Autowired
	OtherMatcher otherMatches;

	@Getter
	@Setter
	Hotel hotel1;

	@Getter
	@Setter
	Hotel hotel2;

	/**
	 * @param hotel1
	 * @param hotel2
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public boolean sameEntitiesorNot() {
		// first do an exact match :
		int screeningResult = screeningStep.matchUniqueInformation(hotel1, hotel2);
		if (screeningResult == Constants.UNIQUE_NO_MATCH) {
			return false;
		} else if (screeningResult == Constants.UNIQUE_EXACT_MATCH) {
			return true;
		}
		double score = 0;
		if (hotel1.getAddress() != null && hotel2.getAddress() != null) {
			score += addressMatchCalculator.addressScore(hotel1.getAddress(), hotel2.getAddress());
			score += addressMatchCalculator.hotelNameScore(hotel1.getAddress(), hotel2.getAddress());
		}

		score += otherMatches.lessImpComponentMatching(hotel1, hotel2);
		System.out.println("scores are:" + score);
		if (score >= Constants.OVERALL_MATCHING_THRESHOLD) {
			return true;
		}

		return false;

	}

}
