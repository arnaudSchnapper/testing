import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.Random;

import static java.lang.Math.PI;

@RunWith(MockitoJUnitRunner.class)
public class MyPointTest {
	
	MyPoint pt;
	MyPoint pt2;
	MyPoint pt3;
	MyPoint pt4;
	MyPoint pt5;
	MyPoint pt6;
	
	@Before
	public void setUp() throws Exception {
		this.pt = new MyPoint();
		this.pt2 = new MyPoint(1,2);
		this.pt3 = new MyPoint(3,2);
		this.pt4 = new MyPoint(6,4);
		this.pt5 = new MyPoint(-4,6);
		this.pt6 = new MyPoint(-5,-7);
	}

	/*****************************************************************************
	**************************  CONTRUCTOR - TEST  *******************************
	*****************************************************************************/

	@Test
	public void testMyPoint() {
		assertTrue(pt.getX() == 0);
		assertTrue(pt.getY() == 0);
	}


	@Test
	public void testMyPointDoubleDouble() {
		MyPoint pt = new MyPoint(1,2);
		assertTrue(pt.getX() == 1);
		assertTrue(pt.getY() == 2);
	}

	
	@Test
	public void testMyPointMyPoint1() {
		MyPoint pt3 = new MyPoint(pt2);
		assertTrue(pt3.getX() == 1);
		assertTrue(pt3.getY() == 2);
	}
	@Test
	public void testMyPointMyPoint2() {
		MyPoint pt3 = new MyPoint(pt);
		assertTrue(pt3.getX() == 0);
		assertTrue(pt3.getY() == 0);
	}

	/*****************************************************************************
	****************************  SETTERS - TEST  ********************************
	*****************************************************************************/
	@Test
	public void testSetX() {
		pt.setX(15);
		assertTrue(pt.getX() == 15);
	}

	@Test
	public void testSetY() {
		pt.setY(13);
		assertTrue(pt.getY() == 13);
	}
	
	/*****************************************************************************
	**************************  GETTERS - TEST  **********************************
	*****************************************************************************/

	@Test
	public void testGetX() {
		assertTrue(pt2.getX() == 1);
	}

	@Test
	public void testGetY() {
		assertTrue(pt2.getY() == 2);
	}
	/*****************************************************************************
	**************************  METHODES - TEST  **********************************
	*****************************************************************************/

	@Test
	public void testScale() {
		pt2 = pt2.scale(5);
		assertTrue(pt2.getX() == 5);
		assertTrue(pt2.getY() == 10);
	}

	//This is left of the MyPoint parameter
	@Test
	public void testHorizontalSymmetryRight() {
		pt = pt3.horizontalSymmetry(pt4);
		assertTrue(pt.getX() == 9);
		assertTrue(pt.getY() == 2);
		
	}
	
	//This is right of the MyPoint parameter
	@Test
	public void testHorizontalSymmetryLeft() {
		pt = pt4.horizontalSymmetry(pt3);
		assertTrue(pt.getX() == 0);
		assertTrue(pt.getY() == 4);
		
	}
	
	//Test for 2 points with same coordinates
	@Test
	public void testHorizontalSymmetrySame() {
		pt = pt4.horizontalSymmetry(pt4);
		assertTrue(pt.getX() == pt4.getX());
		assertTrue(pt.getY() == pt4.getY());
		
	}

	//Test the exception when the symetric point is null
	@Test(expected = IllegalArgumentException.class)
	public void testHorizontalSymmetryNull() {
		pt = pt4.horizontalSymmetry(null);
	}
	
	@Test
	public void testHorizontalSymmetryNegative() {
		pt = pt5.horizontalSymmetry(pt3);
		assertTrue(pt.getX() == 10);
		assertTrue(pt.getY() == pt5.getY());
		
	}
	

	@Test
	public void testHorizontalSymmetryBothNegative() {
		pt = pt6.horizontalSymmetry(pt5);
		assertTrue(pt.getX() == -3);
		assertTrue(pt.getY() == pt6.getY());
		
	}
	
	@Test
	public void testComputeAngleZero() {
		double angle = pt.computeAngle(pt3);
		assertEquals(0.5880, angle, 0.0001);
	}
	
