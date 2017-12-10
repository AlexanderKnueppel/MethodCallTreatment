package de.tubs.mt.codegen.structure;

public class TextCode implements Code {

	private String code;
	
	public TextCode(){}
	
	public TextCode(String code){
		this.code = code;
	}
	
	public void setCode(String code){
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
