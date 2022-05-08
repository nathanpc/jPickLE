package com.innoveworkshop.pickle.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Representation of a category of components in a pick list.
 * 
 * @author Nathan Campos <nathan@innoveworkshop.com>
 */
public class Category {
	private String name;
	private ArrayList<Component> components;
	
	/**
	 * Creates an empty component category object.
	 */
	public Category() {
		name = null;
		components = new ArrayList<Component>();
	}
	
	/**
	 * Creates an empty component category with a name already defined.
	 * 
	 * @param name Name of the category.
	 */
	public Category(String name) {
		this();
		this.name = name;
	}
	
	/**
	 * Creates a pre-populated category with name and components.
	 * 
	 * @param name       Name of the category.
	 * @param components Components inside this category.
	 */
	public Category(String name, Collection<Component> components) {
		this(name);
		this.components.addAll(components);
	}
	
	/**
	 * Parses a category definition line in a pick list file and populates the
	 * object from it.
	 * 
	 * @param line Category definition line in a pick list file.
	 * 
	 * @throws ParseException If the line isn't a category line or something
	 *                        went wrong while trying to parse it.
	 */
	public void parseLine(String line) throws ParseException {
		// Make sure we have a category line before attempting to parse it.
		if (!isCategoryLine(line))
			throw new ParseException("Line isn't a category definition", 0);
		
		// This one is trivial. Just need to remove the colon at the end.
		name = line.substring(0, line.length() - 1);
	}
	
	/**
	 * Checks if a line is actually a category descriptor.
	 * 
	 * @param  line Line to be checked.
	 * @return      {@code true} if the line defines a category.
	 */
	public static boolean isCategoryLine(String line) {
		return line.charAt(line.length() - 1) == ':';
	}
	
	/**
	 * Gets the category name.
	 * 
	 * @return Category name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the category name.
	 * 
	 * @param name Category name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the components inside the category.
	 * 
	 * @return List of components.
	 */
	public ArrayList<Component> getComponents() {
		return components;
	}
	
	/**
	 * Sets the list of components that make this category.
	 * 
	 * @param components List of components.
	 */
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
	/**
	 * Adds a component to the list in this category.
	 * 
	 * @param component Component to be added.
	 */
	public void addComponent(Component component) {
		components.add(component);
	}
}
