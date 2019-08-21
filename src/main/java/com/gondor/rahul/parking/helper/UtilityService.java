package com.gondor.rahul.parking.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gondor.rahul.parking.exceptions.InvalidRequestException;

/**
 * class {@code UtilityService} is the utility class which will basically
 * includes Static methods. The methods in the class has a specific job to do as
 * that can be inferred by there name.
 *
 */
public class UtilityService {

	public static final String regex = "([0-9]{4})";

	/*
	 * code {@code isValidLicensePlate} method checks for the validity of the
	 * vehicleLicensePlateNumber.
	 */
	public static void isValidLicensePlate(String licensePlateNumber) throws InvalidRequestException {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(licensePlateNumber);
		if (!matcher.matches()) {
			throw new InvalidRequestException("The Requested License Plate Number is Invalid");
		}
	}
}
