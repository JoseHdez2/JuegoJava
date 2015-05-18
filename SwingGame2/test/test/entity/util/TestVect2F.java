package test.entity.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import entity.util.Vect2F;

public class TestVect2F {

	Vect2F leftVect, rightVect;
	
	@Before
	public void before() {
		leftVect = Vect2F.LEFT;
		rightVect = Vect2F.RIGHT;
	}
	
	@Test
	public void invert() {
		leftVect.invert();
		assertEquals("The vector is inverted after the function call.", new Float(1), leftVect.x);
		assertEquals("The function call returns the inverted vector.", new Float(-1), rightVect.invert().x);
		assertEquals("The vector remains inverted.", new Float(-1), rightVect.x);
	}
	
	@Test
	public void inverted() {
		assertEquals("The function call returns the inverted vector.", Vect2F.LEFT.x, rightVect.inverted().x);
		assertEquals("The vector remains unmodified.", Vect2F.RIGHT.x, rightVect.x);
	}

}
