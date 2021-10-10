package CSCI3381Project2;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import CSCI3381Project1.*;

public class MainPanel extends JPanel{
	
	//TweetCollection
	private TweetCollection tweets;
	
	//Login elements
	private JTextField loginunametf;
	private JTextField logintitlebanner;
	private JButton loginanonbutton;
	private JButton loginuserbutton;
	
	//Core elements
	private JButton corebackbutton;
	private JButton coresearchbutton;
	private JButton corepostbutton;
	private JButton corepredictbutton;
	private JButton corerefreshbutton;
	private JTextField coretitlebanner;
	private JScrollPane corescrollpane;
	private DefaultListModel<String> coremodel;
	private JList<String> corelist;

	//Search elements

	//Post elements

	//Predict elements

	//Initialize ALL elements in constructor, but only set login elements to be visible by calling toLogin() at the end
	public MainPanel() {
		tweets = new TweetCollection("./CSCI3381Project1/trainingProcessed.txt");
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		//Initialize Login Elements
		loginanonbutton = new JButton("Anonymous Login");
		loginanonbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		loginanonbutton.setBounds(300, 367, 120, 30);
		loginanonbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCore(loginunametf.getText());
				}
			}
		);
		add(loginanonbutton);
		
		loginunametf = new JTextField();
		loginunametf.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		loginunametf.setBounds(300, 177, 120, 30);
		loginunametf.setHorizontalAlignment(SwingConstants.CENTER);
		loginunametf.setText("Username");
		loginunametf.setToolTipText("Enter Username");
		loginunametf.setColumns(10);
		add(loginunametf);
		
		loginuserbutton = new JButton("Login as User");
		loginuserbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		loginuserbutton.setBounds(300, 208, 120, 30);
		loginuserbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCore(loginunametf.getText());
				}
			}
		);
		add(loginuserbutton);
		
		logintitlebanner = new JTextField();
		logintitlebanner.setEditable(false);
		logintitlebanner.setForeground(Color.WHITE);
		logintitlebanner.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20));
		logintitlebanner.setBackground(Color.DARK_GRAY);
		logintitlebanner.setHorizontalAlignment(SwingConstants.CENTER);
		logintitlebanner.setText("JTwitter");
		logintitlebanner.setBounds(0, 30, 720, 40);
		logintitlebanner.setColumns(40);
		add(logintitlebanner);
		
		//Intialize core elements
		corebackbutton = new JButton("Back");
		corebackbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		corebackbutton.setBounds(20, 400, 60, 30);
		corebackbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toLogin();
				}
			}
		);
		add(corebackbutton);
		coresearchbutton = new JButton("Search");
		coresearchbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 15));
		coresearchbutton.setBounds(20, 100, 120, 60);
		coresearchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toSearch(loginunametf.getText());
				}
			}
		);
		add(coresearchbutton);
		corepostbutton = new JButton("Post");
		corepostbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 15));
		corepostbutton.setBounds(20, 193, 120, 60);
		corepostbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toPost(loginunametf.getText());
				}
			}
		);
		add(corepostbutton);
		corepredictbutton = new JButton("Predict");
		corepredictbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 15));
		corepredictbutton.setBounds(20, 290, 120, 60);
		corepredictbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toPredict(loginunametf.getText());
				}
			}
		);
		add(corepredictbutton);
		corerefreshbutton = new JButton("Refresh");
		corerefreshbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		corerefreshbutton.setBounds(630, 400, 70, 30);
		corerefreshbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coreTweets(loginunametf.getText());
				}
			}
		);
		add(corerefreshbutton);
		
		coretitlebanner = new JTextField();
		coretitlebanner.setEditable(false);
		coretitlebanner.setForeground(Color.WHITE);
		coretitlebanner.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20));
		coretitlebanner.setBackground(Color.DARK_GRAY);
		coretitlebanner.setHorizontalAlignment(SwingConstants.CENTER);
		coretitlebanner.setBounds(0, 30, 720, 40);
		coretitlebanner.setColumns(40);
		add(coretitlebanner);
		
		coremodel = new DefaultListModel<String>();
		corelist = new JList<String>(coremodel);
		corescrollpane = new JScrollPane(corelist);
		corescrollpane.setBounds(200, 100, 500, 250);
		coreTweets(loginunametf.getText());
		add(corescrollpane);

		//Initialize search elements
		//Initialized post elements
		//Initialize predict elements
		
		//Always start at login screen
		toLogin();
		
	}

	public void toLogin() {
		loginunametf.setText("Username");
		loginunametf.setVisible(true);
		logintitlebanner.setVisible(true);
		loginanonbutton.setVisible(true);
		loginuserbutton.setVisible(true);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		corepredictbutton.setVisible(false);
		corerefreshbutton.setVisible(false);
		coretitlebanner.setVisible(false);
		corelist.setVisible(false);
		corescrollpane.setVisible(false);
	}
	public void toCore(String username) {
		coreTweets(loginunametf.getText());
		loginunametf.setVisible(false);
		logintitlebanner.setVisible(false);
		loginanonbutton.setVisible(false);
		loginuserbutton.setVisible(false);
		
		corebackbutton.setVisible(true);
		coresearchbutton.setVisible(true);
		corepostbutton.setVisible(true);
		corepredictbutton.setVisible(true);
		coretitlebanner.setVisible(true);
		corelist.setVisible(true);
		corescrollpane.setVisible(true);
	}
	public void coreTweets(String user) {
		if (user.equals("Username")){
			coremodel.removeAllElements();
			coretitlebanner.setText("Random Tweets");
			for (int i = 0; i < 30; i++) {
				Tweet randtweet = tweets.randomTweet();
				coremodel.addElement(randtweet.toString());
			}
			corerefreshbutton.setVisible(true);

		}
		else {
			coremodel.removeAllElements();
			coretitlebanner.setText("My Tweets");
			ArrayList<Tweet> utweets = tweets.searchByUser(user);
			for (Tweet utweet : utweets) {
				coremodel.addElement(utweet.toString());
			}
			corerefreshbutton.setVisible(false);

		}
		
	}
	
	public void toSearch(String username) {
		
	}
	public void toPost(String username) {

	}
	public void toPredict(String username) {

	}
}
	
	
