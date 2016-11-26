package photo_renamer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Photo {
	/**
	 * the image file pointed by this photo
	 */
	public File name;
	/**
	 * the original name of this photo file
	 */
	private String origName;
	/**
	 * the tagList of this Photo which contains all the tags it has
	 */
	public ArrayList<String> tagList = new ArrayList<String>();
	/**
	 * the nameLog of this Photo which contains all its previous names
	 */
	private ArrayList<File> nameLog = new ArrayList<File>();
	
	/**
	 * Construct a photo from an image file photoFile
	 * @param photoFile 
	 *			Construct a Photo object from an image file, set name for this Photo and add name to its nameLog
	 */
	
	public Photo(File photoFile){
		this.name = photoFile;
		nameLog.add(photoFile);
		this.origName = this.name.getName();
		MySystem.systemPhotoList.add(this);
	}
	
	/**
	 * Get the original name of the Photo file
	 * @return the original name
	 */
	
	public String getOrigName(){
		return this.origName;
	}
	
	/**
	 * show all old names from the nameLog of this photo
	 */
	
	public void showAllNames(){
		for (File name: nameLog){
			System.out.println(name.getName());
		}
	}
	
	/**
	 * When add or delete tags on a photo, rename this photo to include 
	 * the tag names and indicated by "@" to make it available to be 
	 * searched by the OS
	 * @param newName  this image file
	 * @throws SecurityException
	 * @throws IOException
	 */
	
	public void renamePhoto(String newName) throws SecurityException, IOException{
		FileHandler myfh = new FileHandler("C:/Users/July Ju/Desktop/uoftyear2/csc207/group_0633/a3/renaminglogfile.log");
		SimpleFormatter myfmt = new SimpleFormatter();
		MySystem.renamingLog.addHandler(myfh);
		myfh.setFormatter(myfmt);
		MySystem.renamingLog.info("OldName is: " + this.name.getName() + " , " + "NewName is: " + newName);
		File newNameFile = new File(this.name.getParent(), newName);
		this.name.renameTo(newNameFile);
		this.name = newNameFile;
		nameLog.add(newNameFile);

	}
	
	
	/**
	 * Add a new Tag to this photo, automatically rename the photo
	 * and also make this Tag shown in the tagList of this photo
	 * 
	 * @param newTag
	 * 				the new Tag that would be added to this photo
	 * @return 
	 * @throws SecurityException
	 * @throws IOException
	 */
	
	
	public void addTag(Tag newTag) throws SecurityException, IOException{
		this.tagList.add(newTag.getName());
//		System.out.println("kkkkkkkk" + MySystem.existingTags);
		if (!MySystem.showTagCollection().contains(newTag.getName() + "\n")){
			MySystem.createTag(newTag);			
		}
		String newFileName = new String("@" + newTag.getName() + this.name.getName());
		renamePhoto(newFileName);
		
	}
	
	
	/**
	 * Delete an existing Tag from this photo, automatically rename the photo
	 * and also remove this Tag from the tagList of this photo
	 * 
	 * @param deletedTag
	 * 				the Tag that would like to be deleted from this Photo
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	
	public void deleteTag(Tag deletedTag) throws SecurityException, IOException{
//		this.tagList.remove(deletedTag);
//		String newName = this.name.getName();
//		for(Tag element: tagList){
//			newName = newName + "@" + element.getName();
//		}
//		String shouldDelete = "@" + deletedTag.getName();
		
//		ArrayList<Tag> newTagList = new ArrayList<Tag>(this.tagList);
		String newName = this.getOrigName(); 
		this.tagList.remove(deletedTag.getName());
		
		for(String element: tagList){
			if(element != deletedTag.getName()){
				newName = "@" + element + newName;
			}
		}
		File newFileName = new File(this.name.getParent(),newName);
		renamePhoto(newFileName.getName());
		
	}
	
	/**
	 * To set the name of the Photo back to an old name by letting user type in its previous name from log
	 * 
	 * @param previousName
	 * 					the exact previous name that the user would like the photo
	 * 					to be called
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws ConcurrentModificationException
	 */
	 
	public void undoName(String previousName) throws SecurityException, IOException, ConcurrentModificationException{
		for(File name: nameLog){
			if (!name.equals(null)){
				if(name.getName().equals(previousName)){
					System.out.println("Bingo");
					break;
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws SecurityException, IOException {
		
//		File castle111 = new File("c:\\forPhoto.jpg");
//		Tag tag1 = new Tag("castle");
//		Tag tag2 = new Tag("buildings");
//		Tag tag3 = new Tag("sweets");
//		Photo photoA = new Photo(castle111);
//		System.out.println(photoA.name);
//		System.out.println(photoA.getOrigName());
//		photoA.addTag(tag1);
//		photoA.addTag(tag2);
//		photoA.addTag(tag3);
//		photoA.addTag(tag1);
//		System.out.println(photoA.name);
//		photoA.deleteTag(tag3);
//		System.out.println(photoA.name);
//		
//		for (Tag eachTag : photoA.tagList){
//			System.out.println(eachTag.getName());
//		}
//		photoA.showAllNames();
//		System.out.print("-----------------------------------\n");
//		for(File name: photoA.nameLog ){
//			System.out.print(name.getName());
//			
//		}
//		System.out.print("\n-----------------------------------\n");
//		System.out.println(photoA.name);
//		photoA.undoName("@castleforPhoto.jpg");
//		System.out.println(photoA.name);
//		System.out.print("-----------------------------------\n");
//		System.out.println(photoA.name);
//		if(!photoA.name.getName().equals("@buildings@castleforPhoto.jpg")){
//			System.out.println(photoA.name.getName() + " and " + "@buildings@castleforPhoto.jpg" + " are not the same");
//		}
//		
		
		
		
	}
	
}
