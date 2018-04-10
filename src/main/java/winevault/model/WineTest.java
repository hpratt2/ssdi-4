package winevault.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WineTest {
	private static final double EPS = 1e-16;
	
	Wine wineTest = new Wine(1,"N","C",10.0,1,2);
	
	@Test
	void testWine() {
		Wine wineTest = new Wine(1,"N","C",10.0,1,2);
		assertEquals(1,wineTest.getID());
		assertEquals("N",wineTest.getName());
		assertEquals("C",wineTest.getCountry());
		assertEquals(10.0, wineTest.getAvgRating(), EPS);
		assertEquals(1, wineTest.getPriceLow(), EPS);
		assertEquals(2, wineTest.getPriceHigh(), EPS);
	}
	
	@Test
	void testGetID() {
		assertEquals(1, wineTest.getID());
	}
	
	@Test
	void testGetName() {
		assertEquals("N", wineTest.getName());
	}
	
	@Test
	void testSetName() {
		wineTest.setName("name");
		assertEquals("name", wineTest.getName());
	}
	
	@Test
	void testGetCountry() {
		assertEquals("C", wineTest.getCountry());
	}
	
	@Test
	void testSetCountry() {
		wineTest.setCountry("US");
		assertEquals("US", wineTest.getCountry());
	}
	
	@Test
	void testGetAvgRating() {
		assertEquals(10.0, wineTest.getAvgRating(), EPS);
	}
	
	@Test
	void testSetAvgRating() {
		wineTest.setAvgRating(5.0);
		assertEquals(5.0, wineTest.getAvgRating(), EPS);
	}
	
	@Test
	void testGetPriceLow() {
		assertEquals(1, wineTest.getPriceLow(), EPS);
	}
	
	@Test
	void testSetPriceLow() {
		wineTest.setPriceLow(5.5);
		assertEquals(5.5, wineTest.getPriceLow(), EPS);
	}
	
	@Test
	void testGetPriceHigh() {
		assertEquals(2, wineTest.getPriceHigh(), EPS);
	}
	
	@Test
	void testSetPriceHigh() {
		wineTest.setPriceHigh(6.5);
		assertEquals(6.5, wineTest.getPriceHigh(), EPS);
	}
}
