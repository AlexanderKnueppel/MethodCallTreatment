package de.tubs.mt.programfactory;



/**
 * A factory for creating Program objects.
 */
public abstract class ProgramFactory {

	/** The program. */
	private static IProgram program = null;

	/**
	 * Gets the program.
	 *
	 * @param progamType the progam type
	 * @return the program
	 */
	public static IProgram getProgram(String progamType) {
		switch (progamType) {
		case "single":
			program = new SingleClass();
			break;
		case "codebase":
			program = new CodeBase();
			break;
		case "generated":
			program = new Generated();
			break;
		}
		return program;
	}
}
