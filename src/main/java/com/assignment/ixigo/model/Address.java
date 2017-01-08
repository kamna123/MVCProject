package com.assignment.ixigo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kamna
 *
 */
@Data
@NoArgsConstructor
public class Address {
	
	private String hotelName;
	private String street;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	private String landmark;
	private LatLong latLong;

	
	/**
	 * @param hotelName
	 * @param street
	 * @param locality
	 * @param city
	 * @param state
	 * @param country
	 * @param pinCode
	 */
	public Address(String hotelName, String street, String locality, String city, String state, String country,
			String pinCode) {
		super();
		this.hotelName = hotelName;
		this.street = street;
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getStreet() == null ? "" : this.getStreet()).append(" ")
				.append(this.getLocality() == null ? "" : this.getLocality()).append(" ")
				.append(this.getCity() == null ? "" : this.getCity()).append(", ")
				.append(this.getState() == null ? "" : this.getState()).append(", ")
				.append(this.getCountry() == null ? "" : this.getCountry()).append(" ")
				.append(this.getPinCode() == null ? "" : this.getPinCode());
		return builder.toString().trim();

	}

}
