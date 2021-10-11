//Holden Davis
//CSCI 3381 - CRN 18741
//Project 2 - Dr. Doderer - Fall 2021

package CSCI3381Project2;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import CSCI3381Project1.*;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class MainPanel extends JPanel{
	
	private String datainFile;
	private String testinFile;
	private String dataOutFile;
	
	//Global/shared elements
	private TweetCollection tweets;
	private TweetCollection testing;
	private JTextField titlebanner;
	private JButton backbutton;
	private JScrollPane tweetscrollpane;
	private DefaultListModel<String> tweetmodel;
	private JList<String> tweetlist;
	private HashMap<String, ArrayList<Integer>> testData;
	
	//Login elements
	private JTextField loginunametf;
	private JButton loginanonbutton;
	private JButton loginuserbutton;
	
	//Core elements
	private JButton corebackbutton;
	private JButton coresearchbutton;
	private JButton corepostbutton;
	private JButton corerefreshbutton;
	private JLabel coreloginnotif;
	private JLabel coreloginval;

	//Search elements
	private JTextField searchfield;
	private JButton deletetweetbutton;
	private JButton searcherbutton;
	private JComboBox<String> searchoptioncombobox;
	private JButton predictionbutton;
	private JToggleButton predicttypebutton;

	//Post elements
	private JButton postposterbutton;
	private JCheckBox genIDcheckbox;
	private JRadioButton pol2radio;
	private JRadioButton pol4radio;
	private JRadioButton pol0radio;
	private ButtonGroup polarities;
	private JTextField postIDfield;
	private JTextField postuserfield;
	private JTextField postcontentfield;
	private JLabel pollabel;
	private JLabel idlabel;
	private JLabel userlabel;
	private JLabel contentlabel;

	//Initialize ALL elements in constructor, but only set login elements to be visible by calling toLogin() at the end
	public MainPanel() {
		//Testing Configuration
		datainFile = "./CSCI3381Project1/trainingProcessed.txt";
		testinFile = "./CSCI3381Project1/testProcessed.txt";
		dataOutFile = "./CSCI3381Project1/out.txt";
		//Production Configuration
//		datainFile = "./CSCI3381Project1/trainingProcessed.txt";
//		testinFile = "./CSCI3381Project1/testProcessed.txt";
//		dataOutFile = "./CSCI3381Project1/trainingProcessed.txt";
		tweets = new TweetCollection(datainFile);
		testData = tweets.createPredictionData();
		testing = new TweetCollection(testinFile);
		setBackground(new Color(29, 161, 242));
		setLayout(null);
		
		//Initialize Login Elements
		loginanonbutton = new JButton("Anonymous Login");
		loginanonbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		loginanonbutton.setBounds(300, 370, 120, 30);
		loginanonbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCore("Username");
				}
			}
		);
		add(loginanonbutton);
		
		loginunametf = new JTextField();
		loginunametf.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		loginunametf.setBounds(300, 180, 120, 30);
		loginunametf.setHorizontalAlignment(SwingConstants.CENTER);
		loginunametf.setText("Username");
		loginunametf.setToolTipText("Enter Username");
		loginunametf.setColumns(10);
		add(loginunametf);
		
		loginuserbutton = new JButton("Login as User");
		loginuserbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		loginuserbutton.setBounds(300, 210, 120, 30);
		loginuserbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tweets.searchByUser(loginunametf.getText()).size() == 0) {
					JOptionPane.showMessageDialog(getParent(), "User does not exist!");
				}
				else toCore(loginunametf.getText());
				}
			}
		);
		add(loginuserbutton);
		
		titlebanner = new JTextField();
		titlebanner.setEditable(false);
		titlebanner.setForeground(Color.WHITE);
		titlebanner.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20));
		titlebanner.setBackground(new Color(20,23,26));
		titlebanner.setHorizontalAlignment(SwingConstants.CENTER);
		titlebanner.setText("JTwitter");
		titlebanner.setBounds(0, 30, 720, 40);
		titlebanner.setColumns(40);
		titlebanner.setVisible(true);
		add(titlebanner);
		
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
		corepostbutton.setBounds(20, 190, 120, 60);
		corepostbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toPost(loginunametf.getText());
				}
			}
		);
		add(corepostbutton);
		corerefreshbutton = new JButton("Refresh");
		corerefreshbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		corerefreshbutton.setBounds(630, 400, 70, 30);
		corerefreshbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coreTweets("Username");
				}
			}
		);
		add(corerefreshbutton);
		
		coreloginnotif = new JLabel("Logged In As:");
		coreloginnotif.setHorizontalAlignment(SwingConstants.CENTER);
		coreloginnotif.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		coreloginnotif.setBounds(20, 300, 120, 20);
		add(coreloginnotif);
		
		coreloginval = new JLabel("Anonymous");
		coreloginval.setHorizontalAlignment(SwingConstants.CENTER);
		coreloginval.setFont(new Font("Source Serif Pro", Font.PLAIN, 12));
		coreloginval.setBounds(20, 320, 120, 20);
		add(coreloginval);
		
		tweetmodel = new DefaultListModel<String>();
		tweetlist = new JList<String>(tweetmodel);
		tweetscrollpane = new JScrollPane(tweetlist);
		tweetscrollpane.setBounds(200, 100, 500, 250);
		coreTweets(loginunametf.getText());
		add(tweetscrollpane);

		//Initialize search elements
		backbutton = new JButton("Back");
		backbutton.setFont(new Font("Source Serif Pro", Font.PLAIN, 10));
		backbutton.setBounds(20, 400, 60, 30);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCore(loginunametf.getText());
				}
			}
		);
		add(backbutton);
		
		searchfield = new JTextField();
		searchfield.setHorizontalAlignment(SwingConstants.CENTER);
		searchfield.setBounds(20, 190, 120, 60);
		add(searchfield);
		searchfield.setColumns(10);
		
		searcherbutton = new JButton("Search");
		searcherbutton.setBounds(20, 290, 120, 60);
		searcherbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(searchfield.getText());
				}
			}
		);
		add(searcherbutton);
		
		searchoptioncombobox = new JComboBox<String>();
		searchoptioncombobox.setModel(new DefaultComboBoxModel<String>(new String[] {"Search by ID", "Search by User"}));
		searchoptioncombobox.setBounds(20, 100, 120, 60);
		add(searchoptioncombobox);
		
		deletetweetbutton = new JButton("Delete");
		deletetweetbutton.setBounds(200, 400, 100, 30);
		deletetweetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(tweetlist.getSelectedValue());
				}
			}
		);
		add(deletetweetbutton);
		
		predictionbutton = new JButton("Predict");
		predictionbutton.setBounds(600, 400, 100, 30);
		predictionbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				predict();
				}
			}
		);
		add(predictionbutton);
		
		predicttypebutton = new JToggleButton("Chosen Tweet");
		predicttypebutton.setBounds(450, 400, 140, 30);
		predicttypebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (predicttypebutton.isSelected())
				{
					predicttypebutton.setText("All Training Data");
				}
				else predicttypebutton.setText("Chosen Tweet");
			}
		});
		add(predicttypebutton);
		
		//Initialize post elements
		postposterbutton = new JButton("Post");
		postposterbutton.setBounds(340, 400, 100, 30);
		postposterbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				post();
				}
			}
		);
		add(postposterbutton);
		
		genIDcheckbox = new JCheckBox("Auto Generate ID");
		genIDcheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		genIDcheckbox.setBounds(200, 400, 130, 30);
		genIDcheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (genIDcheckbox.isSelected()) {
					postIDfield.setEditable(false);
					postIDfield.setText(tweets.genID().toString());
				}
				else {
					postIDfield.setEditable(true);
					postIDfield.setText("");
				}
				}
			}
		);
		add(genIDcheckbox);
		
		pol2radio = new JRadioButton("2 :|");
		pol2radio.setBounds(8, 225, 72, 24);
		add(pol2radio);
		
		pol4radio = new JRadioButton("4 :)");
		pol4radio.setBounds(8, 197, 72, 24);
		add(pol4radio);
		
		pol0radio = new JRadioButton("0 :(");
		pol0radio.setBounds(8, 253, 72, 24);
		add(pol0radio);
		
		polarities = new ButtonGroup();
		polarities.add(pol2radio);
		polarities.add(pol4radio);
		polarities.add(pol0radio);
		
		postIDfield = new JTextField();
		postIDfield.setHorizontalAlignment(SwingConstants.CENTER);
		postIDfield.setBounds(90, 230, 120, 20);
		add(postIDfield);
		postIDfield.setColumns(10);
		
		postuserfield = new JTextField();
		postuserfield.setHorizontalAlignment(SwingConstants.CENTER);
		postuserfield.setBounds(220, 230, 120, 20);
		add(postuserfield);
		postuserfield.setColumns(10);
		
		postcontentfield = new JTextField();
		postcontentfield.setHorizontalAlignment(SwingConstants.CENTER);
		postcontentfield.setBounds(340, 200, 360, 80);
		add(postcontentfield);
		postcontentfield.setColumns(10);
		
		pollabel = new JLabel("Polarity");
		pollabel.setHorizontalAlignment(SwingConstants.CENTER);
		pollabel.setBounds(20, 170, 60, 20);
		add(pollabel);
		
		idlabel = new JLabel("ID");
		idlabel.setHorizontalAlignment(SwingConstants.CENTER);
		idlabel.setBounds(90, 170, 120, 20);
		add(idlabel);
		
		userlabel = new JLabel("User");
		userlabel.setHorizontalAlignment(SwingConstants.CENTER);
		userlabel.setBounds(220, 170, 120, 20);
		add(userlabel);
		
		contentlabel = new JLabel("Content");
		contentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentlabel.setBounds(340, 170, 360, 20);
		add(contentlabel);
		
		//Always start at login screen
		toLogin();
		
	}
	//Go to Login screen: set all core, search, and post elements invisible, change banner text
	public void toLogin() {
		titlebanner.setText("JTwitter");
		
		loginunametf.setVisible(true);
		loginanonbutton.setVisible(true);
		loginuserbutton.setVisible(true);
		
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		predictionbutton.setVisible(false);
		predicttypebutton.setVisible(false);
		
		backbutton.setVisible(false);
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		coreloginnotif.setVisible(false);
		coreloginval.setVisible(false);
		corerefreshbutton.setVisible(false);

		tweetlist.setVisible(false);
		tweetscrollpane.setVisible(false);
		postposterbutton.setVisible(false);
		genIDcheckbox.setVisible(false);
		pol2radio.setVisible(false);
		pol4radio.setVisible(false);
		pol0radio.setVisible(false);
		postIDfield.setVisible(false);
		postuserfield.setVisible(false);
		postcontentfield.setVisible(false);
		pollabel.setVisible(false);
		idlabel.setVisible(false);
		userlabel.setVisible(false);
		contentlabel.setVisible(false);

	}
	//Go to Core screen: set all login, search, and post elements invisible, change banner text
	//Get tweets + change login notifier based on input from login screen
	public void toCore(String username) {
		if (username.equals("")) {
			coreloginval.setText("Anonymous");
			loginunametf.setText("Username");
		}
		else if (username.equals("Username")) {
			coreloginval.setText("Anonymous");
		}
		else coreloginval.setText(username);
		coreTweets(username);
		
		loginunametf.setVisible(false);
		loginanonbutton.setVisible(false);
		loginuserbutton.setVisible(false);
		
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		predictionbutton.setVisible(false);
		predicttypebutton.setVisible(false);
		
		corebackbutton.setVisible(true);
		coresearchbutton.setVisible(true);
		corepostbutton.setVisible(true);
		coreloginnotif.setVisible(true);
		coreloginval.setVisible(true);
		tweetlist.setVisible(true);
		tweetscrollpane.setVisible(true);
		
		postposterbutton.setVisible(false);
		genIDcheckbox.setVisible(false);
		pol2radio.setVisible(false);
		pol4radio.setVisible(false);
		pol0radio.setVisible(false);
		postIDfield.setVisible(false);
		postuserfield.setVisible(false);
		postcontentfield.setVisible(false);
		pollabel.setVisible(false);
		idlabel.setVisible(false);
		userlabel.setVisible(false);
		contentlabel.setVisible(false);
	}
	//Fill tweet list with tweets: random tweets in anonymous login, or user's tweets if logged in as a user
	//Set refresh button to visible if logged in anonymously, will show new random tweets when pressed
	public void coreTweets(String user) {
		if (user.equals("Username")){
			tweetmodel.removeAllElements();
			titlebanner.setText("Random Tweets");
			for (int i = 0; i < 30; i++) {
				Tweet randtweet = tweets.randomTweet();
				tweetmodel.addElement(randtweet.toString());
			}
			corerefreshbutton.setVisible(true);

		}
		else {
			tweetmodel.removeAllElements();
			titlebanner.setText("My Tweets");
			ArrayList<Tweet> utweets = tweets.searchByUser(user);
			for (Tweet utweet : utweets) {
				tweetmodel.addElement(utweet.toString());
			}
			corerefreshbutton.setVisible(false);

		}
		
	}
	//Go to Search screen: set all core, login, and post elements invisible, change banner text
	public void toSearch(String username) {
		tweetmodel.removeAllElements();
		searchfield.setText(loginunametf.getText());
		searchoptioncombobox.setSelectedIndex(1);
		titlebanner.setText("Search");
		search(username);
		
		backbutton.setVisible(true);		
		searchfield.setVisible(true);
		deletetweetbutton.setVisible(true);
		searcherbutton.setVisible(true);
		searchoptioncombobox.setVisible(true);
		predictionbutton.setVisible(true);
		predicttypebutton.setVisible(true);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		coreloginnotif.setVisible(false);
		coreloginval.setVisible(false);
		corerefreshbutton.setVisible(false);
		tweetlist.setVisible(true);
		tweetscrollpane.setVisible(true);
		
		postposterbutton.setVisible(false);
		genIDcheckbox.setVisible(false);
		pol2radio.setVisible(false);
		pol4radio.setVisible(false);
		pol0radio.setVisible(false);
		postIDfield.setVisible(false);
		postuserfield.setVisible(false);
		postcontentfield.setVisible(false);
		pollabel.setVisible(false);
		idlabel.setVisible(false);
		userlabel.setVisible(false);
		contentlabel.setVisible(false);
	}
	//Search for a tweet given the search type and ID/user
	//Clear tweet list and add results to it
	public void search(String query) {
		tweetmodel.removeAllElements();
		//Searching by ID
		if (searchoptioncombobox.getSelectedIndex() == 0) {
			try {
				int tweetid = Integer.parseInt(query);
				if (tweets.searchByID(tweetid) == null)
				{
					JOptionPane.showMessageDialog(getParent(), "Tweet does not exist!");
					searchfield.setText("");
				}
				else tweetmodel.addElement(tweets.searchByID(tweetid).toString());
			} catch(Exception e) {
				JOptionPane.showMessageDialog(getParent(), "Enter a valid ID!");
				searchfield.setText("");
			}
		}
		//Searching by user
		else {
			if (tweets.searchByUser(query).size() == 0) {
				JOptionPane.showMessageDialog(getParent(), "User does not exist!");
				searchfield.setText("");
			}
			else {
			ArrayList<Tweet> utweets = tweets.searchByUser(query);
			for (Tweet utweet : utweets) {
				tweetmodel.addElement(utweet.toString());
			}
			}
		}
	}
	//Delete a tweet given the input toString representation of a tweet
	//Also update the search results to reflect deletion
	public void delete(String tweetstring) {
		try {
			String[] stuff = tweetstring.split(",");
			tweets.removeTweet(tweets.searchByID(Long.parseLong(stuff[1])));
			search(searchfield.getText());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(getParent(), "Choose a tweet to delete!");
		}
	}
	//If singular tweet prediction (chosen from list), popup dialog box with prediction
	//Otherwise, clear tweet list and add judgeAccuracy result
	public void predict() {
		//Option to test on all training data is chosen
		if (predicttypebutton.isSelected())
		{
			tweetmodel.removeAllElements();
			String[] results = testing.judgeAccuracy(testData);
			for (String result: results) {
				tweetmodel.addElement(result);
			}
		}
		//Option to test on chosen tweet is chosen
		else {
			String tweetstring = tweetlist.getSelectedValue();
			try {
				String[] stuff = tweetstring.split(",");
				Tweet tweet = new Tweet(Integer.parseInt(stuff[0]), Long.parseLong(stuff[1]), stuff[2], stuff[3]);
				int polguess = tweets.predict(tweet, testData);
				JOptionPane.showMessageDialog(getParent(), "Predicted a polarity of " + polguess + " for chosen tweet.");
			} catch(Exception e) {
				JOptionPane.showMessageDialog(getParent(), "Choose a tweet to predict!");
			}
		}
	}
	//Go to Post screen: set all core, search, and login elements invisible, change banner text
	//Set post tweet polarity to 2 (in the middle) by default
	public void toPost(String username) {
		postuserfield.setText(loginunametf.getText());
		pol2radio.setSelected(true);
		titlebanner.setText("Post");
		
		backbutton.setVisible(true);
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		predictionbutton.setVisible(false);
		predicttypebutton.setVisible(false);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		coreloginnotif.setVisible(false);
		coreloginval.setVisible(false);
		corerefreshbutton.setVisible(false);
		tweetlist.setVisible(false);
		tweetscrollpane.setVisible(false);
		
		postposterbutton.setVisible(true);
		genIDcheckbox.setVisible(true);
		pol2radio.setVisible(true);
		pol4radio.setVisible(true);
		pol0radio.setVisible(true);
		postIDfield.setVisible(true);
		postuserfield.setVisible(true);
		postcontentfield.setVisible(true);
		pollabel.setVisible(true);
		idlabel.setVisible(true);
		userlabel.setVisible(true);
		contentlabel.setVisible(true);

	}
	//Add tweet from given contents, and clear all fields once added
	public void post() {
		try {
			int polarity;
			if (polarities.getSelection() == pol2radio) {
				polarity = 2;
			}
			else if (polarities.getSelection() == pol0radio) {
				polarity = 0;
			}
			else polarity = 4;
			Long ID = Long.parseLong(postIDfield.getText());
			String user = postuserfield.getText();
			String content = postcontentfield.getText();
			if (ID != null && !user.equals("") && !content.equals("")) {
				tweets.addTweet(new Tweet(polarity, ID, user, content));
			}
			else JOptionPane.showMessageDialog(getParent(), "Fill all fields to post a tweet!");
			postIDfield.setText("");
			postuserfield.setText("");
			postcontentfield.setText("");
			pol2radio.setSelected(false);
			genIDcheckbox.setSelected(false);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(getParent(), "Enter a valid ID!");
		}
	}
	//To be automatically called by MainFrame when window is closed
	public void writeOut() {
		tweets.writeOut(dataOutFile);
	}
}
	
	
