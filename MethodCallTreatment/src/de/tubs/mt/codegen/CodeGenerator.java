package de.tubs.mt.codegen;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public interface CodeGenerator extends Serializable {

	public abstract void generateCode(OutputStream stream, int depth, int width) throws IOException;

	public abstract void generateCode(OutputStream stream, int depth, int width, String name) throws IOException;

}