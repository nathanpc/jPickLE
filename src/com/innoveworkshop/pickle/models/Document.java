package com.innoveworkshop.pickle.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representation of a pick list document.
 * 
 * @author Nathan Campos <nathan@innoveworkshop.com>
 */
public class Document {
	private ArrayList<Category> categories;
	
	/**
	 * Creates an empty pick list document object.
	 */
	public Document() {
		categories= new ArrayList<Category>();
	}
	
	/**
	 * Creates a pre-populated pick list document with a list of categories.
	 * 
	 * @param categories Categories to have populated in the document object.
	 */
	public Document(Collection<Category> categories) {
		this();
		this.categories.addAll(categories);
	}
	
	/**
	 * Tries to get a list of component of a category by the category name. If
	 * a category with that name isn't found, this returns {@code null}.
	 * 
	 * @param  name Name of the category to search for.
	 * @return      Components inside the category or {@code null}.
	 */
	public ArrayList<Component> getComponentsByCategoryName(String name) {
		// Go through the categories list trying to find one that is a match.
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getName().equalsIgnoreCase(name))
				return categories.get(i).getComponents();
		}
		
		// We weren't able to find any categories with that name.
		return null;
	}
	
	/**
	 * Gets the list of categories that this document has.
	 * 
	 * @return List of component categories.
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	/**
	 * Sets the list of categories in this document.
	 * 
	 * @param categories List of component categories.
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
}
