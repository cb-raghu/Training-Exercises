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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

    static final String[] HEADERS = {CUSTOMER_ID, SUBSCRIPTION_ID, INVOICE_ID, INVOICE_DATE, AMOUNT, FIRST_NAME, LAST_NAME, EMAIL};
    static final CSVFormat CSV_INPUT_FORMAT = CSVFormat.DEFAULT.withSkipHeaderRecord(true).withHeader(HEADERS);
    static final CSVFormat CSV_OUTPUT_FORMAT = CSVFormat.DEFAULT.withRecordSeparator("\n");
    Map<String, List<KeyValuePair>> config = new LinkedHashMap<String, List<KeyValuePair>>();

    public static void main(String[] args) throws IOException, JSONException,ParseException {
        Scanner scan = new Scanner(System.in);
        CsvFormater formater = new CsvFormater();

        System.out.println("Enter Configuration file(*.json) path : ");
        File conFile = new File(scan.nextLine());
        if (!conFile.isFile()) {
            System.exit(1);
        }
        formater.parseConfigFile(conFile);

        System.out.println("Enter CSV input file : ");
        File csvInputFile = new File(scan.nextLine());
        if (!csvInputFile.isFile()) {
            System.exit(1);
        }
        File csvOutputFile = new File(csvInputFile.getParent() + "/output.csv");
        formater.printToCsv(csvInputFile, csvOutputFile);
    }

    public void parseConfigFile(File conFile) throws IOException, JSONException {

        String jString = FileUtils.readFileToString(conFile);
        JSONObject jConfig = new JSONObject(jString);
        JSONArray jConfigRecords = jConfig.optJSONArray("configuration");
        JSONObject jConfigRecord;
        List<String> valueList;
        for (int i = 0; i < jConfigRecords.length(); i++) {
            jConfigRecord = jConfigRecords.optJSONObject(i);
            mapJSONConfig(jConfigRecord);
        }
    }

    void mapJSONConfig(JSONObject jConfigRecord) {
        Iterator it = jConfigRecord.keys();
        String colName = null;
        List<String> valueList;
        JSONArray jvalues;
        List<KeyValuePair> kvpList = new ArrayList<KeyValuePair>();
        while (it.hasNext()) {
            String key = it.next().toString();
            KeyValuePair kvp;
            switch (key) {
                case "Column Name":
                    colName = jConfigRecord.optString(key);
                    break;
                case "values":
                    jvalues = jConfigRecord.optJSONArray(key);
                    valueList = new ArrayList<String>();
                    for (int j = 0; j < jvalues.length(); j++) {
                        valueList.add(jvalues.optString(j));
                    }
                    kvp = new KeyValuePair(key, valueList);
                    kvpList.add(kvp);
                    break;
                case "Input Date format":
                    kvp = new KeyValuePair(key, jConfigRecord.optString(key));
                    kvpList.add(kvp);
                    break;
                case "Output Date format":
                    kvp = new KeyValuePair(key, jConfigRecord.optString(key));
                    kvpList.add(kvp);
                    break;    
                case "Divide By":
                    kvp = new KeyValuePair(key, jConfigRecord.optString(key));
                    kvpList.add(kvp);
                    break;
            }
        }

        config.put(colName, kvpList);
    }

    void printToCsv(File inputFile, File outputFile) throws IOException,ParseException {
        FileReader reader = new FileReader(inputFile);
        CSVParser parser = new CSVParser(reader, CSV_INPUT_FORMAT);
        FileWriter writer = new FileWriter(outputFile);
        CSVPrinter printer = new CSVPrinter(writer, CSV_OUTPUT_FORMAT);
        List<String> dataList;
        printer.printRecord(config.keySet());

        for (CSVRecord record : parser.getRecords()) {
            dataList = new ArrayList<String>();
            for (Map.Entry<String, List<KeyValuePair>> entry : config.entrySet()) {
                dataList.add(formatRecord(record, entry.getValue()));
            }
            printer.printRecord(dataList);
        }
        
        printer.flush();
        printer.close();

    }

    String formatRecord(CSVRecord record, List<KeyValuePair> kvlList) throws ParseException {
        String output = "";
        SimpleDateFormat inputDateFormat = null;
        KeyValuePair kvlPair = kvlList.get(0);
        for (KeyValuePair kvl : kvlList) {
            if (kvl.key.equals("values")) {
                List<String> colList = kvl.valueList;
                if (colList.size() == 1) {
                    output = record.get(colList.get(0));
                } else {
                    for (String col : colList) {
                        output += col + " : " + record.get(col) + ";";
                    }
                }
            } else if (kvl.key.equals("Divide By")) {
                output = String.valueOf(Double.parseDouble(output) / Double.parseDouble(kvl.value));
            }
            else if (kvl.key.equals("Input Date format"))
            {
                inputDateFormat = new SimpleDateFormat(kvl.value);
            }
            else if(kvl.key.equals("Output Date format"))
            {
                SimpleDateFormat outputDateFormat = new SimpleDateFormat(kvl.value);
                Date date =   inputDateFormat.parse(output);
                output =  outputDateFormat.format(date);
            }

        }

        return output;
    }
}

class KeyValuePair {

    String key;
    String value;
    List<String> valueList;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValuePair(String key, List<String> valueList) {
        this.key = key;
        this.valueList = valueList;
    }
}
