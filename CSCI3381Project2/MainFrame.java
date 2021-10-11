package CSCI3381Project2;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import CSCI3381Project1.*;

public class MainFrame {
	
	public static void main(String[] args) {
		
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
					panel.writeOut();
				}
				
			});
		}

	}

}
