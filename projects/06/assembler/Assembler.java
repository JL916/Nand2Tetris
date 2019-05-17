package assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assembler {
	
	private Parser parser;
	private String filename;
	
	public Assembler () {
		parser = new Parser();
	}
	
	public void assemble (String filename) {
		this.filename = filename;
		firstPass();
		secondPass();
	}
	
	private void secondPass () {
		// �ڶ��α��� Ϊ���������ַ�ռ� ������ÿ����� �����".hack"�ļ�
		try {
			File file = new File(filename);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String str;
			FileWriter filewriter = new FileWriter(filename.replace(".asm", ".hack"));
			int n = 16;
			while ((str = bufferedReader.readLine()) != null)  {
				str = str.replace(" ", "");
				if (str.contains("//")) {
					str = str.substring(0, str.indexOf("//"));
				}
				if (str.length() == 0)
					continue;
				if (str.contains("("))
					continue;
				if (str.contains("@")) {
					String t = str.substring(1);
					if (!Parser.isNumeric(t) && !Parser.symboltable.contains(t)) {
						Parser.symboltable.add(t, n++);
					}
				} 
				
				String s = parser.parse(str);
				filewriter.write(s + "\n");
			}
			bufferedReader.close();
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void firstPass () {
		// ��һ�α��� ȷ�����б�ǩλ��
		try {
			File file = new File(filename);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String str;
			int line = 0;
			while ((str = bufferedReader.readLine()) != null)  {
				str = str.replace(" ", "");
				if (str.contains("//")) {
					str = str.substring(0, str.indexOf("//"));
				}
				if (str.length() == 0)
					continue;
				if (!str.contains("(")) {
					line++;
					continue;
				}
				Parser.symboltable.add(str.substring(1, str.length()-1), line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Assembler assembler = new Assembler();
		assembler.assemble(args[0]);
	}
}
