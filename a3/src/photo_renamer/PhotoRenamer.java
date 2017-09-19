package photo_renamer;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PhotoRenamer implements ActionListener {

	public static String yourPath = "C:/Users/July Ju/Desktop/uoftyear2/csc207/group_0633/a3";
	String photoPath = new String();
	File newFile;
	Photo newPhoto;
	// create the frame that contain all Panels and Buttons
	JFrame jf = new JFrame("Photo Editor");
	JPanel buttonPanel = new JPanel();
	JPanel imagePanel = new JPanel();
	BufferedImage img = null;
	JFrame frame = new JFrame("PhotoRenamer");
	JTabbedPane tabPane = new JTabbedPane();
	Container con = new Container();
	Container con2 = new Container();
	JLabel label1 = new JLabel("Choose your photo");
	JLabel label2 = new JLabel("Open this photo!");
	// create text field that shows contents
	JTextField text1 = new JTextField();
	JTextField text2 = new JTextField();
	// create buttons
	JButton button1 = new JButton("browse");
	JButton button2 = new JButton("open");
	JButton button3 = new JButton("Add Tag To This Photo");
	JButton button4 = new JButton("Delete Tag From This Photo");
	JButton button5 = new JButton("Show All Tags of This Photo");
	JButton button6 = new JButton("View/Revert Names of This Photo");
	JButton button7 = new JButton("Manage Current Existing Tags");
	// to browse and select image files from the PC
	JFileChooser jfc = new JFileChooser();

	PhotoRenamer() {
		jfc.setCurrentDirectory(new File("C:\\"));
		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// frame
																					// location
		frame.setSize(700, 220);// frame size
		frame.setContentPane(tabPane);
		label1.setBounds(10, 20, 150, 30);
		label2.setBounds(10, 60, 350, 30);
		text1.setBounds(200, 20, 300, 30);
		text2.setBounds(20, 20, 500, 500);
		button1.setBounds(500, 20, 100, 30);
		button2.setBounds(500, 60, 100, 30);
		// add ActionListener to button
		// Using design pattern Observer to let buttons know what to do once
		// being clicked
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		con2.add(text2);
		con.add(label1);
		con.add(label2);
		con.add(text1);
		con.add(button1);
		con.add(button2);
		con.add(jfc);
		tabPane.add("first step: get path", con);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(2000, 1000);
	}

	public void actionPerformed(ActionEvent e) {

		// Add actionPerformed to tell the program what to do
		try {
			MySystem.importTagFile();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		MySystem.clearTagFile();
		// if the button1 is clicked, then show the browser and let the
		// user select image files
		if (e.getSource().equals(button1)) {
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int state = jfc.showOpenDialog(null);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					// System.out.println("closed");

					MySystem.clearTagFile();
					for (Tag oneTag : MySystem.getExistingTags()) {
						try {
							MySystem.writeToTagFile(oneTag.getName());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			});

			// return the file that the user chose, and record this path
			if (state != JFileChooser.APPROVE_OPTION) {
				return;
			} else {
				File f = jfc.getSelectedFile();
				try {
					text1.setText(f.getCanonicalPath());
					photoPath = f.getCanonicalPath().replace('\\', '/'); // this
																			// is
																			// for
																			// changing
																			// the
																			// java-style
																			// path
																			// to
																			// Windows-style
																			// path
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}

		// if button2 was clicked, open this image( make it visible) and pop up
		// another frame "
		// Photo Editor" that can modify the Tag and name of this Photo
		if (e.getSource().equals(button2)) {
			imagePanel.removeAll();
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(photoPath));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			newFile = new File(photoPath);
			newPhoto = new Photo(newFile);
			ImageIcon icon = new ImageIcon(img);
			JLabel imageLabel = new JLabel(null, icon, JLabel.CENTER);
			imagePanel.add(imageLabel);
			jf.add(imagePanel, BorderLayout.CENTER);
			jf.pack();
			jf.setSize(2000, 1000);
			jf.setVisible(true);

			JPanel leftbox = new JPanel();
			leftbox.setLayout(new BoxLayout(leftbox, BoxLayout.Y_AXIS));
			leftbox.add(button3);
			leftbox.add(button4);
			leftbox.add(button5);
			leftbox.add(button6);
			leftbox.add(button7);
			jf.add(leftbox, BorderLayout.WEST);
		}

		// if Button3 is clicked, the "Add New Tag" frame shows so that user can
		// do actions
		// on add Tag to the Photo, as well as selecting Tags from the previous
		// Tag (allexistingTag)
		if (e.getSource().equals(button3)) {

			JFrame AddTagFrame = new JFrame("Add New Tag");
			AddTagFrame.setSize(800, 250);
			AddTagFrame.setLayout(new GridLayout(3, 1));
			AddTagFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					// System.exit(0);
				}
			});
			JLabel headerLabel = new JLabel("", JLabel.CENTER);
			JLabel statusLabel = new JLabel("", JLabel.CENTER);

			statusLabel.setSize(200, 500);

			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new FlowLayout());

			AddTagFrame.add(headerLabel);
			AddTagFrame.add(controlPanel);
			AddTagFrame.add(statusLabel);
			AddTagFrame.setVisible(true);
			headerLabel.setText("Type in your new tag please");

			JLabel chooseTag = new JLabel("Add Tag from exisitng ones:", JLabel.CENTER);
			JLabel newTag = new JLabel("	OR: Create a new Tag: ", JLabel.CENTER);

			final JTextField NewTagText = new JTextField(6);

			Choice TagChooser2 = new Choice();
			for (Tag allExistingTag : MySystem.getExistingTags()) {
				TagChooser2.add(allExistingTag.getName());
			}

			// User can add Tag to this Photo by either choosing from an
			// existing Tags
			// from the System, clicking confirm, or by creating a new Tag by
			// typing its
			// name, and add this Tag automatically to the System Tag list
			JButton confButton = new JButton("confirm");
			confButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newAddTagName = TagChooser2.getSelectedItem();
					Tag newAddTag = new Tag(newAddTagName);
					try {
						newPhoto.addTag(newAddTag);
						System.out.println(MySystem.getExistingTags());
					} catch (SecurityException | IOException e1) {
						e1.printStackTrace();
					}
					AddTagFrame.dispose();

				}

			});

			JButton addButton = new JButton("Add");
			addButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Tag newTag = new Tag(NewTagText.getText());
					try {
						newPhoto.addTag(newTag);
						// System.out.println(MySystem.getExistingTags());
					} catch (SecurityException | IOException e1) {
						e1.printStackTrace();
					}
					AddTagFrame.dispose();
				}

			});
			controlPanel.add(chooseTag);
			controlPanel.add(TagChooser2);
			controlPanel.add(confButton);
			controlPanel.add(newTag);
			controlPanel.add(NewTagText);
			controlPanel.add(addButton);
			System.out.println(NewTagText.getText());
		}

		// if Button4 is clicked, the "Delete Tag" frame shows up and user can
		// delete Tag on it
		if (e.getSource().equals(button4)) {

			JFrame DeleteTagFrame = new JFrame("Delete Tag");
			DeleteTagFrame.setSize(500, 150);
			DeleteTagFrame.setLayout(new GridLayout(3, 1));
			DeleteTagFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					// System.exit(0);
				}
			});
			JLabel headerLabel = new JLabel("", JLabel.CENTER);
			JLabel statusLabel = new JLabel("", JLabel.CENTER);

			statusLabel.setSize(200, 500);

			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new FlowLayout());

			DeleteTagFrame.add(headerLabel);
			DeleteTagFrame.add(controlPanel);
			DeleteTagFrame.add(statusLabel);
			DeleteTagFrame.setVisible(true);
			headerLabel.setText("Select the tag you want to delete please");

			JLabel removeTag = new JLabel("Delete Tag: ", JLabel.CENTER);
			Choice TagChooser = new Choice();
			for (String previousTagName : newPhoto.getTagList()) {
				TagChooser.add(previousTagName);
			}
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String deleteTagName = TagChooser.getSelectedItem();
					Tag deletedTag = new Tag(deleteTagName);
					try {
						newPhoto.deleteTag(deletedTag);
					} catch (SecurityException | IOException e1) {
						e1.printStackTrace();
					}
					DeleteTagFrame.dispose();

				}

			});

			controlPanel.add(removeTag);
			controlPanel.add(TagChooser);
			controlPanel.add(deleteButton);
		}

		// if Button5 is clicked, the new Frame "Show All Tags" shows up and
		// user
		// can see what Tags this Photo has
		if (e.getSource().equals(button5)) {

			JFrame ShowTagFrame = new JFrame("Show All Tags");
			ShowTagFrame.setSize(800, 800);
			ShowTagFrame.setLayout(new GridLayout(3, 1));
			ShowTagFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					// System.exit(0);
				}
			});
			JLabel headerLabel = new JLabel("", JLabel.CENTER);
			JLabel statusLabel = new JLabel("", JLabel.CENTER);

			statusLabel.setSize(200, 500);

			JPanel controlPanel = new JPanel();

			controlPanel.setLayout(new FlowLayout());

			ShowTagFrame.add(headerLabel);
			ShowTagFrame.add(controlPanel);
			ShowTagFrame.add(statusLabel);
			ShowTagFrame.setVisible(true);
			headerLabel.setText("All the tags attached to this photo: ");

			String[] tagnames = newPhoto.getTagList().toArray(new String[newPhoto.getTagList().size()]);

			JList tagJlist = new JList(tagnames);
			tagJlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			tagJlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			tagJlist.setVisibleRowCount(-1);
			JButton exit = new JButton("OK");
			exit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					ShowTagFrame.dispose();

				}

			});
			controlPanel.add(tagJlist);
			controlPanel.add(exit);

		}

		// if Button6 is clicked, the "View All Names" frame shows up so that
		// user
		// can see what previous names this Photo has had, and revert to any of
		// these names
		// by using the chooser and selecting one of them
		if (e.getSource().equals(button6)) {

			JFrame ViewNamesFrame = new JFrame("View All Names");
			ViewNamesFrame.setSize(500, 150);
			ViewNamesFrame.setLayout(new GridLayout(3, 1));
			ViewNamesFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					// System.exit(0);
				}
			});
			JLabel headerLabel = new JLabel("", JLabel.CENTER);
			JLabel statusLabel = new JLabel("", JLabel.CENTER);

			statusLabel.setSize(200, 500);

			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new FlowLayout());

			ViewNamesFrame.add(headerLabel);
			ViewNamesFrame.add(controlPanel);
			ViewNamesFrame.add(statusLabel);
			ViewNamesFrame.setVisible(true);
			headerLabel.setText("Scroll to see all the names this photo has had");
			Choice NameChooser = new Choice();
			for (File previousTagName : newPhoto.getNameLog()) {
				NameChooser.add(previousTagName.getName());
			}
			JButton revertButton = new JButton("Revert");
			revertButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String selectedName = NameChooser.getSelectedItem();

					try {
						newPhoto.renamePhoto(selectedName);
					} catch (SecurityException | IOException e1) {
						e1.printStackTrace();
					}
					ViewNamesFrame.dispose();

				}

			});
			controlPanel.add(NameChooser);
			controlPanel.add(revertButton);
		}

		// if Button7 is clicked, the new frame "Manage Current Existing Tags"
		// shows up
		// so that user can delete one existing Tag directly from the system,
		// and remove
		// this Tag from all Photos that has this Tag automatically.
		// Note that if the user want to create one Tag, they can create it by
		// clicking
		// "Add new Tag to this Photo" above

		if (e.getSource().equals(button7)) {

			JFrame AddTagFrame = new JFrame("Manage Current Existing Tags");
			AddTagFrame.setSize(800, 250);
			AddTagFrame.setLayout(new GridLayout(3, 1));
			AddTagFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent) {
					// System.exit(0);
				}
			});
			JLabel headerLabel = new JLabel("", JLabel.CENTER);
			JLabel statusLabel = new JLabel("", JLabel.CENTER);

			statusLabel.setSize(200, 500);

			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new FlowLayout());

			AddTagFrame.add(headerLabel);
			AddTagFrame.add(controlPanel);
			AddTagFrame.add(statusLabel);
			AddTagFrame.setVisible(true);
			headerLabel.setText("Scroll to see current existing tags");

			JLabel chooseTag = new JLabel("Current existing tags:", JLabel.CENTER);

			Choice TagChooser2 = new Choice();
			for (Tag allExistingTag : MySystem.getExistingTags()) {
				TagChooser2.add(allExistingTag.getName());
			}

			JButton deleteButton = new JButton("delete");
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String toDeleteTagName = TagChooser2.getSelectedItem();
					try {
						MySystem.removeTag(toDeleteTagName);
						Tag tempTag = new Tag(toDeleteTagName);
						MySystem.removeTagFromAll(tempTag);
					} catch (SecurityException | IOException e1) {
						e1.printStackTrace();
					}
					AddTagFrame.dispose();

				}

			});

			controlPanel.add(chooseTag);
			controlPanel.add(TagChooser2);
			controlPanel.add(deleteButton);

		}

	}

	public static void main(String[] args) {
		new PhotoRenamer();
	}
}