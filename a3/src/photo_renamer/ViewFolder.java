package photo_renamer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.activation.MimetypesFileTypeMap;

import photo_renamer.Photo;

/**
 * To get and show image files from a directory( and its sub-directories),
 * change them to an array list of Photos, and show their names
 * 
 * @author July Ju
 *
 */

public class ViewFolder {

	/**
	 * show name of Photos
	 * 
	 * @param photoList
	 *            the array list of Photo which name to be shown
	 * @return the array list containing names of Photos
	 */
	public static ArrayList<String> showContents(ArrayList<Photo> photoList) {

		// File folder = new File(picPath);
		// File[] listOfFiles = folder.listFiles();
		// ArrayList<String> nameList = new ArrayList<String>();
		//
		//// File f = new File(filepath);
		//// String mimetype= new MimetypesFileTypeMap().getContentType(f);
		//// String type = mimetype.split("/")[0];
		//// if(type.equals("image"))
		//
		// for (File file : listOfFiles) {
		// if (file.isFile()) {
		// String mimetype= new MimetypesFileTypeMap().getContentType(file);
		// String type = mimetype.split("/")[0];
		// if(type.equals("image")){
		// String each = file.getName();
		// nameList.add(each);
		// }
		// } if (file.isDirectory()) {
		// String newPath = file.getPath();
		// nameList.addAll(showContents(newPath));
		// }
		//
		// }
		// return nameList;

		ArrayList<String> nameList = new ArrayList<String>();
		for (Photo pic : photoList) {
			String n = pic.getOrigName();
			nameList.add(n);
		}
		return nameList;
	}

	/**
	 * To get image files from a path to this directory and return a list of
	 * Photo representation of them
	 * 
	 * @param picPath
	 *            the path to that destination folder
	 * @return the array list of Photo representation of those image files
	 */

	public static ArrayList<Photo> getPhotoList(String picPath) {

		File folder = new File(picPath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<Photo> photoList = new ArrayList<Photo>();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String mimetype = new MimetypesFileTypeMap().getContentType(file);
				String type = mimetype.split("/")[0];
				if (type.equals("image")) {
					Photo pic = new Photo(file);
					photoList.add(pic);
				}
			}
			if (file.isDirectory()) {
				String newPath = file.getPath();
				photoList.addAll(getPhotoList(newPath));
			}

		}
		System.out.println(photoList);
		MySystem.systemPhotoList.addAll(photoList);
		return photoList;
	}

	public static void main(String[] args) {

		Desktop desktop = Desktop.getDesktop();
		File dirToOpen = null;
		try {
			dirToOpen = new File("C:/207a2images/sweets");
			desktop.open(dirToOpen);
		} catch (IOException e) {
			System.out.println("File Not Found");

		}

		ArrayList<Photo> aaa = getPhotoList("C:/207a2images/sweets");
		System.out.println(showContents(aaa));
	}
}
