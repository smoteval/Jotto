package jotto;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;


class View extends JPanel implements Observer {
	private JButton button;
    private JButton button2; 
	private JTextField text;
	private JottoModel model;
	private JFrame frame = (JFrame)this.getParent();

	View(JottoModel model_) {
		button = new JButton("Make a guess");
		button.setMaximumSize(new Dimension(100, 50));
		button.setPreferredSize(new Dimension(100, 30));
        button2 = new JButton("I quit, Reveal the word");
        button2.setMaximumSize(new Dimension(100, 50));
        button2.setPreferredSize(new Dimension(100, 30));        
		text = new JTextField("Your Guess");

        this.setLayout(new BorderLayout());
		this.add(button, BorderLayout.CENTER);
        this.add(button2, BorderLayout.SOUTH);
		this.add(text, BorderLayout.NORTH);

		model = model_;

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String g = text.getText();
            	g = g.toUpperCase();
                boolean won = model.guess(g);
                if( won ) {
                	JOptionPane.showMessageDialog(frame, "You won", "WINNER!!", JOptionPane.INFORMATION_MESSAGE);
                	System.exit(0);
                }
                else if (model.legal_guess()) {
                	JOptionPane.showMessageDialog(frame, model.reason(), "ERROR", JOptionPane.WARNING_MESSAGE);
                }
                if(model.CUR_GUESSES >=5) {
                	JOptionPane.showMessageDialog(frame,"The word is " + model.target(),"No more guesses" , JOptionPane.INFORMATION_MESSAGE);
                	System.exit(0);
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, model.target(), "Target Word", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                
            }
        });        	
	}

	public void update(Observable arg0, Object arg1) {
		text.setText("Guess # " + (model.guesses()+1));
	}

}