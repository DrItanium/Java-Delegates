//Copyright 2012-2013 Joshua Scoggins. All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are
//permitted provided that the following conditions are met:
//
//   1. Redistributions of source code must retain the above copyright notice, this list of
//      conditions and the following disclaimer.
//
//   2. Redistributions in binary form must reproduce the above copyright notice, this list
//      of conditions and the following disclaimer in the documentation and/or other materials
//      provided with the distribution.
//
//THIS SOFTWARE IS PROVIDED BY Joshua Scoggins ``AS IS'' AND ANY EXPRESS OR IMPLIED
//WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
//FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Joshua Scoggins OR
//CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
//CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
//ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
//NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
//ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//
//The views and conclusions contained in the software and documentation are those of the
//authors and should not be interpreted as representing official policies, either expressed
//or implied, of Joshua Scoggins. 
package com.dritanium.delegates;

import junit.framework.TestCase;

/**
 * Tests the IntelligentDelegate interface and associated classes
 * @author Joshua Scoggins 
 */
public class IntelligentDelegateTest extends TestCase {
	
	public IntelligentDelegateTest(String testName) {
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
	public void testIntelligentDelegate() {
		DefaultAbstractIntelligentDelegate test = new 
			DefaultAbstractIntelligentDelegate(new Class[] {
				Integer.class,
				Integer.class
			}) {

			@Override
			protected Object invokeImpl(Object[] input) {
				Integer a = (Integer)input[0];
				Integer b = (Integer)input[1];
				return a.intValue() + b.intValue();
			}
		};
		Integer result = (Integer)test.invoke(new Object[]{2,2});
		assertEquals("(invoke) 2 + 2 = 4", 4, result.intValue());
		boolean caughtIncorrectArgs = false;
		try {
			//this should just fail
			test.invoke(new Object[]{2,"donuts"});
		} catch(TypeCheckingException e) {
			caughtIncorrectArgs = true;
		}
		assertTrue("Incorrect arguments were found by delegate", caughtIncorrectArgs);
		test.setInput(new Object[] { 2, 2 });
		test.run();
		result = (Integer)test.getResult();
		assertEquals("(run) 2 + 2 = 4", 4, result.intValue());

	}
}
