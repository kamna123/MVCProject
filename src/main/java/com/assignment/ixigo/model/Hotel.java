package com.assignment.ixigo.model;

import org.springframework.stereotype.Repository;

import lombok.Data;

/**
 * @author kamna 
 * It is high level Hotel java class.
 *
 */
@Data
@Repository
public class Hotel {

	private String hotelType;
	private Address address;
	private String phoneNumber;
	private Integer numberOfRooms;
	private Integer numberOfFloors;
	private String currencyType;
	private String amenities;
	private String rating;
	private String cancelationPolicy;
	private String ownerName;
	private String careTakerName;
	private String priceRange;
	private Double distanceFromAirport;
	private Double distanceFromStation;
	private String vATorTINorCST;
	private String leaseAgreementNumber;
	private String nearByAttraction;
	private CheckInPolicy checkInPolicy;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append(this.address.getHotelName() == null ? "" : this.address.getHotelName()).append(" ")
				.append(this.address.getStreet() == null ? "" : this.address.getStreet()).append(" ")
				.append(this.address.getLocality() == null ? "" : this.address.getLocality()).append(" ").toString();

	}

}
