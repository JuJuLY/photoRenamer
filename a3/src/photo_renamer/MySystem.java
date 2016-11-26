package photo_renamer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


import javax.activation.MimetypesFileTypeMap;

/**
 * the system of this program, which contains all the existing tags that can be shown and chose by user,
 * and keeps logs of every renaming of any Photo, as well as keeps track of tags
 * @author July Ju
 *
 */

public class MySystem {
	
	/**
	 * ArrayList of all available Tags
	 */
	public static ArrayList<Tag> existingTags = new ArrayList<Tag>();
	/**
	 * A logger to keep track of all Tags
	 */
	private static final Logger tagCollection = Logger.getLogger(MySystem.class.getName());
	/**
	 * A logger to keep track of all renaming actions
	 */
	static final Logger renamingLog = Logger.getLogger(MySystem.class.getName());
	/**
	 * ArrayList of all the photos in this directory
	 */
	public static ArrayList<Photo> systemPhotoList = new ArrayList<Photo>();
	
	
	/**
	 * Construct a MySystem object with path systemPath
	 * @param name 
	 *			set name for this Photo
	 */
//	public MySystem(String systemPath){
//		this.systemPhotoList = ViewFolder.getPhotoList(systemPath);
//	}

	
	public MySystem(){
		
	}
	
	/**
	 * create and edit a text file that can keep track of all the available tags
	 * @param msg
	 * 			the name of the Tag ( or Tags) that would be added
	 * @throws IOException
	 */
	public static void writeToTagFile(String msg) throws IOException{

		try {  
		    FileWriter outFile = new FileWriter("C:/Users/July Ju/Desktop/uoftyear2/csc207/group_0633/a3/tagcollectionfile.txt");  
		    PrintWriter out = new PrintWriter(outFile);  
		    out.append(msg);  
		    out.close();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}
	}
	
	
	/**
	 * show tag collection of all existing tags
	 * @return
	 */
	
	public static String showTagCollection(){
		 String allTags = "";
//		 System.out.println("hhhhhhhhhh" + existingTags);
		 for (Tag element: existingTags){
			 allTags = allTags + element.getName() + "\n";
		 }
		 return allTags;
	}
	
	/**
	 * 
	 * @param createdTag
	 * 				tell the system that the Tag createdTag was created
	 * 				and add this tag to existingTags
	 * @throws SecurityException 
	 * @throws IOException
	 */
	
	public static void createTag(Tag createdTag) throws SecurityException, IOException{
		existingTags.add(createdTag);		
		writeToTagFile(showTagCollection());
		
	}
	
	/**
	 * 
	 * @param deletedTag
	 * 				tell the system that the Tag deletedTag was created
	 * 				and remove this Tag from existingTags
	 * @throws SecurityException 
	 * @throws IOException
	 */
	public static void removeTag(Tag deletedTag) throws SecurityException, IOException{
		existingTags.remove(deletedTag);
		writeToTagFile(showTagCollection());
	}
	
	/**
	 * If a Tag is removed from the system, then also remove this Tag from any Photo
	 * that has this Tag and rename them 
	 * 
	 * @param photoList
	 * 				the list of Photo that would be checked if any of them contains this Tag
	 * @param deletedTag
	 * 				the Tag that would be deleted from the system
	 * @throws SecurityException
	 * @throws IOException
	 */
	
	public static void removeTagFromAll(Tag deletedTag) throws SecurityException, IOException{
//		removeTag(deletedTag);
		for (Photo each : MySystem.systemPhotoList){
			if (each.tagList.contains(deletedTag)){
				each.deleteTag(deletedTag);
			}
		}	
		removeTag(deletedTag);
	}
	
	
	/**return the existingTags which contains all the available tags
	 * 
	 * @return return existingTags
	 */
	
	public static ArrayList<Tag> getExistingTags() {
		return existingTags;
	}
	
	/**
	 * set existingTags to this new array list of Tags
	 * @param existingTags
	 */

//	public void setExistingTags(ArrayList<Tag> existingTags) {
//		this.existingTags = existingTags;
//	}

	public static void main(String args[]) throws SecurityException, IOException {
//		
//	Tag tag1 = new Tag("castle");
//	Tag tag2 = new Tag("buildings");
//	Tag tag3 = new Tag("sweets");
//	Tag tag4 = new Tag("holidays");
//	Tag tag5 = new Tag("forests");
//	MySystem.createTag(tag1);
//	MySystem.createTag(tag2);
//	MySystem.createTag(tag3);
//	MySystem.createTag(tag4);
//	MySystem.createTag(tag5);
//	System.out.println(MySystem.systemPhotoList);
//	System.out.println(MySystem.showTagCollection());	
//	MySystem.removeTag(tag2);
//	System.out.println(MySystem.showTagCollection());
//	MySystem.removeTagFromAll(tag3);
//	System.out.println(MySystem.showTagCollection());
//		
	}

}
