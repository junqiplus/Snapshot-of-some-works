package myinterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatisticDialog extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane mainPanal;
	JTextArea mainText;

	
	StatisticDialog(String title,String text) {
		super(title);
		mainPanal=new JScrollPane();
		mainText=new JTextArea();
    	
		mainText.setText(text);
		mainText.setEditable(false);
		mainText.setBackground(new Color(248,255,240));
		mainText.setBorder(new LineBorder(Color.cyan,1,true));
		
    	add(mainPanal);
    	mainPanal.setViewportView(mainText);
    	
    	setSize(420, 250);
    	setResizable(false);
    	setLocation(350,230);
    	setVisible(true);
    }
}
