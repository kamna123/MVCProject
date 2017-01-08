package com.assignment.ixigo.calculators;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import org.springframework.stereotype.Service;

import com.assignment.ixigo.model.Address;
import com.assignment.ixigo.model.LatLong;
import com.assignment.ixigo.utils.Constants;
import com.assignment.ixigo.utils.Helper;
import com.google.api.client.util.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kamna
 *
 */
@Slf4j
@Service("latLongCalculation")
public class LatLongCalculator {
	/**
	 * @param location
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 *             It takes the address entered by user and return the lat long
	 *             using google map apis. It is helper for
	 *             getLatLongUsingGoogleApi Api.
	 */
	public LatLong getLatLongFromGoogleMapLocation(String location) throws IOException {
		String mapLocationResult;
		LatLong latLong = new LatLong();
		URL mapLocationUrl = null;
		try {
			mapLocationUrl = new URI(location).toURL();
		} catch (URISyntaxException e) {
			log.info("URL entered is incorrect ", e.getMessage());
		}
		mapLocationResult = Helper.convertStreamToString(mapLocationUrl.openConnection().getInputStream());
		Gson gson = new Gson();
		JsonObject job = gson.fromJson(mapLocationResult, JsonObject.class);
		if (job.getAsJsonArray("results").size() > 0) {
			JsonObject entry = job.getAsJsonArray("results").get(0).getAsJsonObject().getAsJsonObject("geometry")
					.getAsJsonObject("location");

			latLong.setLatitude(entry.get("lat").getAsDouble());
			latLong.setLongitude(entry.get("lng").getAsDouble());
		}

		return latLong;
	}

	/**
	 * @param address1
	 * @param address2
	 * @return
	 * @throws URISyntaxException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 *             It takes two address and return their lat long using google
	 *             map apis.
	 */
	public HashMap<String, LatLong> getLatLongUsingGoogleApi(Address address1, Address address2) {
		HashMap<String, LatLong> latLongs = Maps.newHashMap();
		String googleMapLocation1 = null;
		String googleMapLocation2 = null;
		LatLong googleMapLocation1LatLong = null;
		LatLong googleMapLocation2LatLong = null;
		try {
			googleMapLocation1 = Constants.GOOGLE_REST_API + URLEncoder.encode(address1.toString(), "UTF-8")
					+ Constants.KEY;
			googleMapLocation2 = Constants.GOOGLE_REST_API + URLEncoder.encode(address2.toString(), "UTF-8")
					+ Constants.KEY;
			googleMapLocation1LatLong = getLatLongFromGoogleMapLocation(googleMapLocation1);
			googleMapLocation2LatLong = getLatLongFromGoogleMapLocation(googleMapLocation2);
		} catch (IOException e) {
			log.info("Some problem with network", e.getMessage());
		}
		latLongs.put(Constants.ADDRESS_1, googleMapLocation1LatLong);
		latLongs.put(Constants.ADDRESS_2, googleMapLocation2LatLong);
		return latLongs;
	}

	

	public static void main(String[] args) throws UnsupportedEncodingException, IOException, URISyntaxException {
		LatLongCalculator calculation = new LatLongCalculator();
		Address address = new Address("2a-003 akme harmony", "locality", "bellandur", "banglore", "karnatka", "india",
				"560103");
		Address address2 = new Address(" kumar I life Apartments", "locality", "deverbisanahalli", "bengluru",
				"karnatka", "india", "560103");
		HashMap<String, LatLong> hashMap = calculation.getLatLongUsingGoogleApi(address, address2);
		System.out.println(hashMap);
	}

}
