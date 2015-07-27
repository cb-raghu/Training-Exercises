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
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

class PhoneDirectoryCsv {

    public static Map<String, List<Person>> phoneDirectory = new HashMap<String, List<Person>>();
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String MOBILE = "mobile";
    public static final String WORK = "work";
    public static final String HOME = "home";
    public static final String[] headers = {NAME, ADDRESS, MOBILE, WORK, HOME};
    public static final CSVFormat format = CSVFormat.DEFAULT.withHeader(headers).withSkipHeaderRecord(true);
    //List<Phone> phoneList = new ArrayList<Phone>();

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        String data, path;
        File file;
        System.out.println("Enter the CSV file path(Absolute path) : ");
        file = new File(scan.next());
        if (!file.isFile()) {
            System.exit(1);
        }

        populateData(file);

        System.out.println("1) Search by name\n2)Partial search by name\n3)Search by phone number");
        int choice = scan.nextInt();

        if (choice == 1) {
            System.out.println("Enter a name to search : ");
            data = scan.next();
            nameSearch(data);
        } else if (choice == 2) {
            System.out.println("Enter a name to search : ");
            data = scan.next();
            partialNameSearch(data);
        } else {
            System.out.println("Enter a phone to search : ");
            data = scan.next();
            phoneNumberSearch(data);
        }

    }

    public static void phoneNumberSearch(String number) {
        String output;

        for (List<Person> personList : phoneDirectory.values()) {
            for (Person person : personList) {
                output = String.format("Name : %s \t Address : %s \nPhone List", person.name, person.address);

                for (Phone phone : person.phoneList) {
                    if (phone.mobile != null && phone.mobile.equals(number)) {
                        System.out.println(output);
                        System.out.println("Mobile : " + phone.mobile);
                    } else if (phone.work != null && phone.work.equals(number)) {
                        System.out.println(output);
                        System.out.println("Work : " + phone.work);
                    } else if (phone.home != null && phone.home.equals(number)) {
                        System.out.println(output);
                        System.out.println("Home : " + phone.home);
                    }
                }
            }

        }

    }

    public static void partialNameSearch(String name) {
        String output;

        for (List<Person> personList : phoneDirectory.values()) {
            for (Person person : personList) {                

                if (person.name.indexOf(name) != -1) {
                    
                    output = String.format("Name : %s \t Address : %s \nPhone List", person.name, person.address);
                    System.out.println(output);

                    for (Phone phone : person.phoneList) {
                        if (phone.mobile != null) {
                            System.out.println("Mobile : " + phone.mobile);
                        }
                        if (phone.work != null) {
                            System.out.println("Work : " + phone.work);
                        }
                        if (phone.home != null) {
                            System.out.println("Home : " + phone.home);
                        }
                    }
                }
            }
        }

    }   
   

    public static void nameSearch(String name) {
        List<Person> personList = phoneDirectory.get(name);
        String output;

        if (personList == null) {
            System.out.println("No entry found!!");
        } else {
            for (Person person : personList) {
                output = String.format("Name : %s \t Address : %s \nPhone List", person.name, person.address);
                System.out.println(output);
                for (Phone phone : person.phoneList) {
                    if (phone.mobile != null) {
                        System.out.println("Mobile : " + phone.mobile);
                    }
                    if (phone.work != null) {
                        System.out.println("Work : " + phone.work);
                    }
                    if (phone.home != null) {
                        System.out.println("Home : " + phone.home);
                    }
                }

            }
        }

    }

    public static void addPerson(Person person) {
        List<Person> personList = phoneDirectory.get(person.name);
        if (personList == null) {
            personList = new ArrayList<Person>();
            phoneDirectory.put(person.name, personList);
        }
        personList.add(person);

    }

    public static void populateData(File file) throws FileNotFoundException, IOException {
        Person person;
        Phone phone;

        String phoneStr;
        Reader reader = new FileReader(file);
        CSVParser parser = new CSVParser(reader, format);
        for (CSVRecord record : parser.getRecords()) {
            person = new Person(record.get(NAME), record.get(ADDRESS), parsePhoneNumber(record));
            addPerson(person);
        }
    }

    public static List<Phone> parsePhoneNumber(CSVRecord record) {
        List<Phone> phoneList = new ArrayList<Phone>();
        String phoneStr;
        Phone phone;
        phoneStr = record.get(MOBILE);
        if (!phoneStr.equals("")) {
            phone = new Phone();
            phone.addMobileNumber(phoneStr);
            phoneList.add(phone);
        }
        phoneStr = record.get(HOME);
        if (!phoneStr.equals("")) {
            phone = new Phone();
            phone.addHomeNumber(phoneStr);
            phoneList.add(phone);
        }
        phoneStr = record.get(WORK);
        if (!phoneStr.equals("")) {
            phone = new Phone();
            phone.addWorkNumber(phoneStr);
            phoneList.add(phone);
        }

        return phoneList;
    }

}

class Person {

    String name;
    String address;
    List<Phone> phoneList;

    public Person(String name, String address, List<Phone> phoneList) {
        this.name = name;
        this.address = address;
        this.phoneList = phoneList;
    }

}

class Phone {

    String mobile;
    String home;
    String work;

    public void addMobileNumber(String mobile) {
        this.mobile = mobile;
    }

    public void addHomeNumber(String home) {
        this.home = home;
    }

    public void addWorkNumber(String work) {
        this.work = work;
    }

}
