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

/**
 * A two argument function
 * @author Joshua Scoggins 
 */
public abstract class AbstractFunction2<T0,T1,R> extends DefaultAbstractIntelligentDelegate implements Function2<T0,T1,R> {
	public AbstractFunction2() {
		super(new Class[] {
		  ((T0)new Object()).getClass(),
		  ((T1)new Object()).getClass()
		});
	}

	@Override
	protected Object invokeImpl(Object[] input) {
		return invoke((T0)input[0], (T1)input[1]);
	}

	public Function<T0, R> curry(T1 a1) {
		final NonLocalClosure<T1> val = new NonLocalClosure<T1>(a1);
		final NonLocalClosure<AbstractFunction2<T0,T1,R>> fn = 
			new NonLocalClosure<AbstractFunction2<T0,T1,R>>(this);
		return new AbstractFunction<T0,R>() {
			//"Transfer"
			T1 value = val.getActualValue();
			AbstractFunction2<T0,T1,R> func = fn.getActualValue();
			public R invoke(T0 a0) {
				return func.invoke(a0, value);
			}
		};
	}

	
	
}
