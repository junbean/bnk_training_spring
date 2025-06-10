package com.example.memo.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	Calculator calculator = new Calculator();
	
	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	
	@Test
	void testDivisionByZero() {
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0), "0으로 나누면 IllegalArgumentException이 발생해야 합니다.");
		// assertThrows(NullPointerException.class, () -> calculator.divide(10, 0), "0으로 나누면 IllegalArgumentException이 발생해야 합니다.");
	}
	
	@Test
	void testFalse() {
		assertFalse(calculator.subtract(10, 2) == 5, "10-2는 5가 아니여야 한다");
	}
	
	@Test
	void testTrue() {
		assertTrue(calculator.multiply(3, 3) == 9, "3*3은 9여야 한다");
	}
	
	@Test
	void testNotEquals() {
		int result = calculator.add(2, 2);
		assertNotEquals(5, result, "2+2는 5가 아니여야 한다");
	}
	
	@Test
	void testDivide() {
		int result = calculator.divide(10, 5);
		assertEquals(2, result);
	}
	
	@Test
	void testMultiply() {
		int result = calculator.multiply(3, 5);
		assertEquals(15, result);
	}
	
	
	@Test
	void testSubtraction() {
		int result = calculator.subtract(3, 5);
		assertEquals(-2, result);
	}

	
	@Test
	void testAddtion() {
		int result = calculator.add(3, 5);
		assertEquals(8, result); // 통과 : 
		// assertEquals(9, result, 1); // 통과 - 오차 허용 범위 : 1
		// assertEquals(7, result, 1); // 통과 - 오차 허용 범위 : 1
		// assertEquals(6, result, 1, "8에서 오차 +1, -1을 허용합니다"); // 실페 : 실패할 시에 메세지를 출력
	}
	
}

	
	