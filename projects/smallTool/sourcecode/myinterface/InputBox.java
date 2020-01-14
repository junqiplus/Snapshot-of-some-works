package myinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputBox extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel up_panel,down_panel;
	JTextField inputString;
	JButton button_confirm,
			button_cancel;
	
	public InputBox(String title,char []message){
	//�������
		super();
		this.setTitle(title);
		up_panel=new JPanel();
		down_panel=new JPanel();
		inputString=new JTextField();
		button_confirm=new JButton("ȷ��");
		button_cancel=new JButton("ȡ��");
	//��ť���¼�����	
	button_confirm.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			char []input=inputString.getText().toCharArray();
			for(int i=0;i<input.length;++i) {
				message[i]=input[i];
			}
			InputBox.this.dispose();
			}
	});	
	button_cancel.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			InputBox.this.dispose();
			}
	});
	
	inputString.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar()==10) {
				char []input=inputString.getText().toCharArray();
				for(int i=0;i<input.length;++i) {
					message[i]=input[i];
				}
				InputBox.this.dispose();
			}
		}
	});
	//��Ϣ�����ã����塢�����Ϣ���ޱ߿򡢲��ɱ༭	
		inputString.setFont(new Font("",Font.BOLD,14));
		inputString.setText(null);
		inputString.setPreferredSize(new Dimension(270,25));
		inputString.setEditable(true);
	//��������	
		setLayout(new GridLayout(2,1));
		up_panel.setLayout(new FlowLayout(1,0,35));
		down_panel.setLayout(new FlowLayout(1,35,8));
		up_panel.setBackground(new Color(237,243,252));
		down_panel.setBackground(new Color(237,243,252));
		add(up_panel);
		add(down_panel);
		up_panel.add(inputString);
		down_panel.add(button_confirm);
		down_panel.add(button_cancel);
	//��������	
		setModal(true);
		setLocation(500,230);
		setSize(390,230);
		setResizable(false);
		setVisible(true);
	}
	
	public static String getString() {
		char []msg=new char[100];
		
		new InputBox("�ı��ļ�·��",msg);
		return String.valueOf(msg).trim();
	}
}