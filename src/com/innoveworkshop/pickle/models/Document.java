package com.innoveworkshop.pickle.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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
	 * Indicates in which stage of parsing a line we are.
	 */
	protected enum ParseStage {
		EMPTY,
		CATEGORY,
		COMPONENT_DESCRIPTOR,
		COMPONENT_REFDES
	}

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
	 * Creates a pre-populated pick list document object from a file reader.
	 * 
	 * @param fr File reader to use for parsing.
	 * 
	 * @throws IOException    If some issue occurred while reading the streams.
	 * @throws ParseException If there is a problem with the file and the we
	 *                        couldn't parse it.
	 */
	public Document(FileReader fr) throws IOException, ParseException {
		this();
		parseFile(fr);
	}

	/**
	 * Parses a pick list document and populates the object with its contents.
	 * 
	 * @param fr File reader to use for parsing.
	 * 
	 * @throws IOException    If some issue occurred while reading the streams.
	 * @throws ParseException If there is a problem with the file and the we
	 *                        couldn't parse it.
	 */
	public void parseFile(FileReader fr) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(fr);
		ParseStage stage = ParseStage.EMPTY;

		try {
			String line = null;
			Category category = null;
			Component component = null;

			// Go through the document parsing each line.
			while ((line = br.readLine()) != null) {
				switch (stage) {
				case EMPTY:
					if (Component.isDescriptorLine(line)) {
						// Make sure we have a category defined.
						if (category == null) {
							throw new ParseException(
									"Can't have a component defined before a category", 0);
						}
						
						// Change the stage and parse a new component.
						component = new Component(line);
						stage = ParseStage.COMPONENT_REFDES;
						continue;
					} else if (Category.isCategoryLine(line)) {
						// Check if we need to commit our parsed category first.
						if (category != null)
							addCategory(category);

						// Create the new category.
						category = new Category();
						category.parseLine(line);
						stage = ParseStage.EMPTY;
						continue;
					} else if (line.isEmpty()) {
						// Just another empty line...
						continue;
					}
					break;
				case COMPONENT_REFDES:
					// Looks like we've finished parsing this component.
					if (line.isBlank()) {
						// Add component to the category.
						category.addComponent(component);
						
						// Reset everything.
						component = null;
						stage = ParseStage.EMPTY;
						continue;
					}

					// Parse the reference designators.
					component.parseRefDesLine(line);
					stage = ParseStage.EMPTY;
					continue;
				default:
					// Continue below...
					break;
				}
			}
			
			// Make sure the last parsed category is accounted for.
			if (category != null)
				addCategory(category);
		} finally {
			fr.close();
			br.close();
		}
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
	
	/**
	 * Adds a category to the categories list.
	 * 
	 * @param category Component category.
	 */
	public void addCategory(Category category) {
		categories.add(category);
	}
}
