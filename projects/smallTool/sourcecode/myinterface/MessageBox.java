package myinterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//提示信息对话框类：可以很方便的弹出一个对话框用来显示一行信息。在出现Exception时可以利用这个类来显示提示信息
//使用方法：new Tipdialog("message");
//		点击对话框内的"确定"按钮，可以关闭对话框

public class MessageBox extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel up_panel,down_panel;
	JTextField the_remindnotes;
	JButton button_confirm;
	
	public MessageBox(String message){
	//创建组件
		super();
		setTitle("message");
		up_panel=new JPanel();
		down_panel=new JPanel();
		the_remindnotes=new JTextField();
		button_confirm=new JButton("确定");
	//按钮的事件处理	
	button_confirm.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			MessageBox.this.dispose();
			}
	});	
	//信息框设置：字体、添加信息、无边框、不可编辑	
		the_remindnotes.setFont(new Font("",Font.BOLD,14));
		the_remindnotes.setText(message);
		the_remindnotes.setBorder(null);
		the_remindnotes.setEditable(false);
	//布局设置	
		setLayout(new GridLayout(2,1,0,40));
		up_panel.setLayout(new FlowLayout(1,0,35));
		down_panel.setLayout(new FlowLayout(1,0,8));
		add(up_panel);
		add(down_panel);
		up_panel.add(the_remindnotes);
		down_panel.add(button_confirm);
	//窗口设置	
		setModal(true);
		setLocation(500,230);
		setSize(350,210);
		setResizable(false);
		setVisible(true);
	}
	
}