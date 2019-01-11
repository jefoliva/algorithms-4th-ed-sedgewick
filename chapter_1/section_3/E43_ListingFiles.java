/******************************************************************************
 *  Compilation:  javac E43_ListingFiles 
 *  Execution:    java E43_ListingFiles  src
 *  Dependencies: StdOut Queue
 *  
 *  Description: 1.3.43 Listing files. A folder is a list of files and folders.
 *  Write a program that takes the name of a folder as a command-line argument 
 *  and prints out all of the files contained in that folder, with the contents
 *  of each folder recursively listed (indented) under that folder’s name. Hint : 
 *  Use a queue, and see java.io.File
 *
 *  Example execution:
 *  % java E43_ListingFiles src
 *   |   .gitignore
 *   |   output.txt
 *  |   README.md
 *   |   
 *   +---chapter_1
 *     |   
 *     +---section_1
 *     |   Exercise_1.java
 *     |   Exercise_10.txt
 *     |
 *     +---section_2
 *     |   E01.class
 *     |   E01.java
 *     |   
 *     \---section_3
 *     |   E01.java
 *     |   E02.txt
 *     |   E03.txt
 *     \    
 ******************************************************************************/
package chapter_1.section_3;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import java.io.File;

/**
 * @author jefoliva
 */

public class E43_ListingFiles {
    public static void main(String[] args) {
        if(args.length != 1) {
            StdOut.println("Usage E43_ListingFiles dirname");
            return;
        }

        File rootDir = new File(args[0]);
        if(rootDir.exists() && rootDir.isDirectory())
            listFiles(rootDir, 0);
        else 
            StdOut.println("File must be an existing direectory");
    }

    public static void listFiles(File rootDir, int indentLevel) {
        Queue<File> dirs  = new Queue<File>();
        Queue<File> files = new Queue<File>();

        // Fill dirs and files queues with the files in rootDir
        fillQueues(dirs, files, rootDir);      

        // Format pretty output for files
        String format = formatOutput(" |   ", indentLevel);

        // Display files in current directory first
        while(!files.isEmpty()) {
            StdOut.println(format + files.dequeue().getName());
        }
        StdOut.println(formatOutput(" \\   ", indentLevel)) ;

        // Format pretty output for directories
        format = formatOutput("", indentLevel);

        // Display subdirectories in current directory 
        while(!dirs.isEmpty()) {
            File currentDir = dirs.dequeue();
            
            // Different format for the last subdirectory
            String dirFormat = "";
            if(dirs.size() >= 1)
                dirFormat = "+---";
            else
                dirFormat = "\\---";

            StdOut.println(format + dirFormat + currentDir.getName());

            // call itself with each directory
            listFiles(currentDir, indentLevel+1);
        }
    }

    /**
     * Function used to format ouput
     * @param  symbol string used to format ouput
     * @param  indentLevel number of indetation levels to format output accordingly
     * @return a formatted string
     */
    private static String formatOutput(String symbol, int indentLevel) {
        String formatStr = " ";
        for(int i = 0; i < indentLevel; i++) 
            formatStr = "\t";
        formatStr += symbol;
        return formatStr;
    }

    private static void fillQueues(Queue<File> dirs, Queue<File> files, File file) {
        File[] listFiles  = file.listFiles();
        for(File f : listFiles) {
            if(f.isFile())
                files.enqueue(f);
            else if(f.isDirectory() && !f.isHidden())
                dirs.enqueue(f);
        }
    }
}