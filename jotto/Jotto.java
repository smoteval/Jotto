package jotto;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.BorderLayout;	
import java.awt.GridBagConstraints;

public class Jotto{

	public static final String[] LEVELS = {"Easy", "Medium", "Hard", "Any Difficulty"};	
	public static final String[] GAMES = {"Single Player","Multi Player"};	
	private static WordList list = new WordList("words.txt");


	public static void main(String[] args){	
		JFrame frame = new JFrame("Jotto Game");
		JottoModel model;
		JottoModel model2;
		String play = (String) JOptionPane.showInputDialog(frame, 
	        "Single or Multy",
	        "Players",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        GAMES, 
	        GAMES[0]);

		if(play == "Multi Player") {
			String word1 = JOptionPane.showInputDialog(frame, "Player 1's word for player 2:", "Player 1", JOptionPane.QUESTION_MESSAGE);
			word1 = word1.toUpperCase();
			while (!list.contains(word1)) {
				word1 = JOptionPane.showInputDialog(frame, "Word should be in the dictionary of 5 character words", "Player 1", JOptionPane.WARNING_MESSAGE);
				word1 = word1.toUpperCase();
			}
			String word2 = JOptionPane.showInputDialog(frame, "Player 2's word for player 1:", "Player 2", JOptionPane.QUESTION_MESSAGE);
			word2 = word2.toUpperCase();
			while (!list.contains(word2)) {
				word2 = JOptionPane.showInputDialog(frame, "Word should be in the dictionary of 5 character words", "Player 2", JOptionPane.WARNING_MESSAGE);
				word2 = word2.toUpperCase();
			}
			model = new JottoModel(word1);
			model2 = new JottoModel(word2);
			// create View, tell it about model (and controller)
			View view = new View(model);
			TableView table = new TableView(model);
			HintView hints = new HintView(model);
			View view2 = new View(model2);
			TableView table2 = new TableView(model2);
			HintView hints2 = new HintView(model2);			
			// tell Model about View. 
			model.addObserver(view);
			model.addObserver(table);
			model.addObserver(hints);
			model2.addObserver(view2);
			model2.addObserver(table2);
			model2.addObserver(hints2);
			
			// let all the views know that they're connected to the model
			model.notifyObservers();
			model2.notifyObservers();
			
			JPanel p = new JPanel(new GridLayout());	
			JPanel north = new JPanel(new BorderLayout());
			north.setBorder(BorderFactory.createTitledBorder("Guessing"));
			north.setSize(100,100);
			north.add(view, BorderLayout.NORTH);
			JPanel south = new JPanel(new BorderLayout());
			south.setBorder(BorderFactory.createTitledBorder("Table"));
			south.setSize(100,100);
			south.add(table, BorderLayout.NORTH);
			JPanel n = new JPanel(new BorderLayout());
			n.setBorder(BorderFactory.createTitledBorder("Hints"));
			n.setSize(100,800);
			n.add(hints, BorderLayout.NORTH);
			p.add(south);
			p.add(north);
			p.add(n);

			JPanel p2 = new JPanel(new GridLayout());	
			JPanel north2 = new JPanel(new BorderLayout());
			north2.setBorder(BorderFactory.createTitledBorder("Guessing"));
			north2.setSize(100,100);
			north2.add(view2, BorderLayout.NORTH);
			JPanel south2 = new JPanel(new BorderLayout());
			south2.setBorder(BorderFactory.createTitledBorder("Table"));
			south2.setSize(100,100);
			south2.add(table2, BorderLayout.NORTH);
			JPanel n2 = new JPanel(new BorderLayout());
			n2.setBorder(BorderFactory.createTitledBorder("Hints"));
			n2.setSize(100,800);
			n2.add(hints2, BorderLayout.NORTH);
			p2.add(south2);
			p2.add(north2);
			p2.add(n2);

			JTabbedPane tabs = new JTabbedPane();
			
			tabs.add("Player 2", p);
			tabs.add("Player 1", p2);
			frame.getContentPane().add(tabs);			
		}
		else 
		{	
			int response = JOptionPane.showConfirmDialog(null, "Do you want to choose the target word?", "Target Word",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
			    String diff = (String) JOptionPane.showInputDialog(frame, 
		        "Which level of difficulity?",
		        "Level",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        LEVELS, 
		        LEVELS[0]);
		        if( response == 3) {
		        	model = new JottoModel();
		        }
		        else {
		        	model = new JottoModel(response);
		        }	
		    } 
		    else if (response == JOptionPane.YES_OPTION) {
		       String word = JOptionPane.showInputDialog(frame, "Enter a word", "Jotto input", JOptionPane.QUESTION_MESSAGE);
		       word = word.toUpperCase();
				while (!list.contains(word)) {
					word = JOptionPane.showInputDialog(frame, "Word should be in the dictionary of 5 character words", "Jotto input", JOptionPane.WARNING_MESSAGE);
					word = word.toUpperCase();
				}
				model = new JottoModel(word);		              
		    }
		    else {
		    	model = new JottoModel();
		    }        


			
			// create View, tell it about model (and controller)
			View view = new View(model);
			TableView table = new TableView(model);
			HintView hints = new HintView(model);
			// tell Model about View. 
			model.addObserver(view);
			model.addObserver(table);
			model.addObserver(hints);
			
			// let all the views know that they're connected to the model
			model.notifyObservers();
			
			// create the window
			JPanel p = new JPanel(new GridLayout());
	        GridBagConstraints gc = new GridBagConstraints();
	        // gc.fill = GridBagConstraints.HORIZONTAL;
	        // gc.anchor = GridBagConstraints.NORTH;
	        // gc.gridwidth = 1;
	        // gc.gridheight = 2;
	        // gc.weightx = 0.1;
	        // gc.weighty = 0.3;
	        frame.getContentPane().add(p);		
			JPanel north = new JPanel(new BorderLayout());
			north.setBorder(BorderFactory.createTitledBorder("Guessing"));
			north.setSize(100,100);
			north.add(view, BorderLayout.NORTH);
			JPanel south = new JPanel(new BorderLayout());
			south.setBorder(BorderFactory.createTitledBorder("Table"));
			south.setSize(100,100);
			south.add(table, BorderLayout.NORTH);
			JPanel n = new JPanel(new BorderLayout());
			n.setBorder(BorderFactory.createTitledBorder("Hints"));
			n.setSize(100,800);
			n.add(hints, BorderLayout.NORTH);
			p.add(south);
			p.add(north);
			p.add(n);
	    }
		
		//p.add(view2);
		
		frame.setPreferredSize(new Dimension(990,200));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 300);
		frame.setVisible(true);
		//System.exit(0);
	} 
}