package de.tubs.mt.codeanalyze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import de.tubs.mt.FileControl;

public abstract class MethodPrinter {

    public static void recreateJavaFile(String input, int seed) throws Exception {
        FileInputStream in = new FileInputStream("TestClasses/" + input);

        CompilationUnit cu = JavaParser.parse(in);
        List<Comment> lc = cu.getComments();
        
        //cu.getAllContainedComments().get(2).remove();
        
        /**
        for (Comment child : cu.getAllContainedComments()) {
            child.remove();
        }
		**/

        // visit and print the methods names
        //cu.accept(new MethodVisitor(), null);
        
        List<String> lines = new LinkedList<String>();
        lines.add(cu.toString());
        
        
        File f = new File(FileControl.getTmpPath().getPath() + "/" + seed);
		f.mkdir();
        
        Files.write(Paths.get(FileControl.getTmpPath().getPath() + "/" + seed + "/" + input), lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);
        
        
        
    }

    
    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this 
             CompilationUnit, including inner class methods */
            //System.out.println(n.);
            super.visit(n, arg);
        }
    }
}