//Holden Davis
//CSCI 3381 - CRN 18741
//Project 2 - Dr. Doderer - Fall 2021

package CSCI3381Project2;

import CSCI3381Project1.*;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame {
	
	public static void main(String[] args) {
		File f = new File(MainFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		//Test for the existence of the input file, as there's no point in even launching the program if it doesn't exist
		Tester tester = new Tester();
		if (tester.testInFile()){
			JFrame frame = new JFrame("JTwitter");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MainPanel panel = new MainPanel();
			frame.getContentPane().add(panel);		
			frame.pack();
			frame.setMinimumSize(new Dimension(720,480));
			frame.setResizable(false);
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					panel.doClose(f.toString());
				}
				
			});
		} else JOptionPane.showMessageDialog(null, "Source file does not exist!");

	}

}
