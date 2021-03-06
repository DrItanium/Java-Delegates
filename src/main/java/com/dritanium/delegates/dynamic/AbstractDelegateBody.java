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
package com.dritanium.delegates.dynamic;
import com.dritanium.delegates.NonLocalClosure;
import com.dritanium.delegates.dynamic.DelegateBody;
import com.dritanium.delegates.dynamic.DynamicDelegate;
import static com.dritanium.delegates.dynamic.FunctionalOperations.*;

/**
 * An implementation of the DelegateBody interface.
 * This class is meant to be used directly in a new instance.
 * @author Joshua Scoggins 
 * @param <T>  The type of the value stored in the return container
 */
public abstract class AbstractDelegateBody<T> implements DelegateBody<T> {

	private NonLocalClosure<T> returnContainer;

	public NonLocalClosure<T> getReturnContainer() {
		return returnContainer;
	}

	public void setReturnContainer(NonLocalClosure<T> returnContainer) {
		this.returnContainer = returnContainer;
	}

	public AbstractDelegateBody(NonLocalClosure<T> returnValue) {
		setReturnContainer(returnValue);
	}

	public AbstractDelegateBody() {
		this(null);
	}

	public abstract Object invoke(DynamicDelegate localVariables);

	public void run(DynamicDelegate localVariables) {
		if (returnContainer == null) {
			invoke(localVariables);
		} else {
			setNonLocalVariable(returnContainer, (T) invoke(localVariables));
		}
	}
}
