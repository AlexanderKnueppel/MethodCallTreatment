package de.tubs.mt.codegen.structure;


/**
 * The Class TextCode.
 */
public class TextCode implements Code {

	/** The code. */
	private String code;
	
	/**
	 * Instantiates a new text code.
	 */
	public TextCode(){}
	
	/**
	 * Instantiates a new text code.
	 *
	 * @param code the code
	 */
	public TextCode(String code){
		this.code = code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code){
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.structure.Code#getCode()
	 */
	@Override
	public String getCode() {
		return code;
	}
}
