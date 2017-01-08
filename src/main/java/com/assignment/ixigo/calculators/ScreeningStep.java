package com.assignment.ixigo.calculators;

import org.springframework.stereotype.Service;

import com.assignment.ixigo.model.Hotel;
import com.assignment.ixigo.utils.Constants;

/**
 * @author kamna
 *
 */
@Service("screeningStep")
public class ScreeningStep {
	/**
	 * @param hotel1
	 * @param hotel2
	 * @return This is major step. If two hotels have same VAT/TIN/CST or lease
	 *         agreement or exact same address,then they represent the same
	 *         hotel. We have assumed that city,state country are codified.so if
	 *         they do not match,then we do not proceed further.
	 * 
	 */
	public int matchUniqueInformation(Hotel hotel1, Hotel hotel2) {
		boolean exactMatch = false;
		
		if (hotel1.getAddress().getCity() != null && hotel2.getAddress().getCity() != null) {
			if (!hotel1.getAddress().getCity().toLowerCase().equals(hotel2.getAddress().getCity().toLowerCase())) {
				return Constants.UNIQUE_NO_MATCH;
			}
		}
		if (hotel1.getAddress().getState() != null && hotel2.getAddress().getState() != null) {
			if (!hotel1.getAddress().getState().toLowerCase().equals(hotel2.getAddress().getState().toLowerCase())) {
				return Constants.UNIQUE_NO_MATCH;
			}
		}
		if (hotel1.getAddress().getCountry() != null && hotel2.getAddress().getCountry() != null) {
			if (!hotel1.getAddress().getCountry().toLowerCase()
					.equals(hotel2.getAddress().getCountry().toLowerCase())) {
				return Constants.UNIQUE_NO_MATCH;
			}
		}
		if (hotel1.getVATorTINorCST() != null && hotel2.getVATorTINorCST() != null) {
			if (hotel1.getVATorTINorCST().equals(hotel2.getVATorTINorCST())) {
				exactMatch = true;
			}
		}

		if (hotel1.getLeaseAgreementNumber() != null && hotel2.getLeaseAgreementNumber() != null) {
			if (hotel1.getLeaseAgreementNumber().equals(hotel2.getLeaseAgreementNumber())) {
				exactMatch = true;
			}
		}
		if (hotel1.getAddress().getHotelName() != null && hotel2.getAddress().getHotelName() != null
				&& hotel1.getAddress().getCity() != null && hotel2.getAddress().getCity() != null
				&& hotel1.getAddress().getLocality() != null && hotel2.getAddress().getLocality() != null
				&& hotel1.getAddress().getStreet() != null && hotel2.getAddress().getStreet() != null) {
			exactMatch = (hotel1.getAddress().getHotelName().equals(hotel2.getAddress().getHotelName()))
					&& (hotel1.getAddress().getCity().equals(hotel2.getAddress().getCity()))
					&& (hotel1.getAddress().getLocality().equals(hotel2.getAddress().getLocality()))
					&& (hotel1.getAddress().getStreet().equals(hotel2.getAddress().getStreet()));
		}

		if (exactMatch) {
			return Constants.UNIQUE_EXACT_MATCH;
		}
		return Constants.UNIQUE_PARTIAL_MATCH;
	}
}
