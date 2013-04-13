/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dritanium.delegates;

import com.dritanium.indirection.Closure;
import com.dritanium.indirection.NonLocalClosure;
import junit.framework.TestCase;

/**
 *
 * @author jwscoggins
 */
public class ClosureTest extends TestCase {
	
	public ClosureTest(String testName) {
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
	// TODO add test methods here. The name must begin with 'test'. For example:
	// public void testHello() {}
	public void testClosures() {
		int increment = 32;
		final Closure<Integer> factor = new Closure<Integer>(increment);
		final Closure<Integer> count = new Closure<Integer>(0);
		final Closure<Integer> output = new Closure<Integer>(0);
		Delegate d = new Delegate() {
			//close over the counter indirectly, this will make it
			//modifiable
			final NonLocalClosure<Integer> counter = new NonLocalClosure<Integer>(count);
			final NonLocalClosure<Integer> outputStorage = new NonLocalClosure<Integer>(output);
			public Object invoke(Object[] input) {
				//take in zero args
				if(input.length > 0) {
					throw new RuntimeException("Too may arguments provided");
				} else {
				  Integer oldValue = counter.getActualValue();
				  counter.setActualValue(oldValue + factor.getValue());
				  return oldValue;
				}

			}

			public void run() {
				outputStorage.setActualValue((Integer)invoke(new Object[0]));
			}
		};
		assertEquals("(invoke) Counter was 0", 0, d.invoke(new Object[0]));
		assertEquals("(invoke) Counter was 32", 32, d.invoke(new Object[0]));
		assertEquals("(invoke) Counter was 64", 64, d.invoke(new Object[0]));
		d.run();
		assertEquals("(run) Counter was 96", 96, output.getValue().intValue());
		assertEquals("(check) Counter is 128", 128, count.getValue().intValue());
	}
}
