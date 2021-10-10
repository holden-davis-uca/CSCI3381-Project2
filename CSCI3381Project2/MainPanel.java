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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MainPanel extends JPanel{
	
	//Global/shared elements
	private TweetCollection tweets;
	private JTextField titlebanner;
	private JButton backbutton;
	
	//Login elements
	private JTextField loginunametf;
	private JButton loginanonbutton;
	private JButton loginuserbutton;
	
	//Core elements
	private JButton corebackbutton;
	private JButton coresearchbutton;
	private JButton corepostbutton;
	private JButton corepredictbutton;
	private JButton corerefreshbutton;
	private JScrollPane corescrollpane;
	private DefaultListModel<String> coremodel;
	private JList<String> corelist;
	
	//Search elements
	private JTextField searchfield;
	private JButton deletetweetbutton;
	private JButton searcherbutton;
	private JComboBox<String> searchoptioncombobox;
	
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
		
		titlebanner = new JTextField();
		titlebanner.setEditable(false);
		titlebanner.setForeground(Color.WHITE);
		titlebanner.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 20));
		titlebanner.setBackground(Color.DARK_GRAY);
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
		
		coremodel = new DefaultListModel<String>();
		corelist = new JList<String>(coremodel);
		corescrollpane = new JScrollPane(corelist);
		corescrollpane.setBounds(200, 100, 500, 250);
		coreTweets(loginunametf.getText());
		add(corescrollpane);

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
		searchfield.setBounds(20, 193, 120, 60);
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
		deletetweetbutton.setBounds(400, 401, 98, 26);
		deletetweetbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(corelist.getSelectedValue());
				}
			}
		);
		add(deletetweetbutton);
		
		//Initialized post elements

		//Initialize predict elements
		
		//Always start at login screen
		toLogin();
		
	}

	public void toLogin() {
		loginunametf.setVisible(true);
		loginanonbutton.setVisible(true);
		loginuserbutton.setVisible(true);
		
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		corepredictbutton.setVisible(false);
		corerefreshbutton.setVisible(false);
		titlebanner.setText("JTwitter");
		corelist.setVisible(false);
		corescrollpane.setVisible(false);
	}
	public void toCore(String username) {
		coreTweets(loginunametf.getText());
		loginunametf.setVisible(false);
		loginanonbutton.setVisible(false);
		loginuserbutton.setVisible(false);
		
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		
		corebackbutton.setVisible(true);
		coresearchbutton.setVisible(true);
		corepostbutton.setVisible(true);
		corepredictbutton.setVisible(true);
		corelist.setVisible(true);
		corescrollpane.setVisible(true);
	}
	public void coreTweets(String user) {
		if (user.equals("Username")){
			coremodel.removeAllElements();
			titlebanner.setText("Random Tweets");
			for (int i = 0; i < 30; i++) {
				Tweet randtweet = tweets.randomTweet();
				coremodel.addElement(randtweet.toString());
			}
			corerefreshbutton.setVisible(true);

		}
		else {
			coremodel.removeAllElements();
			titlebanner.setText("My Tweets");
			ArrayList<Tweet> utweets = tweets.searchByUser(user);
			for (Tweet utweet : utweets) {
				coremodel.addElement(utweet.toString());
			}
			corerefreshbutton.setVisible(false);

		}
		
	}
	
	public void toSearch(String username) {
		coremodel.removeAllElements();
		titlebanner.setText("Search");
		backbutton.setVisible(true);

		loginunametf.setVisible(false);
		loginanonbutton.setVisible(false);
		loginuserbutton.setVisible(false);
		
		searchfield.setVisible(true);
		searchfield.setText(loginunametf.getText());
		deletetweetbutton.setVisible(true);
		searcherbutton.setVisible(true);
		searchoptioncombobox.setVisible(true);
		searchoptioncombobox.setSelectedIndex(1);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		corepredictbutton.setVisible(false);
		corerefreshbutton.setVisible(false);
		corelist.setVisible(true);
		corescrollpane.setVisible(true);
	}
	
	public void search(String query) {
		coremodel.removeAllElements();
		//Searching by ID
		if (searchoptioncombobox.getSelectedIndex() == 0) {
			int tweetid = Integer.parseInt(query);
			coremodel.addElement(tweets.searchByID(tweetid).toString());
		}
		//Searching by user
		else {
			ArrayList<Tweet> utweets = tweets.searchByUser(query);
			for (Tweet utweet : utweets) {
				coremodel.addElement(utweet.toString());
			}
		}
	}
	
	public void delete(String tweetstring) {
		String[] stuff = tweetstring.split(",");
		tweets.removeTweet(tweets.searchByID(Long.parseLong(stuff[1])));
		search(searchfield.getText());
	}
	
	public void toPost(String username) {
		titlebanner.setText("Post");
		backbutton.setVisible(true);
		
		loginunametf.setVisible(false);
		loginanonbutton.setVisible(false);
		loginuserbutton.setVisible(false);
		
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		corepredictbutton.setVisible(false);
		corerefreshbutton.setVisible(false);
		corelist.setVisible(false);
		corescrollpane.setVisible(false);

	}
	public void toPredict(String username) {
		titlebanner.setText("Predict");
		backbutton.setVisible(true);
		
		loginunametf.setVisible(false);
		loginanonbutton.setVisible(false);
		loginuserbutton.setVisible(false);
		
		searchfield.setVisible(false);
		deletetweetbutton.setVisible(false);
		searcherbutton.setVisible(false);
		searchoptioncombobox.setVisible(false);
		
		corebackbutton.setVisible(false);
		coresearchbutton.setVisible(false);
		corepostbutton.setVisible(false);
		corepredictbutton.setVisible(false);
		corerefreshbutton.setVisible(false);
		corelist.setVisible(false);
		corescrollpane.setVisible(false);

	}
}
	
	
