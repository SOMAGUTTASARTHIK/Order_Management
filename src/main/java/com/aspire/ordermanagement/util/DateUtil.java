package com.aspire.ordermanagement.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.aspire.ordermanagement.exception.CustomApplicationException;

public class DateUtil {
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private DateUtil() {
		
	}
	
	public static LocalDate toLocalDate(String date) {
		if(date == null ||date.trim().isEmpty()) {
			return null;
		}
		
		try {
			return LocalDate.parse(date, DATE_TIME_FORMATTER);
		} catch (DateTimeParseException dtpe) {
			throw new CustomApplicationException("invalid date formate and date formate should be dd/MM/yyyy");
		}
		
	}
	
	public static String toString(LocalDateTime localDateTime) {
		if(localDateTime == null) {
			return null;
		}
		
		return localDateTime.format(DATE_TIME_FORMATTER);
		
	}

}
