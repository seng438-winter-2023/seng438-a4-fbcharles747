package org.jfree.data;

import static org.junit.Assert.*;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities{
	
	static Values2D values;
	static Values2D negValues;

	@BeforeClass
    public static void setUp()
    {
    	// setup
        Mockery mockingContext = new Mockery();
        values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
            	// 3 Rows
                allowing(values).getRowCount();
                will(returnValue(3));
                
                // 3 Columns
                allowing(values).getColumnCount();
                will(returnValue(3));
                
                // Row 1: [7.5, 2.2, 6.5]
                allowing(values).getValue(0, 0);
                will(returnValue(7.5));
                allowing(values).getValue(0, 1);
                will(returnValue(2.2));
                allowing(values).getValue(0, 2);
                will(returnValue(6.5));
                
                // Row 2: [2.5, 8.9, null]
                allowing(values).getValue(1, 0);
                will(returnValue(2.5));
                allowing(values).getValue(1, 1);
                will(returnValue(8.9));
                allowing(values).getValue(1, 2);
                will(returnValue(null));
                
                // Row 3: [5.5, 5.0, 2.5]
                allowing(values).getValue(2,0);
                will(returnValue(5.5));
                allowing(values).getValue(2,1);
                will(returnValue(5.0));
                allowing(values).getValue(2,2);
                will(returnValue(2.5));
            }
        });
        //
    	// setup
        Mockery mockingContext2 = new Mockery();
        negValues = mockingContext2.mock(Values2D.class);
        mockingContext2.checking(new Expectations() {
            {
            	// 3 Rows
                allowing(negValues).getRowCount();
                will(returnValue(-1));
                
                // 3 Columns
                allowing(negValues).getColumnCount();
                will(returnValue(-1));
                
                allowing(negValues).getValue(0, 0);
                will(returnValue(42));
                
                allowing(negValues).getValue(with(equal(1)), with(any(Integer.class)));
                will(returnValue(1));
            }
        });
    }
	
	
	
	@Test
	public void calculateColumnTotalForThreeValues() {
		double result = DataUtilities.calculateColumnTotal(values, 2);
	    // verify
	    assertEquals(9, result, .000000001d);
	}
	
	@Test
	public void calculateColumnTotalWithValidRows() {
		// Rows that will be taken into account
		int[] validRows = {0, 2, 3, 6};
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
	    // verify
	    assertEquals(13, result, .000000001d);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateColumnWithNullArgument() {
		DataUtilities.calculateColumnTotal(null, 0);
		DataUtilities.calculateColumnTotal(null, 0, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateRowWithNullArgument() {
		DataUtilities.calculateRowTotal(null, 0);
		DataUtilities.calculateRowTotal(null, 0, null);
	}
		
	@Test
	public void calculateRowTotalForThreeValues() {
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(16.2, result, .000000001d);
	}
	
	@Test
	public void calculateRowTotalWithValidCols() {
		// Rows that will be taken into account
		int[] validCols = {0,2,3};
		double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	    // verify
	    assertEquals(14, result, .000000001d);
	}
		
	@Test
	public void calculateRowTotalWithNegativeColCount() {		
		int[] validCols = {};
		double result = DataUtilities.calculateRowTotal(negValues, 0, validCols);
		assertEquals(0, result, .000000001d);
	}
	
	@Test
	public void createNumberArrayTest() {
		double[] array = {5.0,6.0,7.0};
		java.lang.Number[] expected = {5.0,6.0,7.0};
 		java.lang.Number[] result = DataUtilities.createNumberArray(array);
		assertArrayEquals(expected, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createNumberArrayWithNullArgument() {
		DataUtilities.createNumberArray(null);
	}
	
	@Test
	public void createNumberArray2DTest() {
		double[][] array = {{5.0,6.0,7.0}, {5.1,6.8,4.9}};
		java.lang.Number[][] expected = {{5.0,6.0,7.0}, {5.1,6.8,4.9}};
 		java.lang.Number[][] result = DataUtilities.createNumberArray2D(array);
 		assertEquals(expected.length, result.length);
 		assertEquals(expected[0].length, result[0].length);
		assertArrayEquals(expected, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createNumberArray2DWithNullArgument() {
		DataUtilities.createNumberArray2D(null);
	}
	
	@Test
	public void getCumulativePercentagesTest() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue("0", 9);
		data.addValue("1", 2);
		data.addValue("2", 14);
		
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		
		List<String> keys = result.getKeys();
		
		assertEquals(3, keys.size());
		assertEquals(9/25.0, result.getValue("0"));
		assertEquals(11/25.0, result.getValue("1"));
		assertEquals(25/25.0, result.getValue("2"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getCumulativePercentagesWithNullArgument() {
		DataUtilities.getCumulativePercentages(null);
	}

		
	@Test
	public void helperInstructionsTest(){
		Thread dataUtilThread = new Thread() { 
			@Override
			public void run() {
				DataUtilities.calculateRowTotal(negValues, 1);
			}
		};
		
		try {
			dataUtilThread.start();
			Thread.sleep(5);
			dataUtilThread.interrupt();
			assertTrue(dataUtilThread.isInterrupted());
		} catch (Exception e) {
			// If an exception occurred, fail the test
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void equalTest() {
		double[][] array1 = { 	{9, 6, 9.1},
								{4, 10, 3},
								{5, 0, 1.1}};
		
		double[][] array2 = { 	{9, 6, 9.1},
								{4, 10, 3},
								{5, 0, 1.1}};	
		
		boolean expected = true;
		boolean actual = DataUtilities.equal(array1, array2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void equalFailingTest() {
		double[][] array1 = { 	{8, 6, 9.1},
								{4, 10, 3},
								{5, 0, 1.1}};
		
		double[][] array2 = { 	{9, 6, 9.1},
								{4, 5, 3},
								{5, 0, 1.1}};
		double[][] nullArray = null;
		double[][] diffSizeArray = {{50,100}, {82,93}};
		
		boolean expected = false;
		// Test if two arrays with one differing element
		boolean actual = DataUtilities.equal(array1, array2);
		assertEquals(expected, actual);
		// Test if first array is null
		actual = DataUtilities.equal(nullArray, array2);
		assertEquals(expected, actual);
		// Test if second array is null
		actual = DataUtilities.equal(array1, nullArray);
		assertEquals(expected, actual);
		// Test if arrays are different sizes
		actual = DataUtilities.equal(array1, diffSizeArray);
		assertEquals(expected, actual);
		//Assert if both are null
		actual = DataUtilities.equal(nullArray, nullArray);
		assertEquals(true, actual);

	}
		
	@Test
	public void cloneTest() {
		double[][] expected = { {0, 1, 1.0},
								{2.0, 3, 5},
								{8.12, 13, 21}};
		
		double[][] actual = DataUtilities.clone(expected);
		
		assertTrue( DataUtilities.equal(expected, actual) );
	}
	
	@Test
	public void cloneTestWithNull() {
		double[][] expected = { {5,6,7}, null, {5,6,8}};
		
		double[][] actual = DataUtilities.clone(expected);
		
		assertTrue( DataUtilities.equal(expected, actual) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cloneTestWithNullArgument() {
		DataUtilities.clone(null);
	}

}
