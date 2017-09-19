package photo_renamer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * To test if all methods in MySystem class that modify the main list of Tag are
 * correct
 * 
 */

public class MySystemTest {

	public MySystem newSystem;
	public Photo pho1;
	public Photo pho2;

	@Before
	public void setUp() {

		newSystem = new MySystem();
		File castle111 = new File("c:\\forPhoto1.jpg");
		pho1 = new Photo(castle111);
		File forest111 = new File("c:\\forPhoto2.jpg");
		pho2 = new Photo(forest111);
	}

	// Test if MySystem returns an empty string, which means not tag available
	// since have not yet created any new Tag!

	@Test
	public void testEmptyTagCollection() {

		assertEquals("", MySystem.showTagCollection());
		System.out.println("1111111" + MySystem.showTagCollection());
	}

	// Test if MySystem add this new Tag( or Tags) to it's list when the user
	// created and add a new Tag to a Photo.
	// Also test if user add a Tag, which was actually created previously, to
	// one
	// Photo, then the System does not add this duplicated Tag again

	@Test
	public void testAddNewTag() throws SecurityException, IOException {

		Tag tag1 = new Tag("castle");
		pho1.addTag(tag1);
		// Add one new Tag to the System
		assertEquals("castle\n", MySystem.showTagCollection());
		Tag tag2 = new Tag("buildings");
		pho2.addTag(tag1);
		pho1.addTag(tag2);
		// Add more Tags and one duplicate Tag
		assertEquals("castle\nbuildings\n", MySystem.showTagCollection());
		Tag tag3 = new Tag("greens");
		MySystem.createTag(tag3);
		// Add Tag to the System by creating directly in MySystem,
		// rather than create it by adding it to one Photo
		assertEquals("castle\nbuildings\ngreens\n", MySystem.showTagCollection());

	}

	// test if delete one Tag from the System works
	@Test
	public void testDeleteTag() throws SecurityException, IOException {
		MySystem.getSystemPhotoList().add(pho1);
		MySystem.getSystemPhotoList().add(pho2);
		Tag tag1 = new Tag("buildings");
		Tag tag2 = new Tag("greens");
		Tag tag3 = new Tag("sweets");
		pho1.addTag(tag1);
		pho1.addTag(tag2);
		pho2.addTag(tag3);
		pho1.deleteTag(tag1);
		// test if delete one Tag from Photo would not delete it from the System
		assertEquals("buildings\ngreens\nsweets\n", MySystem.showTagCollection());
		MySystem.removeTag(tag2.getName());
		MySystem.removeTag(tag3.getName());
		// test if RemoveTag delete one Tag from the System, or from the System
		// list
		assertEquals("buildings\n", MySystem.showTagCollection());
		MySystem.removeTag(tag1.getName());
		assertEquals("", MySystem.showTagCollection());

	}

	// Test if one Tag is removed from the System, then any Photo that contains
	// this Tag
	// should be checked, and this Tag should be removed from them and Photos
	// are renamed

	@Test
	public void testRemoveFromAllTags() throws SecurityException, IOException {

		Tag tag1 = new Tag("castle");
		Tag tag2 = new Tag("buildings");
		Tag tag3 = new Tag("sweets");
		pho1.addTag(tag1);
		pho1.addTag(tag2);
		pho2.addTag(tag1);
		pho2.addTag(tag3);
		MySystem.removeTagFromAll(tag1);
		// remove one Tag from the System will permanently delete this Tag
		assertEquals("buildings\nsweets\n", MySystem.showTagCollection());
		pho2.deleteTag(tag3);
		pho1.deleteTag(tag2);
		// delete one Tag from one Photo would not delete this Tag from System
		assertEquals("buildings\nsweets\n", MySystem.showTagCollection());
		MySystem.removeTagFromAll(tag2);
		MySystem.removeTagFromAll(tag3);
		assertEquals("", MySystem.showTagCollection());
	}

}
