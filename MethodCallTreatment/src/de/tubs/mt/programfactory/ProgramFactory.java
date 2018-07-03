package de.tubs.mt.programfactory;

public abstract class ProgramFactory {

	private static IProgram program = null;

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
