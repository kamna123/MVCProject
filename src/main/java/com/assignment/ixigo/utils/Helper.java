package com.assignment.ixigo.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.api.client.util.Lists;

/**
 * @author kamna
 *
 */
public class Helper {

	/**
	 * @param distance1
	 * @param distance2
	 * @return
	 */
	public static int diffBetweenTwoDistance(double distance1, double distance2) {
		Double difference = (distance1 > distance2 ? distance1 - distance2 : distance2 - distance1);
		if (difference < Constants.MIN_DISTANCE) {
			return 1;
		}
		return 0;
	}

	/**
	 * @param tokenList1
	 * @param tokenList2
	 * @return It matches two strings and check the exact match or substring
	 *         match.
	 */
	public static int tokenScoreForTwoLists(ArrayList<String> tokenList1, ArrayList<String> tokenList2) {
		int match = 0;
		for (String token1 : tokenList1) {
			for (String token2 : tokenList2) {
				if (token1 != null && token2 != null
						&& (token1.equals(token2) || token1.contains(token2) || token2.contains(token1))) {
					match++;
					break;
				}
			}
		}

		return match;
	}

	/**
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static double tokenScoreForTwoStrings(String string1, String string2) {
		ArrayList<String> list1 = getListFromString(string1);
		ArrayList<String> list2 = getListFromString(string2);
		double score = tokenScoreForTwoLists(list1, list2)
				/ (list1.size() < list2.size() ? list1.size() : list2.size());
		return score;
	}

	/**
	 * @param string
	 * @return
	 */
	public static ArrayList<String> getListFromString(String string) {
		ArrayList<String> arrayList = Lists.newArrayList(Arrays.asList(string.toLowerCase().split("[\\s,]+")));
		return arrayList;
	}

	/**
	 * @param inputStream
	 * @return
	 */
	public static String convertStreamToString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		scanner.useDelimiter("\\A");
		String result = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
		return result;
	}

	/**
	 * @param thresholdScore
	 * @param calculatedScore
	 * @return
	 */
	public static double decidingScore(int thresholdScore,int calculatedScore){
		double score = ((calculatedScore == 0) ? thresholdScore
				: (thresholdScore - Math.min(thresholdScore, calculatedScore)));
		return score;
	}
}
