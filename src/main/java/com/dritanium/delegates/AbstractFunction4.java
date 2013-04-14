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

import com.dritanium.indirection.NonLocalClosure;

/**
 * Represents a partial implementation of a four argument function
 * @author Joshua Scoggins 
 */
public abstract class AbstractFunction4<T0,T1,T2,T3,R> extends DefaultAbstractIntelligentDelegate implements Function4<T0,T1,T2,T3,R> {
	public AbstractFunction4() {
		super(new Class[] { 
			((T0)new Object()).getClass(),
			((T1)new Object()).getClass(),
			((T2)new Object()).getClass(),
			((T3)new Object()).getClass() });
	}

	@Override
	protected Object invokeImpl(Object[] input) {
		return invoke((T0)input[0],
			(T1)input[1],
			(T2)input[2],
			(T3)input[3]);
	}

	public Function3<T0, T1, T2, R> curry(T3 value) {
		final NonLocalClosure<T3> input = new NonLocalClosure<T3>(value);
		final NonLocalClosure<AbstractFunction4<T0,T1,T2,T3,R>> fn =
			new NonLocalClosure<AbstractFunction4<T0,T1,T2,T3,R>>(this);
		return new AbstractFunction3<T0,T1,T2,R>() {
			T3 closedValue = input.getActualValue();
			AbstractFunction4<T0,T1,T2,T3,R> func = fn.getActualValue();
			public R invoke(T0 a0, T1 a1, T2 a2) {
				return func.invoke(a0, a1, a2, closedValue);
			}
		};
	}

		
			
}
