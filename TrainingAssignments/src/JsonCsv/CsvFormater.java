/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JsonCsv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cb-raghu
 */
public class CsvFormater {
    static final String CUSTOMER_ID = "Customer Id";
    static final String SUBSCRIPTION_ID = "Subscription Id";
    static final String INVOICE_ID = "Invoice Id";
    static final String INVOICE_DATE = "Invoice Date";
    static final String AMOUNT = "Amount";
    static final String FIRST_NAME = "First Name";
    static final String LAST_NAME = "Last Name";
    static final String EMAIL = "Email";
    
    static final String[] HEADERS = {CUSTOMER_ID,SUBSCRIPTION_ID,INVOICE_ID,INVOICE_DATE,AMOUNT,FIRST_NAME,LAST_NAME,EMAIL};
    static final CSVFormat CSV_INPUT_FORMAT = CSVFormat.DEFAULT.withSkipHeaderRecord(true).withHeader(HEADERS);
    static final CSVFormat CSV_OUTPUT_FORMAT = CSVFormat.DEFAULT.withRecordSeparator("\n");
    Map<String,List<String>> config = new LinkedHashMap<String, List<String>>();
    public static void main(String[] args) throws IOException, JSONException {
        Scanner scan = new Scanner(System.in);
        CsvFormater formater = new CsvFormater();       
        
        System.out.println("Enter Configuration file(*.json) path : ");
        File conFile = new File(scan.nextLine());        
        if(!conFile.isFile())
            System.exit(1);
        formater.parseConfigFile(conFile);
        
        System.out.println("Enter CSV input file : ");
        File csvInputFile = new File(scan.nextLine());        
        if(!csvInputFile.isFile())
            System.exit(1);        
        File csvOutputFile  = new File(csvInputFile.getParent() + "/output.csv");
        formater.printToCsv(csvInputFile,csvOutputFile);
    }
    
    public void parseConfigFile(File conFile) throws IOException, JSONException{
    
        String jString =   FileUtils.readFileToString(conFile);
        JSONObject jConfig = new JSONObject(jString);
        JSONArray jConfigRecords = jConfig.optJSONArray("configuration"); 
        JSONObject jConfigRecord;
        List<String> valueList;
        for (int i = 0; i < jConfigRecords.length(); i++) {
            valueList = new ArrayList<String>();
            jConfigRecord = jConfigRecords.optJSONObject(i);
            JSONArray jvalues = jConfigRecord.optJSONArray("values");
            for (int j = 0; j < jvalues.length(); j++) {
                valueList.add(jvalues.optString(j));
            }
            config.put(jConfigRecord.optString("Column Name"),valueList);            
        }        
    }
    
    void printToCsv(File inputFile,File outputFile) throws IOException
    {
        FileReader reader = new FileReader(inputFile);
        CSVParser parser = new CSVParser(reader, CSV_INPUT_FORMAT);
        FileWriter writer = new FileWriter(outputFile);
        CSVPrinter printer = new CSVPrinter(writer, CSV_OUTPUT_FORMAT );   
        List<String> dataList;
        printer.printRecord(config.keySet());
        
        for (CSVRecord record : parser.getRecords()) {
            dataList = new ArrayList<String>();
            for (Map.Entry<String,List<String>> entry : config.entrySet()) {
                List<String> columnList =  entry.getValue();
                dataList.add(formatOutput(columnList, record));
            }
            printer.printRecord(dataList);
        }
        printer.flush();
        printer.close();
        
    }
    
    String formatOutput(List<String> columnList,CSVRecord record)
    {
        String formatedData = "";
        if(columnList.size() > 1)
        {
            for (String column : columnList) {
                formatedData += column + " : "  + record.get(column) + ";";
            }
        }
        else if(columnList.size() == 1)
            formatedData = record.get(columnList.get(0));
        
        return formatedData;
    }
    
    void mapRecords(CSVRecord record)
    {
        
    }
}

class CustomConfig {
    List<String> Mapping;
}

class KeyValuePair
{
    String key;
    String value;
    
    public KeyValuePair(String key,String value)
    {
        this.key = key; 
        this.value = value;
    }
}
