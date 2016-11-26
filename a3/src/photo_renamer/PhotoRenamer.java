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

	String photoPath = new String();
	File newFile;
	Photo newPhoto;
	JFrame jf = new JFrame("My Window");
	JButton exitButton = new ExitButton("exit");
	JButton saveButton = new JButton("save");
	JButton moogahButton = new JButton("moogah");
	JPanel buttonPanel = new JPanel();
	JPanel imagePanel = new JPanel();
	BufferedImage img = null;
	JFrame frame = new JFrame("PhotoRenamer");
	JTabbedPane tabPane = new JTabbedPane();
	Container con = new Container();
	Container con2 = new Container();
	JLabel label1 = new JLabel("Choose your photo");
	JLabel label2 = new JLabel("Open this photo!");
	JTextField text1 = new JTextField();
	JTextField text2 = new JTextField();
	JButton button1 = new JButton("browse");
	JButton button2 = new JButton("open");
	JButton button3 = new JButton("Add Tag");
	JButton button4 = new JButton("Delete Tag");
	JButton buttonAddTag = new JButton("Add Tag");
	JButton buttonDelTag = new JButton("Delete Tag");
	JButton buttonShowTag = new JButton("Show Available Tags");
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
		buttonAddTag.setBounds(20, 600, 100, 40);
		buttonDelTag.setBounds(150, 600, 100, 40);
		buttonShowTag.setBounds(280, 600, 200, 40);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		con2.add(text2);
		con2.add(buttonAddTag);
		con2.add(buttonDelTag);
		con2.add(buttonShowTag);
		con.add(label1);
		con.add(label2);
		con.add(text1);
		con.add(button1);
		con.add(button2);
		con.add(jfc);
		tabPane.add("first step: get path", con);
		tabPane.add("second step: modify name", con2);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(button1)) {
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int state = jfc.showOpenDialog(null);
			if (state != JFileChooser.APPROVE_OPTION) {
				return;
			} else {
				File f = jfc.getSelectedFile();
				try {
					text1.setText(f.getCanonicalPath());
					photoPath = f.getCanonicalPath().replace('\\', '/');
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (e.getSource().equals(button2)) {
			imagePanel.removeAll();
			// jf.revalidate();
			// jf.repaint();
			// SwingUtilities.updateComponentTreeUI(jf);
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(photoPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			newFile = new File(photoPath);
			newPhoto = new Photo(newFile);
//			MySystem.systemPhotoList.add(newPhoto);
			ImageIcon icon = new ImageIcon(img);
			JLabel imageLabel = new JLabel(null, icon, JLabel.CENTER);
			imagePanel.add(imageLabel);
			jf.add(imagePanel, BorderLayout.CENTER);
			jf.setSize(1000, 1000);
			jf.pack();
			jf.setVisible(true);

			JPanel leftbox = new JPanel();
			leftbox.setLayout(new BoxLayout(leftbox, BoxLayout.Y_AXIS));
			leftbox.add(button3);
			leftbox.add(button4);
			jf.add(leftbox, BorderLayout.WEST);
		}

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
						System.out.println(MySystem.getExistingTags());
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
			// mainFrame.setVisible(true);
			System.out.println(NewTagText.getText());
		}

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
			for (String previousTagName : newPhoto.tagList) {
				TagChooser.add(previousTagName);
			}
			// String deleteTagName = TagChooser.getSelectedItem();
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String deleteTagName = TagChooser.getSelectedItem();
					Tag deletedTag = new Tag(deleteTagName);
					try {
						newPhoto.deleteTag(deletedTag);
						System.out.println(MySystem.getExistingTags());
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

	}

	public static void main(String[] args) {
		new PhotoRenamer();
	}
}