// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
	
	public static ArrayList<String> getLinks(String markdown) {
		ArrayList<String> toReturn = new ArrayList<>();
		ArrayList<Integer> openBraketIndexes = new ArrayList<>();
		ArrayList<Integer> openParenIndexes = new ArrayList<>();
		ArrayList<Integer> closeBracketIndexes = new ArrayList<>();
		ArrayList<Integer> closeParenIndexes = new ArrayList<>();
		//make and arraylist of all of the indexes of the occurances of the 
		//different [, ], (, and )

		//for the [
		int currentIndex = 0;
		while(currentIndex < markdown.lastIndexOf("[")) {
		    int openBracketIndex = markdown.indexOf("[", currentIndex);
		    openBraketIndexes.add(openBracketIndex);
		    currentIndex = openBracketIndex + 1;
		}
		//for ]
		currentIndex = 0;
		while(currentIndex < markdown.lastIndexOf("]")) {
		    int closeBracketIndex = markdown.indexOf("]", currentIndex);
		    closeBracketIndexes.add(closeBracketIndex);
		    currentIndex = closeBracketIndex + 1;
		}
		//for (
		currentIndex = 0;
		while(currentIndex < markdown.lastIndexOf("(")) {
		    int openParenIndex = markdown.indexOf("(", currentIndex);
		    openParenIndexes.add(openParenIndex);
		    currentIndex = openParenIndex + 1;
		}
		//for )
		currentIndex = 0;
		while(currentIndex < markdown.lastIndexOf(")")) {
		    int closeParenIndex = markdown.indexOf(")", currentIndex);
		    closeParenIndexes.add(closeParenIndex);
		    currentIndex = closeParenIndex + 1;
		}

		for(int i = 0; i < openParenIndexes.size(); i++){
		    if(markdown.charAt(openParenIndexes.get(i) - 1) != ']'){
			openParenIndexes.remove(i);
			i--;
		    }
		}

		/**
		 * basically get an Integer Array of all of the indexes of [, ],(, and )
		 * to then compare the contents between each bracket and each parenthesis
		 * 
		 * Note:ignore check previous index if index == 0. if it's in the [ list then
		 *      skip that index and go to the next in the list elsewise remove the index
		 *      from the list
		 * 
		 * 1.   Remove all indexes of [ where the previous character is ! to remove
		 *      the image case. ()
		 * 2.   check all closed brackets to see if the next character is either (
		 *      or [ due to how markdown reads links as [text](link) or
		 *      [text][reference]
		 * 3.   if it follows the [text][reference] type, save the references into a
		 *      ArrayList<String> 
		 *              note: do the loops above to check if there's any "]: " to 
		 *                    see if it uses references and to mark where references could be
		 * 4.   create data class indexPair(int indexOpen, int indexClosed) and add all the
		 *      indexes of [ and ] that aren't disqualified previously
		 * 5.   disqualify pairs based on whether they contain
		 */

		for(int openParen:openParenIndexes){
		    toReturn.add(markdown.substring(openParen+1, markdown.indexOf(")",openParen)));
		}
		return toReturn;
    	}
	
		
	/*	
    
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )

        boolean missingParen = false;

        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1){
                break;
            }

            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket == -1){
                break;
            }

            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen == -1){
                closeParen = markdown.length() - 1;
                missingParen = true;
            }

            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;

            if(missingParen){
                break;
            }
        }

        return toReturn;
    }
    
    */
	
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
	
	
	
}



/*
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        String[] lines = markdown.split(System.getProperty("line.separator"));
        for (int i=0; i < lines.length; i++) {
            String thisLine = lines[i];
            int openParen = thisLine.lastIndexOf("(");
            for (int j = lines[i].length(); j > -1; j--) {
                if (j == openParen) {
                    toReturn.add(lines[i].substring(j+1, lines[i].length()-1));
                }
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
*/
