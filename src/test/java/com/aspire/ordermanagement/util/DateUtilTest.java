package com.aspire.ordermanagement.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.ordermanagement.exception.CustomApplicationException;

@ExtendWith(MockitoExtension.class)
public class DateUtilTest {

	@Test
	void toLocalDateTest() {

		assertNull(DateUtil.toLocalDate(null));
		assertNull(DateUtil.toLocalDate(""));

	}

	@Test
	void toLocalDateTest_CustomApplicationException() {
		CustomApplicationException exception = assertThrows(CustomApplicationException.class,
				() -> DateUtil.toLocalDate("2027-09/37"));
		assertNotNull(exception);

	}
	
	@Test
	void toStringTest() {
		assertNull(DateUtil.toString(null));
	}

}
