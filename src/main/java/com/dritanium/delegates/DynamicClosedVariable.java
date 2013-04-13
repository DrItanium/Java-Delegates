/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dritanium.delegates;

/**
 * Provides a quick way to define ClosedVariables that contain a DynamicVariable 
 * @author jwscoggins
 */
public class DynamicClosedVariable extends ClosedVariable<DynamicVariable> {
	public void setName(String name) {
		getValue().setName((name));
	}
	public String getName() {
		return super.getValue().getName();
	}
	public Object getVariableValue() {
		return super.getValue().getValue();
	}
	public boolean isReadonly() {
		return super.getValue().isReadonly();
	}
	public DynamicClosedVariable(DynamicVariable dv) {
		super(dv);
	}	
	public DynamicClosedVariable(String name, Object value) {
		this(new DynamicVariable(name, value, true));
	}
	public DynamicClosedVariable(DynamicClosedVariable dcv) {
		super((ClosedVariable<DynamicVariable>)dcv);
	}

}
