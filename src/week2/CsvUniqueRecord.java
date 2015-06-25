import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
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
    
    static Set<Person> uniqueRecords = new HashSet<Person>();
    
    public static void main(String[] args) throws FileNotFoundException,IOException {
        if(args.length != 1)
        {
           System.exit(1);
        }
        File file = new File(args[0]);
        if(!file.isFile())
        {
            System.exit(1);
        }
        Person person;
        FileReader reader = new FileReader(file);
        CSVParser parser = new CSVParser(reader, INPUT_FORMAT);
        List<CSVRecord> records = parser.getRecords();
        for (CSVRecord record : records) {             
            uniqueRecords.add(new Person(record.get(NAME),Integer.parseInt(record.get(AGE)),record.get(ADDRESS)));
        }
        printUniqueRecords(file);
        
    }
    
    static void printUniqueRecords(File inputFile) throws IOException
    {
        String path = inputFile.getParent() + "UniqueRecords.txt";
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        CSVPrinter printer = new CSVPrinter(writer,OUTPUT_FORMAT );
        printer.printRecords(headers);
        for (Person person : uniqueRecords) {
            printer.printRecord(person);
        }
    }
}

class Person 
{
    String name;
    int age;
    String address;
    
    public Person(String name,int age,String address)
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
        Person person;
        if(!(_person instanceof  Person))
        {
            return false;
        }
        
        person = (Person)_person;        
        
        if(person.name.equals(name) && person.age == age && person.address.equals(address))
        {
            return  true;
        }
        else{
            return false;
        }
    }
    
}
