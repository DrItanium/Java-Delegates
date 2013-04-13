/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dritanium.delegates;

/**
 * Provides a quick way to define ClosedVariables that contain a DynamicVariable 
 * @author jwscoggins
 */
public class ClosedDynamicVariable extends ClosedVariable<DynamicVariable> {
	public String getName() {
		return super.getValue().getName();
	}
	public Object getVariableValue() {
		return super.getValue().getValue();
	}
	public boolean isReadonly() {
		return super.getValue().isReadonly();
	}
	public ClosedDynamicVariable(DynamicVariable dv) {
		super(dv);
	}	
	public ClosedDynamicVariable(String name, Object value) {
		this(new DynamicVariable(name, value, true));
	}
	public ClosedDynamicVariable(ClosedDynamicVariable dcv) {
		super((ClosedVariable<DynamicVariable>)dcv);
	}

}
