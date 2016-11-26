package photo_renamer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MySystemTest {

	public MySystem newSystem1;
	public MySystem newSystem2;
	public Photo pho1;
	public Photo pho2;

	@Before
	public void setUp() {

		newSystem1 = new MySystem();
		newSystem2 = new MySystem();
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
	// created and add a new Tag to a Photo

	@Test
	public void testAddNewTag() throws SecurityException, IOException {

		Tag tag1 = new Tag("castle");
		pho1.addTag(tag1);
		assertEquals("castle\n", MySystem.showTagCollection());
		Tag tag2 = new Tag("buildings");
		pho2.addTag(tag1);
		pho1.addTag(tag2);
		assertEquals("castle\nbuildings\n", MySystem.showTagCollection());
		Tag tag3 = new Tag("greens");
		MySystem.createTag(tag3);
		assertEquals("castle\nbuildings\ngreens\n", MySystem.showTagCollection());

	}

	// Test if user add a Tag, which was actually created previously, to one
	// Photo,
	// then the System should not add this Tag again to its list

	// @Test
	// public void testAddExistingTag() throws SecurityException, IOException {
	//
	// Tag tag1 = new Tag("castle");
	// Tag tag2 = new Tag("buildings");
	// pho1.addTag(tag1);
	// pho1.addTag(tag2);
	// assertEquals("castle\nbuildings\n", MySystem.showTagCollection());
	// }

	// Test if user delete one Tag from one Photo
	// then the System should not remove this Tag from its list

	// @Test
	// public void testDeleteOneTag() throws SecurityException, IOException {
	//
	// Tag tag1 = new Tag("castle");
	// Tag tag2 = new Tag("buildings");
	// pho1.addTag(tag1);
	// pho1.addTag(tag2);
	// pho1.deleteTag(tag1);
	// assertEquals("castle\nbuildings\n", MySystem.showTagCollection());
	// }

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
		assertEquals("buildings\nsweets\n", MySystem.showTagCollection());
		pho2.deleteTag(tag3);
		pho1.deleteTag(tag2);
		assertEquals("buildings\nsweets\n", MySystem.showTagCollection());
		MySystem.removeTagFromAll(tag2);
		MySystem.removeTagFromAll(tag3);
		assertEquals("", MySystem.showTagCollection());
	}

	// @Test
	// public void testAddTagInSystem() throws SecurityException, IOException{
	// Tag tag4 = new Tag("greens");
	// MySystem.createTag(tag4);
	// assertEquals("castle\nbuildings\ngreens\n",
	// MySystem.showTagCollection());
	// }

	@Test
	public void testDeleteTag() throws SecurityException, IOException {
		System.out.println("ppppppppp" + MySystem.systemPhotoList);
		MySystem.systemPhotoList.add(pho1);
		MySystem.systemPhotoList.add(pho2);
		// System.out.println("ppppppppp" + MySystem.systemPhotoList);
		Tag tag1 = new Tag("buildings");
		Tag tag2 = new Tag("greens");
		Tag tag3 = new Tag("sweets");
		pho1.addTag(tag1);
		pho1.addTag(tag2);
		pho2.addTag(tag3);
		pho1.deleteTag(tag1);
		assertEquals("buildings\ngreens\nsweets\n", MySystem.showTagCollection());
		MySystem.removeTag(tag2);
		MySystem.removeTag(tag3);
		assertEquals("buildings\n", MySystem.showTagCollection());
		MySystem.removeTag(tag1);
		assertEquals("", MySystem.showTagCollection());

	}

}
