package com.assignment.ixigo.utils;

/**
 * @author kamna
 *
 */
public class Constants {

	public static final String KEY = "&key=AIzaSyCpcING8TU4CQRoCIv7wbtIkdf46z8gu-s";
	public static final String GOOGLE_REST_API = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	public static final int SCREENING_MATCH = 3;
	public static final int ADDRESS_TOKEN_MATCHING_SCORE = 20;
	public static final int NAME_TOKEN_MATCHING_SCORE = 30;
	public static final int NAME_LEVENSHTEIN_DISTANCE_SCORE = 30;
	public static final int LOCALITY_LEVENSHTEIN_DISTANCE_SCORE = 10;
	public static final int STREET_LEVENSHTEIN_DISTANCE_SCORE = 10;
	public static final int AERIAL_DISTANCE_SCORE = 20;
	public static final String ADDRESS_1 = "ADDRESS_1";
	public static final String ADDRESS_2 = "ADDRESS_2";
	public static final int MIN_DISTANCE = 500;
	public static final int OVERALL_MATCHING_THRESHOLD = 90;
	public static final int UNIQUE_EXACT_MATCH = 1;
	public static final int UNIQUE_PARTIAL_MATCH = -1;
	public static final int UNIQUE_NO_MATCH = 0;
	public static final int AERIAL_DISTANCE_CUTOFF = 1500;
}
