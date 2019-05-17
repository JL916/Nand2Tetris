package assembler;

import java.util.HashMap;

public class SymbolTable {
	
	private static HashMap <String, Integer> symboltable;
	
	public SymbolTable () {
		symboltable = new HashMap<>();
		initTable();
	}
	
	private void initTable () {
		symboltable.put("SP", 0);
		symboltable.put("LCL", 1);
		symboltable.put("ARG", 2);
		symboltable.put("THIS", 3);
		symboltable.put("THAT", 4);
		for (int i = 0; i < 16; i++) {
			symboltable.put("R" + i, i);
		}
		symboltable.put("SCREEN", 16384);
		symboltable.put("KBD", 24576);
	}
	
	public HashMap<String, Integer> getSymbolTable () {
		return symboltable;
	}
	
	public void add (String symbol, int integer) {
		symboltable.put(symbol, integer);
	}
	
	public boolean contains (String symbol) {
		return symboltable.containsKey(symbol);
	}
	
	public int get (String symbol) {
		return symboltable.get(symbol);
	}
}
