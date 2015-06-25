import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LineNumber {   

    public static void main(String[] args) throws IOException {
        args = new String[1];
        args[0] = "/Users/cb-raghu/work/Training-Exercises/class/week2/Dest/SampleWords.txt";
        if (args.length == 1) {
            File file = new File(args[0]);
            File outputFile = null;
            String sentence,query;
            int lineNumber;            
            FileWriter writer = null;
            LineNumberReader lnReader = null;
            Scanner scan = new Scanner(System.in);
            Pattern pattern = null;
            Matcher matcher = null;
            try {                   
                   if (file.isFile()&& file.canRead()) {
                       System.out.println("Enter the search term : ");
                       query = scan.nextLine();
                      pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
                   lnReader = new LineNumberReader(new FileReader(file));
                   outputFile = new File(file.getParent() + "/" + query + "_location.txt");

                   writer = new FileWriter(outputFile);
                   sentence = lnReader.readLine();
                   
                   while(sentence != null)
                   {
                      matcher = pattern.matcher(sentence);
                      if(matcher.find())
                      {
                        writer.append("\n" + lnReader.getLineNumber() + " : ");
                        writer.append(matcher.start() + "");
                      }
                      while(matcher.find())
                      {                      
                          writer.append("," + matcher.start());
                      }                      
                      
                      sentence = lnReader.readLine();                      
                      
                   }
                }
            } catch (Exception e) {
            }
            finally{
                if(scan != null)
                    scan.close();
                if(lnReader != null)
                    lnReader.close();
                if(writer != null)
                {
                    writer.flush();
                    writer.close();
                }
            }
        } else {
            System.out.print("Invalid Arguments");
        }
    }
}
