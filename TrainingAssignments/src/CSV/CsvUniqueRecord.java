/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CSV;

/**
 *
 * @author cb-raghu
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

class CsvUniqueRecord {    
    
    public static final String[] headers = {"name","age","address"};
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static  final CSVFormat INPUT_FORMAT = CSVFormat.DEFAULT.withHeader(headers).withSkipHeaderRecord(true);
    public static  final CSVFormat OUTPUT_FORMAT = CSVFormat.DEFAULT.withRecordSeparator("\n");
    
    static Set<Persons> uniqueRecords = new HashSet<Persons>();
    
    public static void main(String[] args) throws FileNotFoundException,IOException {
        Scanner scan = new Scanner(System.in);
        String path = scan.next();
        File file = new File(path);
        if(!file.isFile())
        {
            System.exit(1);
        }
        Persons person;
        FileReader reader = new FileReader(file);
        CSVParser parser = new CSVParser(reader, INPUT_FORMAT);
        List<CSVRecord> records = parser.getRecords();
        for (CSVRecord record : records) {             
            uniqueRecords.add(new Persons(record.get(NAME),Integer.parseInt(record.get(AGE)),record.get(ADDRESS)));
        }
        printUniqueRecords(file);
        
    }
    
    static void printUniqueRecords(File inputFile) throws IOException
    {
        String path = inputFile.getParent() + "/UniqueRecords.csv";
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        CSVPrinter printer = new CSVPrinter(writer,OUTPUT_FORMAT );
        printer.printRecord(headers);
        for (Persons person : uniqueRecords) {
            List<String> personDetails = new ArrayList<String>();
            personDetails.add((person.name));
            personDetails.add(String.valueOf(person.age));
            personDetails.add((person.address));
            printer.printRecord(personDetails);
        }
        printer.flush();
        printer.close();
    }
}

class Persons 
{
    String name;
    int age;
    String address;
    
    public Persons(String name,int age,String address)
    {
        this.name= name;
        this.age = age;
        this.address =address; 
    }
    
    @Override
    public int hashCode()
    {
        return ((this.age * 7) + (name.hashCode() * 11) + address.hashCode());
    }
    
    @Override
    public boolean equals(Object _person)
    {
        Persons person;
        if(!(_person instanceof  Persons))
        {
            return false;
        }
        
        person = (Persons)_person;        
        
        if(person.name.equals(name) && person.age == age && person.address.equals(address))
        {
            return  true;
        }
        else{
            return false;
        }
    }
    
}

