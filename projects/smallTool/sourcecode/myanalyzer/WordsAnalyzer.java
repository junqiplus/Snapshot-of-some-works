package myanalyzer;
import java.io.*;
import myinterface.MessageBox;
import java.util.*;


public class WordsAnalyzer {
	Vector<String> data;
	int [] places;
	String filePath;
	
	public WordsAnalyzer(){
		data=new Vector<>();
		filePath=null;
	}
	public WordsAnalyzer(String filepath){
		this();
		source(filepath);
	}
	
	public void source(String filePath) {
		try {
			int lines=0;
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			this.filePath=filePath;
			data.removeAllElements();
			do {
				++lines;
				String line=br.readLine();
				if(line!=null)
					data.add(line);
				else
					data.add(null);
			}while(data.lastElement()!=null);
			data.remove(data.size()-1);
			places=new int[lines];
			br.close();
		}
		catch(IOException e) {new MessageBox("文件"+filePath+"读取失败！");}
	}
	
	public void analyze(String keyWord) {
		
			int dataLines=data.size(); //提高运算速度
			char []keyWordArray=keyWord.toCharArray();		//关键字数组
			int iteKeyWord=0;		//关键字迭代
			char []textLine;		//一行文本
			int words=0;			//每行关键字数量
			for(int ite=0;ite<dataLines;++ite) {
				textLine=data.get(ite).toCharArray();	//取一行文本
				words=0;			//每一行，初始化为零个
				//对于每个字符，如果它是单词的首个字符，则开始比较，不是首字符就将指针移动到下一个首字符
				//如果比对成功，则words++
				for(int i=0;i<textLine.length;++i) {
					iteKeyWord=0;
					//单词首字母条件
					if(i==0||textLine[i-1]<'A'||(textLine[i-1]>'Z'&&textLine[i-1]<'a')||textLine[i-1]>'z')
						for(;i<textLine.length&&iteKeyWord<keyWordArray.length;++i,++iteKeyWord)
							if(((textLine[i]>='A'&&textLine[i]<='Z')||(textLine[i]>='a'&&textLine[i]<='z'))&&
								textLine[i]==keyWordArray[iteKeyWord]);
							else break;
					//否则移动到单词末尾
					else
						for(;i<textLine.length;++i)
							 if((textLine[i]>='A'&&textLine[i]<='Z')||(textLine[i]>='a'&&textLine[i]<='z'));
							 else break;
					//判定关键字		
					if(iteKeyWord==keyWordArray.length)
						if(i<textLine.length){
							if(!((textLine[i]>='A'&&textLine[i]<='Z')||(textLine[i]>='a'&&textLine[i]<='z')))
								++words;}
						else
							++words;
				}
				places[ite]=words;
			}
	}
	
	public int getCounts(int lines) {
		return places[lines];
	}
	
	public String getText() {
		String text="";
		int size=data.size();
		for(int i=0;i<size;++i)
			text+=data.get(i)+'\n';
		return text;
	}
	
	public String getLine(int lines) {
		if(lines<data.size())
			return data.get(lines);
		else
			return null;
	}
	
	public int lines() {
		return data.size();
	}
	
	public String getFilepath() {
		return filePath;
	}
}
