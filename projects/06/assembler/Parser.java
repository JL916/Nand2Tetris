package assembler;

public class Parser {

	private static final String AInstructionPre = "0000000000000000";
	private static final String DInstructionPre = "111";
	
	private static Code code;
	static SymbolTable symboltable;
	
	public Parser () {
		code = new Code();
		symboltable = new SymbolTable();
	}
	
	// 解析语句
	public String parse (String  statement) {
		if (statement.contains("@")) 		// A Instruction
			return getAInstruction (statement.substring(1));
		else								// C Instruction
			return getDInstruction (statement);
	}
	
	private String getAInstruction (String s) {
		int x;
		// 判断是地址还是变量 如: @16 or @count or @LOOP
		if (!isNumeric(s)) {	
			x = symboltable.get(s);
		} else {				
			x = Integer.parseInt(s);
		}
		// 加16个"0"前缀
		String ins = AInstructionPre + Integer.toBinaryString(x);
		
		return ins.substring(ins.length() - 16, ins.length());
	}
	
	private String getDInstruction (String s) {
		StringBuilder sb = new StringBuilder(DInstructionPre);
		
		int index1 = 0;
		int index2 = s.length();
		String dest;
		String jump;
		
		// (dest=)comp(;jump)
		// index1 和 index2 将 instruction 分隔开
		// dest + index1 + comp + index2 + jump
		
		if (s.contains("=")) {
			index1 = s.indexOf("=") + 1;
			dest = code.dest(s.substring(0, index1 - 1));
		} else {
			dest = code.dest("null");
		}
		
		if (s.contains(";")) {
			index2 = s.indexOf(";");
			jump = code.jump(s.substring(index2 + 1, s.length()));
		} else {
			jump = code.dest("null");
		}
		
		String comp = code.comp(s.substring(index1, index2));
		if (s.substring(index1, index2).contains("M"))
			sb.append("1");
		else
			sb.append("0");
		
		sb = sb.append(comp).append(dest).append(jump);
		
		return sb.toString();
	}
	
	// 判断是否为纯数字 即判断是否为地址
	public static boolean isNumeric (String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
}
