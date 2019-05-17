package assembler;

import java.util.HashMap;

public class Code {

	private static HashMap<String, String> destMap;
	private static HashMap<String, String> compMap;
	private static HashMap<String, String> jumpMap;
	
	public Code () {
		destMap = new HashMap<>();
		compMap = new HashMap<>();
		jumpMap = new HashMap<>();
		initDestMap();
		initCompMap();
		initJumpMap();
	}
	
	private void initDestMap () {
		destMap.put("null", "000");
		destMap.put("M", "001");
		destMap.put("D", "010");
		destMap.put("MD", "011");
		destMap.put("A", "100");
		destMap.put("AM", "101");
		destMap.put("AD", "110");
		destMap.put("AMD", "111");
	}
	
	private void initCompMap () {
		compMap.put("0", "101010");
		compMap.put("1","111111");
		compMap.put("-1", "111010");
		compMap.put("D", "001100");
		compMap.put("A", "110000");
		compMap.put("!D", "001101");
		compMap.put("!A", "110001");
		compMap.put("-D", "001111");
		compMap.put("-A", "110011");
		compMap.put("D+1", "011111");
		compMap.put("A+1", "110111");
		compMap.put("D-1", "001110");
		compMap.put("A-1", "110010");
		compMap.put("D+A", "000010");
		compMap.put("D-A", "010011");
		compMap.put("A-D", "000111");
		compMap.put("D&A", "000000");
		compMap.put("D|A", "010101");
		
		compMap.put("M", "110000");
		compMap.put("!M", "110001");
		compMap.put("-M", "110011");
		compMap.put("M+1", "110111");
		compMap.put("M-1", "110010");
		compMap.put("D+M", "000010");
		compMap.put("D-M", "010011");
		compMap.put("M-D", "000111");
		compMap.put("D&M", "000000");
		compMap.put("D|M", "010101");
	}
	
	private void initJumpMap () {
		jumpMap.put("null", "000");
		jumpMap.put("JGT", "001");
		jumpMap.put("JEQ", "010");
		jumpMap.put("JGE", "011");
		jumpMap.put("JLT", "100");
		jumpMap.put("JNE", "101");
		jumpMap.put("JLE", "110");
		jumpMap.put("JMP", "111");
	}
	
	public String dest (String dest) {
		return destMap.get(dest);
	}
	
	public String comp (String comp) {
		return compMap.get(comp);
	}
	
	public String jump (String jump) {
		return jumpMap.get(jump);
	}
}
