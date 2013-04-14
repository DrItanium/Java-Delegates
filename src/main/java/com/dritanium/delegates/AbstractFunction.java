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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dritanium.delegates;

/**
 * A single input function
 * @author Joshua Scoggins
 */
public abstract class AbstractFunction<T0,R> extends DefaultAbstractIntelligentDelegate implements Function<T0,R> {
	public AbstractFunction() {
		super(new Class[]{
			((T0)new Object()).getClass()
		});
	}

	@Override
	protected Object invokeImpl(Object[] input) {
		return invoke((T0)input[0]);
	}

	public Function0<R> curry(T0 input) {
		final NonLocalClosure<T0> in = new NonLocalClosure<T0>(input);
		final NonLocalClosure<AbstractFunction<T0,R>> fn = 
			new NonLocalClosure<AbstractFunction<T0, R>>(this);
		return new AbstractFunction0<R>() {
			T0 value = in.getActualValue();
			AbstractFunction<T0,R> func = fn.getActualValue();
			public R invoke() {
				return func.invoke(value);
			}
		};
	}


	
}
