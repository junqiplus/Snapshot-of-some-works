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
		catch(IOException e) {new MessageBox("�ļ�"+filePath+"��ȡʧ�ܣ�");}
	}
	
	public void analyze(String keyWord) {
		
			int dataLines=data.size(); //��������ٶ�
			char []keyWordArray=keyWord.toCharArray();		//�ؼ�������
			int iteKeyWord=0;		//�ؼ��ֵ���
			char []textLine;		//һ���ı�
			int words=0;			//ÿ�йؼ�������
			for(int ite=0;ite<dataLines;++ite) {
				textLine=data.get(ite).toCharArray();	//ȡһ���ı�
				words=0;			//ÿһ�У���ʼ��Ϊ���
				//����ÿ���ַ���������ǵ��ʵ��׸��ַ�����ʼ�Ƚϣ��������ַ��ͽ�ָ���ƶ�����һ�����ַ�
				//����ȶԳɹ�����words++
				for(int i=0;i<textLine.length;++i) {
					iteKeyWord=0;
					//��������ĸ����
					if(i==0||textLine[i-1]<'A'||(textLine[i-1]>'Z'&&textLine[i-1]<'a')||textLine[i-1]>'z')
						for(;i<textLine.length&&iteKeyWord<keyWordArray.length;++i,++iteKeyWord)
							if(((textLine[i]>='A'&&textLine[i]<='Z')||(textLine[i]>='a'&&textLine[i]<='z'))&&
								textLine[i]==keyWordArray[iteKeyWord]);
							else break;
					//�����ƶ�������ĩβ
					else
						for(;i<textLine.length;++i)
							 if((textLine[i]>='A'&&textLine[i]<='Z')||(textLine[i]>='a'&&textLine[i]<='z'));
							 else break;
					//�ж��ؼ���		
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
