package org.jfree.data.test;

import static org.junit.Assert.*;
import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;


import org.junit.Test;

public class RangeTest {

	 private Range exampleRange;
	 
	    @BeforeClass public static void setUpBeforeClass() throws Exception {
	    }


	    @Before
	    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
	    }


	    @Test
	    public void testContains() {
	    	assertTrue("The contain method should return true because 0 is within the range -1 to 1",exampleRange.contains(0));
	    }
	    
	    
	    @Test
	    public void testGetLowerBound() {
	    	double expected=-1.0;
	    	double actual=exampleRange.getLowerBound();
	    
	    	assertEquals("GetLowerBound should return -1",expected,actual,.000000001d);
	    }
	    
	    @Test
	    public void testGetUpperBound() {
	    	double expected=1.0;
	    	double actual=exampleRange.getUpperBound();

	    	assertEquals("The upper bound should be 1",expected,actual,.000000001d);
	    }
	    
	    @Test
	    public void testGetLength() {
	    	double expected=2.0;
	    	double actual=exampleRange.getLength();
	    	assertEquals("The length of exampleRange should be 2",expected,actual,.000000001d);
	    }
	    
	    @Test
	    public void testCombine() {
	    	
	    	Range range2=new Range(-5,-1);
	    	Range actual=Range.combine(exampleRange, range2);
	    	Range expected=new Range(-5,1);	
	    	assertEquals("The combine method should return a range from -5 to 1",expected,actual);
	    	
	    }
	    
	    @Test
	    public void testContains_LB_returnTrue() {
	    	double LB=-1;
	    	
	    	assertTrue("Lower bound should be included in the range",exampleRange.contains(LB));
	    }
	    
	    @Test
	    public void testContains_ALB_returnTrue() {
	    	double ALB=-0.5;
	    	assertTrue("value above lower bound should be included in the range",exampleRange.contains(ALB));
	    	
	    }
	    
	    @Test
	    public void testContains_BLB_returnFalse() {
	    	double BLB=-2;
	    	assertFalse("value below lower bound should not be included in the range",exampleRange.contains(BLB));
	    	
	    }
	    
	    @Test
	    public void testContains_UB_returnTrue() {
	    	double UB=1;
	    	assertTrue("upper bound should be included in the range",exampleRange.contains(UB));
	    	
	    }
	    
	    @Test
	    public void testContains_BUB_returnTrue() {
	    	double BUB=0.5;
	    	assertTrue("value below upper bound should be included in the range",exampleRange.contains(BUB));
	    	
	    }
	    
	    @Test
	    public void testContains_AUB_returnFalse() {
	    	double AUB=1.5;
	    	assertFalse("value above upper bound should not be included in the range",exampleRange.contains(AUB));
	    	
	    }
	    
	    @Test(expected=IllegalArgumentException.class)
	    public void testGetLength_LowerIsBiggerThanUpper_ThrowIllegalArgumentException() {
	    	Range range=new Range(1,-1);
	    	double length=range.getLength();
	    }
	    
//	    method constrains
	    @Test
	    public void testConstrains_valueIsGreaterThanUpperBound_returnUpperbound() {
	    	double expected=exampleRange.getUpperBound();
	    	double result= exampleRange.constrain(5);
	    	assertEquals("constrains should return upper bound if the passed value is greater than upper bound",expected,result,.000000001d);
	    	
	    }
	    
	    @Test
	    public void testConstrains_valueIsLowerThanLowerBound_returnLowerbound() {
	    	double expected=exampleRange.getLowerBound();
	    	double result= exampleRange.constrain(-5);
	    	assertEquals("constrains should return lower bound if the passed value is lower than Lower bound",expected,result,.000000001d);
	    	
	    }
	    
	    @Test
	    public void testShift_shiftExampleRangeBy5AllowingZeroCrossing() {
	    	exampleRange=Range.shift(exampleRange, 5, true);
	    	double expectedUpper=6;
	    	double expectedLower=4;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    	
	    }
	    
	    @Test
	    public void testShift_notAllowZeroCrossing() {
	    	exampleRange=Range.shift(exampleRange, 2);
	    	double expectedUpper=3;
	    	double expectedLower=0;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    }
	    
//	    test method scale
	    
	    @Test 
	    public void testScale_FactorBiggerThanZero() {
	    	exampleRange= Range.scale(exampleRange, 3);
	    	double expectedUpper=3;
	    	double expectedLower=-3;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    }
	    
	    @Test(expected=IllegalArgumentException.class)
	    public void testScale_FactorSmallerThanZero_ThrowIllegalArgumentException() {
	    	exampleRange= Range.scale(exampleRange, -3);
//	    	double expectedUpper=3;
//	    	double expectedLower=-3;
//	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
//	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
//	    
	    }
	    
//	    expand test
	    
	    @Test
	    public void testExpand_LowerUpperMarginIs3() {
	    	exampleRange= Range.expand(exampleRange, 3, 3);
	    	double expectedUpper=7;
	    	double expectedLower=-7;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    	
	    }
	    
//	    test method ExpandToInclude()
	    @Test
	    public void testExpandToInclude_valueGreaterThanUpperBound() {
	    	exampleRange= Range.expandToInclude(exampleRange, 7);
	    	double expectedUpper=7;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    }
	    
	    @Test
	    public void testExpandToInclude_valueSmallerThanLowerBound() {
	    	exampleRange= Range.expandToInclude(exampleRange, -7);
	    	double expectedUpper=1;
	    	double expectedLower=-7;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    }
	    
	    @Test
	    public void testExpandToInclude_valueWithinBound() {
	    	exampleRange= Range.expandToInclude(exampleRange, 0);
	    	double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    }
	    
	    @Test
	    public void testExpandToInclude_baseRangeIsNull() {
	    	exampleRange= Range.expandToInclude(null, 0);
	    	double expectedUpper=0;
	    	double expectedLower=0;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	    }
	    
