package jotto;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;	
import java.util.Observable;
import java.util.Observer;


class TableView extends JPanel implements Observer {
	private JTable table;
	private JottoModel model;
	private JFrame frame = (JFrame)this.getParent();

	TableView(JottoModel model_) {
		table = new JTable(11,3);

        this.setLayout(new BorderLayout());
		this.add(table, BorderLayout.NORTH);
		//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		//table.setFillsViewportHeight(true);
		//JScrollPane scrollPane = new JScrollPane(table);
		//add(scrollPane);

        table.setValueAt("Words", 0, 0);
        table.setValueAt("Exact", 0, 1);
        table.setValueAt("Partial", 0, 2);        

		model = model_;	
	}

	public void update(Observable arg0, Object arg1) {
		if( model.guesses() !=0 ) {
            table.setValueAt(model.Whatwastheguess(), model.guesses(), 0);
            table.setValueAt(model.exact(), model.guesses(), 1);
            table.setValueAt(model.partial(), model.guesses(), 2);             
        }
	}

}