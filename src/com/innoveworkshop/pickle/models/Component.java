package com.innoveworkshop.pickle.models;

import java.text.ParseException;
import java.util.ArrayList;

import com.google.code.regexp.Matcher;
import com.google.code.regexp.Pattern;

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
	 * 
	 * @throws ParseException  If something went wrong while trying to parse the
	 *                         line.
	 */
	public Component(String descLine) throws ParseException {
		this();
		parseDescriptorLine(descLine);
	}
	
	/**
	 * Parses a descriptor line in a pick list file and populates the object.
	 * 
	 * @param line Descriptor line in a pick list file.
	 * 
	 * @throws ParseException If the line isn't a descriptor line or something
	 *                        went wrong while trying to parse the it.
	 */
	public void parseDescriptorLine(String line) throws ParseException {
		// Make sure we actually have a descriptor line before parsing.
		if (!isDescriptorLine(line))
			throw new ParseException("Line isn't a valid component descriptor", 0);
		
		// Parse the descriptor line.
		Pattern regex = Pattern.compile("\\[(?<picked>.)\\]\\s+(?<quantity>\\d+)\\s+(?<name>[^\\s]+)\\s*(\\((?<value>[^\\)]+)\\)\\s*)?(\"(?<description>[^\\\"]+)\"\\s*)?(\\[(?<case>[^\\]]+)\\]\\s*)?");
		Matcher m = regex.matcher(line);
		if (!m.find())
			throw new ParseException("Line didn't match our regular expression", 0);
		
		// Populate the mandatory attributes of the object.
		picked = !m.group("picked").equals(" ");
		name = m.group("name");
		
		// Component value.
		try {
			value = m.group("value");
		} catch (IndexOutOfBoundsException ignored) { }

		// Component description.
		try {
			description = m.group("description");
		} catch (IndexOutOfBoundsException ignored) { }
		
		// Component case.
		try {
			caseStyle = m.group("case");
		} catch (IndexOutOfBoundsException ignored) { }
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
	 * Checks if a line is actually of the descriptor variety.
	 * 
	 * @param  line Line to be checked.
	 * @return      {@code true} if the line is a descriptor line.
	 */
	public static boolean isDescriptorLine(String line) {
		if (line.length() == 0)
			return false;

		return line.charAt(0) == '[';
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
	
	/**
	 * Adds a reference designator to the reference designator's list.
	 * 
	 * @param ref Reference designator to be added to the list.
	 */
	public void addReferenceDesignator(String ref) {
		refDes.add(ref);
	}
}