//	    test intersect method
	    @Test
	    public void testIntersect_TwoRangeIntersect() {
	    	Range exampleRange2=new Range(0,1.5);
	    	assertTrue("The two ranges should have intersect",exampleRange.intersects(exampleRange2));
	    }
	   
	    @Test
	    public void testIntersect_TwoRangeNotIntersect() {
	    	Range exampleRange2=new Range(1.5,2);
	    	assertFalse("The two ranges should have intersect",exampleRange.intersects(exampleRange2));
	    }
	    
	    @Test
	    public void testIntersect_RangeWithUpperSmallerThanExampleLower() {
	    	Range exampleRange2=new Range(-2,-1.0001);
	    	assertFalse("The two ranges should have intersect",exampleRange.intersects(exampleRange2));
	    }
	    @Test
	    public void testIntersect_RangeWithLowerBiggerThanExampleLowerSmallerThanExampleUpper() {
	    	Range exampleRange2=new Range(-0.5,2);
	    	assertTrue("The two ranges should have intersect",exampleRange.intersects(exampleRange2));
	    }
	    @Test
	    public void testIntersect_RangeWithUpperBiggerThanExampleLowerSmallerThanExampleUpper() {
	    	Range exampleRange2=new Range(-2,0.5);
	    	assertTrue("The two ranges should have intersect",exampleRange.intersects(exampleRange2));
	    }
	    
	    
	    
	   @Test
	   public void testCombine_Range1ParamIsNull() {
		   exampleRange=Range.combine(null, exampleRange);
		   double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	   }
	    
	   @Test
	   public void testCombine_Range2ParamIsNull() {
		   exampleRange=Range.combine(exampleRange,null);
		   double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	   }
	   
	   @Test
	   public void testcombineIgnoringNaN_Range1ParamIsNull() {
		   exampleRange=Range.combineIgnoringNaN(null, exampleRange);
		   double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	   }
	    
	   @Test
	   public void testcombineIgnoringNaN_Range2ParamIsNull() {
		   exampleRange=Range.combineIgnoringNaN(exampleRange,null);
		   double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	   }
	    
	   @Test
	   public void testcombineIgnoringNaN_TwoRangeIsNotNull() {
		   Range exampleRange2=new Range(0,5);
		   exampleRange=Range.combineIgnoringNaN(exampleRange,exampleRange2);
		   
		   double expectedUpper=5;
	    	double expectedLower=-1;
	    	assertEquals("The new upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The new lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
	    
	   }
	    
	   @Test
	   public void testHashCode_ObjectWithTheSameLowerAndUpperAsExampleRange_returnSameHashCode() {
		   Range exampleRange2=new Range(-1,1);
		   int actualHash=exampleRange2.hashCode();
		   int expectedHash=exampleRange.hashCode();
		   assertEquals("exampleRange1 and 2 should have the same hashcode ",expectedHash,actualHash,.000000001d);
	    	
	   }
	   
	   @Test
	   public void testEquals_RangeWithLowerNotEqualtoExampleRange_returnFalse() {
		   Range range2=new Range(-2,1);
		   assertFalse("Two Range not equals on lower bound",exampleRange.equals(range2));
	   }
	    
	   @Test
	   public void testEquals_RangeWithUpperNotEqualtoExampleRange_returnFalse() {
		   Range range2=new Range(-1,2);
		   assertFalse("Two Range not equals on Upper bound",exampleRange.equals(range2));
	   }
	   @Test
	   public void testEquals_randomObjectNotRangeClass_returnFalse() {
		   Object obj=new Object();
		   assertFalse("Two Range not equals on Upper bound",exampleRange.equals(obj));
	   }
	    
	   @Test
	   public void testGetCentralValue_exampleRange_returnCentervalue() {
		   double expected=0;
		   double actual=exampleRange.getCentralValue();
		   assertEquals("central value should be "+expected,expected,actual,.000000001d);
	    	
	   }
	   
	   @Test
	   public void testGetCentralValue_RangeWithNonZeroCentralPoint_returnCentervalue() {
		   Range range=new Range(2,5);
		   double expected=3.5;
		   double actual=range.getCentralValue();
		   assertEquals("central value should be "+expected,expected,actual,.000000001d);
	    	
	   }
	   
	   @Test
	   public void testMinFromCombine_RangeObjectWithLowerBoundNah() {
		   Range example2=new Range(Double.NaN,2);
		   Range result=Range.combineIgnoringNaN(exampleRange, example2);
		   double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The combined upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The combined lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
		   
	   }
	   
	   @Test
	   public void testMinFromCombine_RangeObjectWithUpperBoundNah() {
		   Range example2=new Range(2,Double.NaN);
		   Range result=Range.combineIgnoringNaN(exampleRange, example2);
		   double expectedUpper=1;
	    	double expectedLower=-1;
	    	assertEquals("The combined upper bound should be "+expectedUpper,expectedUpper,exampleRange.getUpperBound(),.000000001d);
	    	assertEquals("The combined lower bound should be "+expectedLower,expectedLower,exampleRange.getLowerBound(),.000000001d);
		   
	   }
	    
	    

	    @After
	    public void tearDown() throws Exception {
	    }

	    @AfterClass
	    public static void tearDownAfterClass() throws Exception {
	    }

}
