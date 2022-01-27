// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
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