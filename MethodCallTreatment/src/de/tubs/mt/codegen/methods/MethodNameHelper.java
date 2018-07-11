package de.tubs.mt.codegen.methods;


/**
 * The Class MethodNameHelper.
 */
class MethodNameHelper {

	/** The Constant BUCHSTABEN. */
	private static final int BUCHSTABEN = 26;

	/**
	 * Gets the name.
	 *
	 * @param number the number
	 * @return the name
	 */
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
