package a2Package;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Construct Tag for the system
 */
public class Tag {
	
	/**
	 * the name of this new Tag
	 */
	
	private String name;
	// add a static variable tag list
	
	/**
	 * Construct a tag with type Tag, named name
	 *
	 * @param name
	 *            the tag name 
	 */
	public Tag(String name){
		this.name = name;
	}
	
	/**
	 * @return the name of this tag
	 */
	public String getName(){
		return this.name;
	}

    
	public static void main(String[] args) throws FileNotFoundException {	
	
	PrintWriter outputfile = new PrintWriter("logger");
//    outputfile.print("your output" + "\n");
//    outputfile.print("second line" + "\n");
//    outputfile.print("third line" + "\n");
	outputfile.append("trying" + "\n");
//	outputfile.append("\n");
	outputfile.append("is this working?");
	outputfile.append("blahblah");
	
    outputfile.close();
    System.out.println("hhhhh");
	}

}
