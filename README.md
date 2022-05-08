# Java Library for PickLE

Provides an easy way to deal with [PickLE](https://github.com/nathanpc/pickle)
pick list files using Java.


## Example

Here's a simple example of how you can use this library to parse a pick list
document and display its contents:

```java
// Parse the pick list file.
doc = new Document(new FileReader(args[0]));

// Print out the parsed contents.
ArrayList<Category> categories = doc.getCategories();
for (int i = 0; i < categories.size(); i++) {
	Category category = categories.get(i);
	System.out.println(category.getName() + ":");

	// Print the components.
	ArrayList<Component> components = category.getComponents();
	for (int j = 0; j < components.size(); j++) {
		printComponent(components.get(j));
	}

	System.out.println();
}
```

More examples can be found in the [examples folder](/src/com/innoveworkshop/pickle/example/]
of the project.


## License

This project is licensed under the [MIT License](/LICENSE).
