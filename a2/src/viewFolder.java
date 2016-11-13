package a2Package;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.activation.MimetypesFileTypeMap;

/**
 * To get and show image files from a directory( and its sub-directories), change them to 
 * an array list of Photos, and show their names
 * @author July Ju
 *
 */

public class viewFolder {

	/**
	 * show name of Photos 
	 * @param photoList
	 * 				the array list of Photo which name to be shown
	 * @return
	 * 			the array list containing names of Photos
	 */
	public static ArrayList<String> showContents(ArrayList<Photo> photoList) {
		
//		File folder = new File(picPath);
//		File[] listOfFiles = folder.listFiles();
//		ArrayList<String> nameList = new ArrayList<String>();
//
////        File f = new File(filepath);
////        String mimetype= new MimetypesFileTypeMap().getContentType(f);
////        String type = mimetype.split("/")[0];
////        if(type.equals("image"))
//		
//		for (File file : listOfFiles) {
//		    if (file.isFile()) {
//		    	String mimetype= new MimetypesFileTypeMap().getContentType(file);
//		    	String type = mimetype.split("/")[0];
//		    	if(type.equals("image")){
//		    		String each = file.getName();
//			        nameList.add(each);
//		    	}
//		    } if (file.isDirectory()) {
//		    	String newPath = file.getPath();
//		    	nameList.addAll(showContents(newPath));
//		    }
//		    
//		}
//		return nameList;
		ArrayList<String> nameList = new ArrayList<String>();
		for (Photo pic : photoList){
			String n = pic.getOrigName();
			nameList.add(n);			
		}
		return nameList;
	}
	
	/**
	 * To get image files from a path to this directory and return a list of Photo
	 * representation of them
	 * 
	 * @param picPath
	 * 				the path to that destination folder
	 * @return the array list of Photo representation of those image files
	 */
	
	public static ArrayList<Photo> getPhotoList(String picPath) {
		
		File folder = new File(picPath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<Photo> photoList = new ArrayList<Photo>();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	String mimetype= new MimetypesFileTypeMap().getContentType(file);
		    	String type = mimetype.split("/")[0];
		    	if(type.equals("image")){
		    		Photo pic = new Photo(file);
			        photoList.add(pic);
		    	}
		    } if (file.isDirectory()) {
		    	String newPath = file.getPath();
		    	photoList.addAll(getPhotoList(newPath));
		    }
		    
		}
		return photoList;
	}


	
	public static void main(String[] args) {
		
	    Desktop desktop = Desktop.getDesktop();
	    File dirToOpen = null;
	    try {
	        dirToOpen = new File("c:\\207a2images");
	        desktop.open(dirToOpen);
	    } catch (IOException e) {
	    	System.out.println("File Not Found");
	    	
	    }

	    ArrayList<Photo> aaa = getPhotoList("C:\\207a2images");
//		aaa.showContents();
//		 System.out.println(aaa);
		System.out.println(showContents(aaa));
	}
}


//do we need to deal with other files which are not images?
//probably throwing exceptions
//how we are supposed to show the image files without using the GUI
//when deleting a tag then it basically means two things(need to delete from all, but can call methods )
//use any exception you like to deal with children of either image file or directory
//or use for loop to check types before anything else

//rename method can be cut and paste names from the list that we already have or shown in the page
//to show files, your program is supposed to deal with one specific directory, either by letting MyPC to choose a folder in yoUR os
//then come back to your program, or to type in the path to the directory in your program and simply deal with that folder

