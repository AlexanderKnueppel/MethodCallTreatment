package de.tubs.mt.codegen.methods;

public class MethodNameHelper {

	private static final int BUCHSTABEN = ('z'-'a'+1);
	
	public static String getName(int number){
		StringBuilder b = new StringBuilder();
		while(number > 0){
			int ziffer = number % BUCHSTABEN;
			number /= BUCHSTABEN;
			char buchstabe = (char) (ziffer + 'a'-1);
			b.append(buchstabe);
		}
		return b.toString();
	}
}
