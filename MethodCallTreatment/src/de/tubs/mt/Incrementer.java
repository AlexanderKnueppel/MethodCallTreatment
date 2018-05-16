package de.tubs.mt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import de.tubs.mt.codegen.CodeGenerator;
import de.tubs.mt.codegen.TreeCodeGenerator;
import de.tubs.mt.codegen.add.AddCodeGenerator;
import de.tubs.mt.codegen.add.BroadCodeGenerator;
import de.tubs.mt.codegen.add.CrossCodeGenerator;
import de.tubs.mt.codegen.add.TinyCodeGenerator;
import de.tubs.mt.codegen.bubble.BubbleCodeGenerator;

public class Incrementer {
	
	public static List<Integer> jmlWhiteList = new ArrayList<Integer>();
	public static List<Integer> randomList = new ArrayList<Integer>();
	public static int counter = 0;
	
	public static int numberOfMethods(int level, int width) {
		return (int) Math.pow(width, level-1);
	}
	
	public static int totalNumberOfMethods(int width, int depth) {
		int res = 0;
		for(int i = 1; i <= depth; ++i)
			res+=numberOfMethods(i,width);
		return res;
	}
	
	public static List<Integer> getTopDownStrategy(int iter, int depth, int width) {
		List<Integer> soph = getSophisticatedStrategy1(iter, depth);
		int count = 0;
		for(int i : soph) {
			count += numberOfMethods(i, width);
		}
		
		int count2 = 0;
		int thelevel = 0;
		while(count2 < count) {
			thelevel++;
			count2 = totalNumberOfMethods(width, thelevel);
		}
		
		int k = Math.abs(count2-count) < Math.abs(count2-totalNumberOfMethods(width, thelevel-1)-count) ? thelevel : (thelevel-1);
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 1; i <= k; ++i) {
			res.add(i);
		}
		return res;
	}
	
	public static List<Integer> getComplete(int depth) {
		List<Integer> res = new ArrayList<Integer>();
		for(int i = 0; i <= depth; ++i) {
			res.add(i);
		}
		
		return res;
	}
	
	public static List<Integer> getOnlyTop() {
		List<Integer> res = new ArrayList<Integer>();
		return res;
	}
	
	public static List<Integer> getSophisticatedStrategy1(int iter, int depth) {
		List<Integer> l = new ArrayList<Integer>();
		
		int d = (int)Math.ceil((depth+1)/2.0);
		
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(d);
		l.add(d);
		
		int runs = 0;
		
		while(d > 1 && runs++ < iter) {
			List<Integer> tmp = new ArrayList<Integer>();
			
			d /= 2;
			
			while(!stack.isEmpty()) {
				int top = stack.pop();
				tmp.add(top + d);
				tmp.add(top - d);
			}
			
			stack.addAll(tmp);
			l.addAll(tmp);
		}
		
		return l;
	}
	
	public static void execute(final int width, final int depth, int iter, String path) throws FileNotFoundException {
		
		//for(int i = 0; i < iters; ++i) {
			if(path.equals("topdown"))
				jmlWhiteList = getTopDownStrategy(iter, depth, width);
			else if(path.equals("strategy"))
				jmlWhiteList = getSophisticatedStrategy1(iter, depth);
			else if(path.equals("complete"))
				jmlWhiteList = getComplete(depth);
			else
				jmlWhiteList = getOnlyTop();
			
			// top method should have a contract
			jmlWhiteList.add(1);
			
			System.out.println(jmlWhiteList);
			final TreeCodeGenerator cg = new TinyCodeGenerator();
			
			String name = "AddIter"+(iter+1)+"Depth"+(depth)+"Width"+(width);
			
			File f = new File(path + "/" + name + ".java");
			f.delete();
			final FileOutputStream fos = new FileOutputStream(f);

			try {
				cg.generateCode(fos, depth, width, name);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			
			System.out.println("create " + f.getAbsolutePath());
		//}
		
	}
	
	public static void execute2(final int width, final int depth, int iter, String path) throws FileNotFoundException {
		
		//for(int i = 0; i < iters; ++i) {
			if(path.equals("topdown"))
				jmlWhiteList = getTopDownStrategy(iter, depth, width);
			else if(path.equals("strategy"))
				jmlWhiteList = getSophisticatedStrategy1(iter, depth);
			else if(path.equals("complete"))
				jmlWhiteList = getComplete(depth);
			else
				jmlWhiteList = getOnlyTop();
			
			// top method should have a contract
			jmlWhiteList.add(1);
			
			System.out.println(jmlWhiteList);
			final TreeCodeGenerator cg = new BroadCodeGenerator();
			
			String name = "AddIter"+(iter+1)+"Depth"+(depth)+"Width"+(width);
			
			File f = new File(path + "/" + name + ".java");
			f.delete();
			final FileOutputStream fos = new FileOutputStream(f);

			try {
				cg.generateCode(fos, depth, width, name);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			
			System.out.println("create " + f.getAbsolutePath());
		//}
		
	}
	
	public static void generateFullySpecifiedProgramAdd(final int width, final int depth, int seed, String path) throws FileNotFoundException {
		jmlWhiteList = getComplete(depth);
		TreeCodeGenerator cg = new TinyCodeGenerator();
		
		String name = "AddDepth"+(depth)+"Width"+(width);
		
		File f = new File(path + "/" + seed);
		f.mkdir();
		f = new File(path + "/" + seed + "/" + name + ".java");
		f.delete();
		final FileOutputStream fos = new FileOutputStream(f);

		try {
			cg.generateCode(fos, depth, width, name);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateFullySpecifiedProgramBubble(final int width, final int depth, int seed, String path) throws FileNotFoundException {
		counter = 1;
		randomList = getComplete(depth);
		TreeCodeGenerator cg = new BubbleCodeGenerator();
		
		String name = "BubbleDepth"+(depth)+"Width"+(width);
		
		File f = new File(path + "/" + seed);
		f.mkdir();
		f = new File(path + "/" + seed + "/" + name + ".java");
		f.delete();
		final FileOutputStream fos = new FileOutputStream(f);

		try {
			cg.generateCode(fos, depth, width, name);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generatRandomSpecifiedProgramsForAdd(final int width, final int depth, int seed, String path) throws FileNotFoundException {
		generatRandomSpecifiedProgramsForAdd(width, depth, seed, path, true);
	}
	public static void generatRandomSpecifiedProgramsForAdd(final int width, final int depth, int seed, String path, boolean includeBorders) throws FileNotFoundException {
		
		int total = totalNumberOfMethods(width, depth);
		System.out.println("Total number of methods " + total);
		
		for(int i = (includeBorders? 0 : 10); i <= (includeBorders? 100 : 90); i += 10) {
			counter = 1;
			randomList.clear();
			Random random = new Random(seed); 
			while(randomList.size() < total*i/100) {
				int n = random.nextInt(total) + 1;
				if(!randomList.contains(n)) {
					randomList.add(n);
				}
			}
			randomList.add(1);
			Collections.sort(randomList);
			System.out.println(i + "%: " + randomList);
			
			
			TreeCodeGenerator cg = new BroadCodeGenerator();
			
			String name = "AddDepth"+(depth)+"Width"+(width)+"P"+ (i == 100? "9" : "") + i;
			
			File f = new File(path + "/" + seed);
			f.mkdir();
			f = new File(path + "/" + seed + "/" + name + ".java");
			f.delete();
			final FileOutputStream fos = new FileOutputStream(f);

			try {
				cg.generateCode(fos, depth, width, name);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			
			//System.out.println("created: " + f.getAbsolutePath());
		}
		
	}
	
public static void generatRandomSpecifiedProgramsForBubbleSort(final int width, final int depth, int seed, String path, boolean includeBorders) throws FileNotFoundException {
		
		int total = totalNumberOfMethods(width, depth);
		System.out.println("Total number of methods " + total);
		
		for(int i = (includeBorders? 0 : 10); i <= (includeBorders ? 100 : 90); i += 10) {
			counter = 1;
			randomList.clear();
			Random random = new Random(seed); 
			while(randomList.size() < total * i / 100) {
				int n = random.nextInt(total) + 1;
				if(!randomList.contains(n)) {
					randomList.add(n);
				}
			}
			//randomList.add(1);
			Collections.sort(randomList);
			System.out.println(i + "%: " + randomList);
			
			final TreeCodeGenerator cg = new BubbleCodeGenerator();
			
			String name = "BubbleSortDepth" + (depth) + "Width" + (width) + "P" + (i == 100 ? "9" : "") + i;
			
			File f = new File(path + "/" + seed);
			f.mkdir();
			f = new File(path + "/" + seed + "/" + name + ".java");
			f.delete();
			final FileOutputStream fos = new FileOutputStream(f);

			try {
				cg.generateCode(fos, depth, width, name);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			System.out.println("created: " + f.getAbsolutePath());
		}
		
	}

	public static void main(final String[] args) throws IOException {
		
		//execute(final int width, final int depth, int iter, String path);
		
		execute2(8, 7, 1, "complete");
//		for(int i = 0; i < 6; ++i) {
//			System.out.println(getSophisticatedStrategy1(i, 10));
//			System.out.println(getTopDownStrategy(i, 10, 3));
//		}
		
		//generatRandomSpecifiedProgramsForAdd(2, 5, 10000, "");
		//generatRandomSpecifiedProgramsForBubbleSort(2, 5, 10000, ".", true);
		/* Choose Strategy */
		//jmlWhiteList = getTopDownStrategy(1,  Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		//jmlWhiteList = getSophisticatedStrategy1(0,  Integer.parseInt(args[0]));
		
		//final CodeGenerator cg = new BubbleCodeGenerator();
		//final TreeCodeGenerator cg = new TinyCodeGenerator();
//		final PipedOutputStream pos = new PipedOutputStream();
//		InputStream is = new PipedInputStream(pos);
		
//		File f = new File("TinyCode.java");
//		f.delete();
//		final FileOutputStream fos = new FileOutputStream(f);
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					cg.generateCode(fos, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//				} catch (NumberFormatException | IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//
//		System.out.println(f.getAbsolutePath());
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(is));
//				BufferedWriter bw = new Bu) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
//		}
	}
}
