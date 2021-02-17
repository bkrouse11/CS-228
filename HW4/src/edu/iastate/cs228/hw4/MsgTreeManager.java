package edu.iastate.cs228.hw4;

import java.io.File;
import java.util.Scanner;

public class MsgTreeManager {
	
	public static void main(String args[]) {
		MsgTree msg;
		File file;
		String codes;
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Please enter file name to decode (must have .arch attached): ");
			try {
				String filename = scan.next();
				file = new File(filename);
				Scanner fileScanner = new Scanner(file);
				String treeContents = fileScanner.nextLine();
				codes = fileScanner.nextLine();
				
				if(fileScanner.hasNextLine()) {
					treeContents += "\n";
					treeContents += codes;
					codes = fileScanner.nextLine();
				}
				
				msg = new MsgTree(treeContents);
				fileScanner.close();
				break;
				
			} catch(Exception e){
				System.out.println("Not a valid .arch file");
			}
		}
		
		System.out.println("character   code" + "\n" + "----------------");
		MsgTree.printCodes(msg, codes);
		
		System.out.println("MESSAGE:");
		MsgTree.decode(msg, codes);
		
		scan.close();
	}
}