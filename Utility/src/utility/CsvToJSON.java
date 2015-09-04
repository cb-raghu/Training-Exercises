/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cb-raghu
 */
public class CsvToJSON {

    static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("File Path : ");
            String dataPath = scanner.next();            
            dataPath = System.getProperty("user.home") + dataPath;
            File dataFile = new File(dataPath);
            if(!dataFile.isDirectory()){
                System.out.println("Invalid directory");
                return;
            }
            JSONObject jdata = getJson(dataFile);
            String outputPath = dataFile.getParent() + "/" + dataFile.getName() + ".json";
            File outputFile = new File( outputPath);            
            FileUtils.write(outputFile,jdata.toString());
            System.out.println("JSON file generated successfully - " + outputPath);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static JSONObject getJson(File dataFile) throws FileNotFoundException, IOException, JSONException {
        String[] extensions = {"csv"};        
        Collection<File> files = FileUtils.listFiles(dataFile, extensions, true);
        JSONObject jFileData = new JSONObject();
        
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }

            jFileData.put(FilenameUtils.removeExtension(file.getName()), parseCsvToJson(file));
        }

        return jFileData;
    }

    public static JSONArray parseCsvToJson(File file) throws FileNotFoundException, IOException, JSONException {
        CSVParser parser = new CSVParser(new FileReader(file), FORMAT);
        JSONArray jRecords = new JSONArray();
        JSONObject jRecord;

        Set<String> keys = parser.getHeaderMap().keySet();

        for (CSVRecord csvRecord : parser) {
            jRecord = new JSONObject();
            for (String key : keys) {
                jRecord.put(key.trim(), csvRecord.get(key).trim());
            }
            jRecords.put(jRecord);
        }

        return jRecords;
    }

}
