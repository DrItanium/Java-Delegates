/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dritanium.delegates;

import junit.framework.TestCase;

/**
 *
 * @author jwscoggins
 */
public class FunctionClassTest extends TestCase {
	
	public FunctionClassTest(String testName) {
		super(testName);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testFunction4() {
		//testing the function classes
		AbstractFunction4<Integer,Integer,Integer,Integer,Integer> fn = 
			new AbstractFunction4<Integer, Integer, Integer, Integer, Integer>() {

			public Integer invoke(Integer a0, Integer a1, Integer a2, Integer a3) {
				return a0.intValue() + a1.intValue() + a2.intValue() + a3.intValue();
			}
		};
		assertEquals("Function4: (+ 2 2 2 2) = 8", 8, fn.invoke(2,2,2,2).intValue());
		assertEquals("Function4 w/ type checking: (+ 2 2 2 2) = 8", 8, fn.invoke(new Object[] { 2,2,2,2 }));
		Function3<Integer,Integer,Integer,Integer> curriedFn1 = fn.curry(2);
		int resultCFN1 = curriedFn1.invoke(2,2,2).intValue();
		assertEquals("Function4 as Function3: (+ 2 2 2 2) = 8", 8, resultCFN1);
		Integer resultCFN1_Type = (Integer)curriedFn1.invoke(new Object[] { 2, 2, 2 });
		assertEquals("Function4 as Function3 w/ type checking: (+ 2 2 2 2) = 8", 8, resultCFN1_Type.intValue());
		Function2<Integer,Integer,Integer> curriedFn2 = curriedFn1.curry(2);
		int resultCFN2 = curriedFn2.invoke(2, 2);
		assertEquals("Function4 as Function3 as Function2: (+ 2 2 2 2) = 8", 8, resultCFN2);
		int resultCFN2_Type = ((Integer)curriedFn2.invoke(new Object[] { 2, 2})).intValue();
		assertEquals("Function4 as Function3 as Function2 w/ type checking : (+ 2 2 2 2) = 8", 8, resultCFN2_Type);
		Function<Integer,Integer> curriedFn3 = curriedFn2.curry(2);
		int resultCFN3 = curriedFn3.invoke(2);
		assertEquals("Function4 as Function3 as Function2 as Function1: (+ 2 2 2 2) = 8", 8, resultCFN3);
		int resultCFN3_Type = ((Integer)curriedFn3.invoke(2)).intValue();
		assertEquals("Function4 as Function3 as Function2 as Function1 w/ type checking: (+ 2 2 2 2) = 8", 8, resultCFN3_Type);
		Function0<Integer> curriedFn4 = curriedFn3.curry(2);
		int resultCFN4 = curriedFn4.invoke();
		assertEquals("Function4 as Function3 as Function2 as Function1 as Function0: (+ 2 2 2 2) = 8", 8, resultCFN4);
		int resultCFN4_Type = ((Integer)curriedFn4.invoke(new Object[0])).intValue();
		assertEquals("Function4 as Function3 as Function2 as Function1 as Function0 w/ type checking: (+ 2 2 2 2) = 8", 8, resultCFN4_Type);
	}
	// TODO add test methods here. The name must begin with 'test'. For example:
	// public void testHello() {}
}
