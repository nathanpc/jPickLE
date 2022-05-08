package com.innoveworkshop.pickle.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.innoveworkshop.pickle.models.Category;
import com.innoveworkshop.pickle.models.Component;
import com.innoveworkshop.pickle.models.Document;

/**
 * Briefly shows how to use this library.
 * 
 * @author Nathan Campos <nathan@innoveworkshop.com>
 */
public class ExampleUsage {
	public static Document doc;
	
	/**
	 * Application's main entry point.
	 * 
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		String filePath = null;
		
		// Check if we have a command-line argument.
		if (args.length == 0) {
			filePath = "/home/nathanpc/src/pickle/examples/example.pkl";
			/*
			System.out.println("Missing the path to a PickLE pick list file as an argument");
			return;
			*/
		} else {
			filePath = args[0];
		}
		
		try {
			// Parse the pick list file.
			doc = new Document(new FileReader(filePath));
			
			// Print out the parsed contents.
			ArrayList<Category> categories = doc.getCategories();
			for (int i = 0; i < categories.size(); i++) {
				Category category = categories.get(i);
				System.out.println(category.getName() + ":");
				
				// Print the components.
				ArrayList<Component> components = category.getComponents();
				for (int j = 0; j < components.size(); j++) {
					Component component = components.get(j);
					
					System.out.print("    ");
					System.out.print(component.getName());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
