package photo_renamer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * To create an Exit Button that can close the System
 *
 */
public class ExitButton extends JButton implements ActionListener {

	private ExitButton() {
	};

	// The constructor we want to use
	ExitButton(String label) {
		super(label); // Class: What will leaving this out do?
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Exiting using button!");
		System.exit(0);
	}

}