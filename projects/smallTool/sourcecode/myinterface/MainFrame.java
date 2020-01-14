package myinterface;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import myanalyzer.WordsAnalyzer;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel topPanel,
		   bottomPanel;
	JScrollPane middlePanel;
	JButton statisticButton,
			filePathButton;
	JTextArea textDisplyArea;		//文本显示区
	JLabel keyWordLabel;
	JTextField keyWordInput,		//关键字输入
			   filePathDisplay;		//当前文本路径
	WordsAnalyzer wordsAnalyzer;

	MainFrame(String title){
		super(title);
		topPanel=new JPanel();
		middlePanel=new JScrollPane();
		bottomPanel=new JPanel();
		statisticButton=new JButton("查看详情");
		filePathButton=new JButton("选择文件");
		
		textDisplyArea=new JTextArea();
		textDisplyArea.setBorder(new LineBorder(Color.YELLOW,2,true));
		textDisplyArea.setEditable(false);
		
		keyWordLabel=new JLabel("关键字");
		keyWordInput=new JTextField();keyWordInput.setPreferredSize(new Dimension(110,25));
		filePathDisplay=new JTextField();
		
		wordsAnalyzer=new WordsAnalyzer();
		
		
		filePathButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wordsAnalyzer.source(InputBox.getString());
				filePathDisplay.setText(wordsAnalyzer.getFilepath());
				bottomPanel.validate();
				String text="";
				int lines=wordsAnalyzer.lines();
				for(int i=0;i<lines;++i) {
					text+=String.format("%-6d", i+1);
					text+=wordsAnalyzer.getLine(i);
					text+='\n';
				}
				textDisplyArea.setText(text);
				
			}
		});
		
		statisticButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String keyWord;
				keyWord=keyWordInput.getText().trim();
				if(keyWord!=null) {
					wordsAnalyzer.analyze(keyWord);
				}
				String statistics='\"'+keyWord+'\"'+"统计结果：\n";
				int lines=wordsAnalyzer.lines();	//文本行数
				int totalKeyWord=0;				//关键词总数
				int currentLine=0;
				for(int i=0;i<lines;++i) {
					currentLine=wordsAnalyzer.getCounts(i);
					if(currentLine!=0) {
						totalKeyWord+=currentLine;
						statistics+=("第"+(i+1)+"行:"+currentLine+"个\n");
					}
					
				}
				statistics+="总计："+totalKeyWord+"个";
				new StatisticDialog("关键字统计",statistics);
				
			}
		});
		
		add(topPanel,BorderLayout.NORTH);
		add(middlePanel,BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,35,5));
		topPanel.add(keyWordLabel);
		topPanel.add(keyWordInput);
		topPanel.add(statisticButton);
		topPanel.add(filePathButton);
		keyWordInput.setEditable(true);
		topPanel.setBackground(new Color(230,245,253));
		
		middlePanel.setViewportView(textDisplyArea);
		
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.setBackground(new Color(230,245,253));
		bottomPanel.add(filePathDisplay);
		filePathDisplay.setEditable(false);
		filePathDisplay.setBorder(null);
		
		
		setLocation(250,90);
		setSize(870,550);
		setVisible(true);
		
	}
	
	
	
	public static void main(String args[]) {
		new MainFrame("文学助手");
		
		/*try {
		BufferedReader br=new BufferedReader(new FileReader("D:\\abc.txt"));
		System.out.println(br.readLine());
		br.close();
		}
		catch(IOException e) {new MessageBox("文件读取失败！");}*/
	}
}
