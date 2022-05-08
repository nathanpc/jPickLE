package com.innoveworkshop.pickle.models;

import java.util.ArrayList;

/**
 * Representation of an electronic component in a pick list.
 * 
 * @author Nathan Campos <nathan@innoveworkshop.com>
 */
public class Component {
	private boolean picked;
	private String name;
	private String value;
	private String description;
	private String caseStyle;
	private ArrayList<String> refDes;
	
	/**
	 * Creates an empty component object.
	 */
	public Component() {
		// Set defaults.
		picked = false;
		name = "";
		value = null;
		description = null;
		caseStyle = null;
		refDes = new ArrayList<String>();
	}
	
	/**
	 * Creates a pre-populated component object with data from a descriptor line
	 * in a pick list file.
	 * 
	 * @param descLine Descriptor line in a pick list file.
	 */
	public Component(String descLine) {
		this();
		parseDescriptorLine(descLine);
	}
	
	/**
	 * Parses a descriptor line in a pick list file and populates the object.
	 * 
	 * @param line Descriptor line in a pick list file.
	 */
	public void parseDescriptorLine(String line) {
		// TODO: Do the parsing.
	}
	
	/**
	 * Parses a reference designator line and populates the object.
	 * 
	 * @param line Reference designator line in a pick list file.
	 */
	public void parseRefDesLine(String line) {
		String[] des = line.split("\\s");
		
		// Go through adding the reference designators.
		refDes.clear();
		for (int i = 0; i < des.length; i++) {
			refDes.add(des[i]);
		}
	}
	
	/**
	 * Gets the number of components to be picked up.
	 * 
	 * @return Amount of components to be picked up.
	 */
	public long getQuantity() {
		return refDes.size();
	}
	
	/**
	 * Has this component already been picked up?
	 * 
	 * @return {@code true} if it has been picked up.
	 */
	public boolean getPicked() {
		return picked;
	}
	
	/**
	 * Sets the picked state of the component.
	 * 
	 * @param picked Has the component been picked up?
	 */
	public void setPicked(boolean picked) {
		this.picked = picked;
	}
	
	/**
	 * Gets the manufacturer's part number of the component.
	 * 
	 * @return Component's part number.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the component part number.
	 * 
	 * @param name Manufacturer's part number.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the value of the component in case it has one. Otherwise
	 * {@code null}.
	 * 
	 * @return Value of the specific component or {@code null} if one wasn't
	 *         specified.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Sets the value of the component.
	 * 
	 * @param value Value of the specific component.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Gets a meaningful component description. Otherwise {@code null}.
	 * 
	 * @return Meaningful description or {@code null} if it wasn't specified.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets a meaningful description of the component.
	 * 
	 * @param description Meaningful description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the package type of the component. Otherwise {@code null}.
	 * 
	 * @return Package type of the component or {@code null} if it wasn't
	 *         specified.
	 */
	public String getPackage() {
		return caseStyle;
	}
	
	/**
	 * Sets the package type of the component.
	 * 
	 * @param caseStyle Package type.
	 */
	public void setPackage(String caseStyle) {
		this.caseStyle = caseStyle;
	}
	
	/**
	 * Gets the list of reference designators used for this component.
	 * 
	 * @return List of reference designators.
	 */
	public ArrayList<String> getReferenceDesignators() {
		return refDes;
	}
	
	/**
	 * Sets the list of reference designators for this component.
	 * 
	 * @param refDes List of reference designators.
	 */
	public void setReferenceDesignators(ArrayList<String> refDes) {
		this.refDes = refDes;
	}
}