	@Test
	public void testComputeAnglePositive() {
		double angle = pt3.computeAngle(pt2);
		assertEquals(3.1415, angle, 0.0001);
	}
	
	@Test
	public void testComputeAngleNegative() {
		double angle = pt6.computeAngle(pt5);
		assertEquals(1.4940, angle, 0.0001);
	}
	
	@Test
	public void testComputeAngleSame() {
		double angle = pt4.computeAngle(pt4);
		assertEquals(1.0471, angle, 0.0001);
	}
	
	@Test
	public void testComputeAngleYNegat() {
		double angle = pt3.computeAngle(new MyPoint(3,1));
		assertEquals(5.2359, angle, 0.0001);
	}

	@Test
	public void testRotatePoint() {
		pt = pt.rotatePoint(pt2, 2.1);
		assertEquals(2.3680, pt.getX(), 0.0001);
		assertEquals(0.6416, pt.getY(), 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRotatePointNull() {
		pt = pt.rotatePoint(null, 1);
	}

	@Test
	public void testRotatePointNegativeAngle() {
		pt = pt.rotatePoint(pt2, -1);
		assertEquals(-0.3817, pt.getX(), 0.0001);
		assertEquals(1.3011, pt.getY(), 0.0001);
	}
	
	@Test
	public void testRotatePointPiAngle() {
		pt = pt.rotatePoint(pt2, PI);
		assertEquals(2, pt.getX(), 0.0001);
		assertEquals(2, pt.getY(), 0.0001);
	}
	
	@Test
	public void testRotatePointHalfPiAngle() {
		pt = pt.rotatePoint(pt2, PI/2d);
		assertEquals(2, pt.getX(), 0.0001);
		assertEquals(0, pt.getY(), 0.0001);
	}
	
	@Test
	public void testRotatePointTripleHalfPiAngle() {
		pt = pt.rotatePoint(pt2, 3*PI/2d);
		assertEquals(0, pt.getX(), 0.0001);
		assertEquals(2, pt.getY(), 0.0001);
	}

	@Test
	public void testRotatePointAngleZero() {
		pt3 = pt.rotatePoint(pt2, 0);
		assertEquals(pt3.getX(), pt.getX(), 0.0001);
		assertEquals(pt3.getY(), pt.getY(), 0.0001);
	}
	
	@Test
	public void testCentralSymmetry() {
		pt = pt2.centralSymmetry(pt4);
		assertEquals(1, pt.getX(), 0.0001);
		assertEquals(2, pt.getY(), 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCentralSymmetryNull() {
		pt.centralSymmetry(null);
	}

	@Test
	public void testGetMiddlePoint() {
		pt = pt.getMiddlePoint(pt4);
		assertTrue(pt.getX() == 3);
		assertTrue(pt.getY() == 2);
	}
	
	@Test
	public void testGetMiddlePointSame() {
		pt = pt4.getMiddlePoint(pt4);
		assertTrue(pt.getX() == pt4.getX());
		assertTrue(pt.getY() == pt4.getY());
	}

	@Test
	public void testGetMiddlePointNegative() {
		pt = pt5.getMiddlePoint(pt6);
		assertTrue(pt.getX() == -4.5);
		assertTrue(pt.getY() == -0.5);
	}
	@Test
	public void testSetPoint() {		
		Random rdMock = mock(Random.class);
		when(rdMock.nextInt()).thenReturn(10);
		
		pt.setPoint(rdMock, rdMock);
		assertTrue(pt.getX() == 10);
		assertTrue(pt.getY() == 10);
	}
	
	@Test
	public void testTranslateDbl() {
		pt.translate(4, 3);
		assertTrue(pt.getX() == 4);
		assertTrue(pt.getY() == 3);		
	}
	
	@Test
	public void testTranslateIT() {
		ITranslation itMock = mock(ITranslation.class);
		when(itMock.getTx()).thenReturn(4);
		when(itMock.getTy()).thenReturn(3);
		
		pt.translate(itMock);
		assertTrue(pt.getX() == 4);
		assertTrue(pt.getY() == 3);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTranslateITNull() {
		pt.translate(null);
	}
}
