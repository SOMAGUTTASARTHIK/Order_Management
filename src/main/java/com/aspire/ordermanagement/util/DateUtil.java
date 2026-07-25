package com.aspire.ordermanagement.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.aspire.ordermanagement.exception.CustomApplicationException;

public class DateUtil {
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private DateUtil() {
		
	}
	
	public static LocalDate toLocalDate(String date) {
		if(date == null) {
			return null;
		}
		
		try {
			return LocalDate.parse(date, DATE_TIME_FORMATTER);
		} catch (DateTimeParseException dtpe) {
			throw new CustomApplicationException("invalid date formate and date formate should be dd/MM/yyyy");
		}
		
	}
	
	public static String toString(LocalDate date) {
		if(date == null) {
			return null;
		}
		
		return date.format(DATE_TIME_FORMATTER);
		
	}

}
