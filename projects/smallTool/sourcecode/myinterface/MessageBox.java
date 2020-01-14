package myinterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//��ʾ��Ϣ�Ի����ࣺ���Ժܷ���ĵ���һ���Ի���������ʾһ����Ϣ���ڳ���Exceptionʱ�����������������ʾ��ʾ��Ϣ
//ʹ�÷�����new Tipdialog("message");
//		����Ի����ڵ�"ȷ��"��ť�����ԹرնԻ���

public class MessageBox extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel up_panel,down_panel;
	JTextField the_remindnotes;
	JButton button_confirm;
	
	public MessageBox(String message){
	//�������
		super();
		setTitle("message");
		up_panel=new JPanel();
		down_panel=new JPanel();
		the_remindnotes=new JTextField();
		button_confirm=new JButton("ȷ��");
	//��ť���¼�����	
	button_confirm.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			MessageBox.this.dispose();
			}
	});	
	//��Ϣ�����ã����塢�����Ϣ���ޱ߿򡢲��ɱ༭	
		the_remindnotes.setFont(new Font("",Font.BOLD,14));
		the_remindnotes.setText(message);
		the_remindnotes.setBorder(null);
		the_remindnotes.setEditable(false);
	//��������	
		setLayout(new GridLayout(2,1,0,40));
		up_panel.setLayout(new FlowLayout(1,0,35));
		down_panel.setLayout(new FlowLayout(1,0,8));
		add(up_panel);
		add(down_panel);
		up_panel.add(the_remindnotes);
		down_panel.add(button_confirm);
	//��������	
		setModal(true);
		setLocation(500,230);
		setSize(350,210);
		setResizable(false);
		setVisible(true);
	}
	
}