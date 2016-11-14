package a2Package;


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

    
	public static void main(String[] args) {	
		
	}

}
