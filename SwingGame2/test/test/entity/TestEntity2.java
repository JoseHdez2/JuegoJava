package test.entity;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import adv.entity.Entity2_Movable;
import entity.util.Vect2F;

public class TestEntity2 {

	Entity2_Movable testEnt;
	
	@Before
	public void before(){
		testEnt = new Entity2_Movable(100,100,100,100);
		testEnt.body.setLocation(0, 0);
	}

	@Test
	public void go(){
		testEnt.go(Vect2F.LEFT, 1);
		assertEquals("Doesn't move unless we update.",new Point(0,0),testEnt.body.getLocation());
		testEnt.update();
		assertEquals("Moves the amount specified, per update() call.",new Point(-1,0),testEnt.body.getLocation());
		testEnt.go(Vect2F.RIGHT, 1);
		assertEquals("Even if we change direction, doesn't move unless we update.",new Point(-1,0),testEnt.body.getLocation());
		testEnt.update();
		assertEquals("Directions can be overridden",new Point(0,0),testEnt.body.getLocation());
		testEnt.update();
		assertEquals("Keeps going in a direction without having to recall go()",new Point(1,0),testEnt.body.getLocation());
	}
	
	@Test
	public void goX(){
		
		// Similar tests as with go()
		testEnt.goX(Vect2F.LEFT, 1);
		testEnt.update();
		assertEquals("Moves the amount specified, per update() call.",new Point(-1,0),testEnt.body.getLocation());
		testEnt.goX(Vect2F.RIGHT, 1);
		assertEquals("Even if we change direction, doesn't move unless we update.",new Point(0,0),testEnt.body.getLocation());
		testEnt.update();
		assertEquals("Directions can be overridden.",new Point(0,0),testEnt.body.getLocation());
		testEnt.update();
		assertEquals("Keeps going in a direction without having to recall go().",new Point(1,0),testEnt.body.getLocation());
		
		// Tests unique to goX()
		testEnt.body.setLocation(0, 0);
		testEnt.go(Vect2F.DOWN, 2);
		testEnt.goX(Vect2F.RIGHT, 2);
		testEnt.update();
		assertEquals("Does not override Y direction.",new Point(2,2),testEnt.body.getLocation());
	}
}
