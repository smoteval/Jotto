package jotto;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;
import java.awt.GridLayout;


class HintView extends JPanel implements Observer {
	private JButton button;
    private JButton button2; 
    private JButton button3;
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JottoModel model;
	private JFrame frame = (JFrame)this.getParent();

	HintView(JottoModel model_) {
		button = new JButton("Letters Guessed");
		button.setMaximumSize(new Dimension(100, 50));
		button.setPreferredSize(new Dimension(100, 30));
        button2 = new JButton("Exact letters");
        button2.setMaximumSize(new Dimension(100, 50));
        button2.setPreferredSize(new Dimension(100, 30));    
        button3 = new JButton("Partial letters");
        button3.setMaximumSize(new Dimension(100, 50));
        button3.setPreferredSize(new Dimension(100, 30));             
		label = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();

        this.setLayout(new GridLayout(3,2));
		//this.add(button, BorderLayout.NORTH);
		this.add(button);
		this.add(label);
		this.add(button2);
		this.add(label2);
		this.add(button3);
		this.add(label3);

		model = model_;

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	label.setText(model.charsguessed());
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	label2.setText(model.charsexact());
            }
        });   
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	label3.setText(model.charspartial());
            }
        });             	
	}

	public void update(Observable arg0, Object arg1) {
		label.setText("");
		label2.setText("");
		label3.setText("");
	}

}