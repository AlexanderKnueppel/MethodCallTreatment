package de.tubs.mt.codegen.methods;

public class MethodNameHelper {

	private static final int BUCHSTABEN = 26;

	public static String getName(int number) {

		StringBuilder b = new StringBuilder();
		while (number > 0) {
			int ziffer = number % BUCHSTABEN;
			number /= BUCHSTABEN;
			char buchstabe = (char) ((ziffer != 0 ? ziffer : 26) + 'a' - 1);
			b.append(buchstabe);
		}
		return b.toString();
	}
}
