import static org.junit.Assert.*; // imports junit assert library

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.*;

import org.junit.*;               // imports all junit library

public class MarkdownParseTest {  // file class
    @Test                         // indicated a test
    public void addition() {      // void method for Junit tester
        assertEquals(2, 1 + 1);   // checks if 2 is equal to 1+1
    }

    String theFile;

    public void setup(String fileName) throws Exception {
            Path file = Path.of(fileName);
            theFile = Files.readString(file);
    }

    @Test
    public void customTest1() throws Exception {
        // public static ArrayList<String> getLinks(String markdown) {
        setup("test-file.md");
        ArrayList<String> fileContent = MarkdownParse.getLinks(theFile);
        assertEquals(fileContent.toString(), "[https://something.com, some-page.html]");
    }

    @Test
    public void customTest2() throws Exception {
        // public static ArrayList<String> getLinks(String markdown) {
        setup("new-file.md");
        ArrayList<String> fileContent = MarkdownParse.getLinks(theFile);
        assertEquals(fileContent.toString(), "[https://something.com, some-page.html]");
    }

    @Test
    public void customTest3() throws Exception {
        // public static ArrayList<String> getLinks(String markdown) {
        setup("new-file2.md");
        ArrayList<String> fileContent = MarkdownParse.getLinks(theFile);
        assertEquals(fileContent.toString(), "[google.com]");
    }

    @Test
    public void customTest4() throws Exception {
        // public static ArrayList<String> getLinks(String markdown) {
        setup("new-file3.md");
        ArrayList<String> fileContent = MarkdownParse.getLinks(theFile);
        assertEquals(fileContent.toString(), "[# title\n\n[]link goes here]");
    }

    @Test
    public void customTest6() throws Exception {
        // public static ArrayList<String> getLinks(String markdown) {
        setup("test-file3.md");
        ArrayList<String> fileContent = MarkdownParse.getLinks(theFile);
        assertEquals(fileContent.toString(), "[]");
    }

    @Test
    public void customTest7() throws Exception {
        // public static ArrayList<String> getLinks(String markdown) {
        setup("test-file4.md");
        ArrayList<String> fileContent = MarkdownParse.getLinks(theFile);
        assertEquals(fileContent.toString(), "");
    }
    
    // -------------

    @Test
    public void snippetOneTest() throws IOException {
        String contents= Files.readString(Path.of("./Snippet1.md"));
        List<String> expect = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void snippetTwoTest() throws IOException {
        String contents= Files.readString(Path.of("./Snippet2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }


    @Test
    public void snippetThreeTest() throws IOException {
        String contents= Files.readString(Path.of("./Snippet3.md"));
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    } 
    
    // -------------
    
}
