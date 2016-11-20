package photo_renamer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PhotoRenamer implements WindowListener {
	
	private JTextField txtPath;
	private JFrame frame = new JFrame("Renamer");

	public static void main(String[] args) {
		JFrame jf = new JFrame("PhotoRenamer");
		JButton exitButton = new ExitButton("exit");
		JButton saveButton = new JButton("save");
		JButton moogahButton = new JButton("addTag");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(exitButton);
		buttonPanel.add(moogahButton);
		jf.add(buttonPanel, BorderLayout.WEST);

		JPanel imagePanel = new JPanel();

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:\\207a2images\\forest\\forest1.jpg"));
		} catch (IOException e) {
			
		}

		ImageIcon icon = new ImageIcon(img);
		JLabel imageLabel = new JLabel(null, icon, JLabel.CENTER);
		imagePanel.add(imageLabel);
		jf.add(imagePanel, BorderLayout.CENTER);

		CheckBoxPanel checkboxPanel = new CheckBoxPanel();
		JCheckBox chkApple = new JCheckBox("Tag1");
		JCheckBox chkMango = new JCheckBox("Tag2");
		JCheckBox chkPeer = new JCheckBox("Tag3");
		checkboxPanel.add(chkApple);
		checkboxPanel.add(chkMango);
		checkboxPanel.add(chkPeer);

		jf.add(checkboxPanel, BorderLayout.SOUTH);

		JTextField textField = new JTextField();
		jf.add(textField, BorderLayout.NORTH);

		String[] data = { "one", "two", "three", "four" };
		JList<String> myList = new JList<String>(data);
		jf.add(myList, BorderLayout.EAST);
		jf.pack();
		jf.setVisible(true);
		
		//Ask the window to listen to its own events
		PhotoRenamer s = new PhotoRenamer();
		s.initialize();
        s.frame.setVisible(true);
		jf.addWindowListener((WindowListener)s);

	}

	  private void initialize() {	         
		    frame = new JFrame();
		    frame.setBounds(100, 100, 450, 300);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.getContentPane().setLayout(null);
		    
		  txtPath = new JTextField();
		    txtPath.setBounds(10, 10, 414, 21);
		    frame.getContentPane().add(txtPath);
		    txtPath.setColumns(10);
		         
		    JButton btnBrowse = new JButton("Browse");
		    btnBrowse.setBounds(10, 41, 87, 23);
		    frame.getContentPane().add(btnBrowse);
		         
		    btnBrowse.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		 
		        // For Directory
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 
		        // For File
		        //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 
		        fileChooser.setAcceptAllFileFilterUsed(false);
		 
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		          txtPath.setText(fileChooser.getSelectedFile().toString());
		          getDirectoryPath(fileChooser.getSelectedFile().toString());
		        }
		      }
		    });
	  }
	  
	public String getDirectoryPath(String txtPath){

		//		NOTICE!!!!!!!!!!!
//		using this following line to show that we can get the directory path from this method
//		One can ignore this line System.out.println() when doing other actions
		System.out.println(txtPath);
		return txtPath;
	}
	
	// Below window events are implemented, so the window can respond to actions
    public void windowClosing(WindowEvent e) {
            System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}