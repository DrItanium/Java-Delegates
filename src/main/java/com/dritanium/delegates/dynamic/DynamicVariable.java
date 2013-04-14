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

/**
 * A class representing a key/value pair used to define dynamic variables that 
 * can be accessed at runtime. 
 * @author Joshua Scoggins 
 */
public class DynamicVariable<T> implements Cloneable{
	private T value;
	private String name;
	private boolean readonly;
	public T getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setValue(T value) {
		if(!readonly || this.value == null) {
			this.value = value;
		} else {
			throw new DynamicVariableReadonlyException();
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIsReadonly(boolean isReadonly) {
		readonly = isReadonly;
	}
	protected DynamicVariable(String name, T value, boolean isReadonly) {
		this.name = name;
		this.value = value;
		this.readonly = isReadonly;
	}
	protected DynamicVariable(String name, T value) {
		this(name, value, false);
	}
	protected DynamicVariable(String name) {
		this(name, null);
	}
	protected DynamicVariable(DynamicVariable<T> dv)  {
		this(dv.name, dv.value, dv.readonly);
	}
	@Override
	public Object clone() {
		return new DynamicVariable<T>(this);
	}
	@Override
	public boolean equals(Object other) {
		DynamicVariable<T> dv = (DynamicVariable<T>)other;
		return dv.name.equals(name) &&
			dv.readonly == readonly &&
			dv.value.equals(value);
	}
	
	@Override
	public int hashCode() {
		//autogenerated by netbeans for me
		//this is a really wierd hash
		int hash = 7;
		hash = 17 * hash + (this.value != null ? this.value.hashCode() : 0);
		hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 17 * hash + (this.readonly ? 1 : 0);
		return hash;
	}
}