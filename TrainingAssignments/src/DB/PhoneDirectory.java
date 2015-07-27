/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author cb-raghu
 */
public class PhoneDirectory {

    static List<DB.Person> customerList = new ArrayList<DB.Person>();
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String MOBILE = "mobile";
    public static final String WORK = "work";
    public static final String HOME = "home";
    public static final String[] headers = {NAME, ADDRESS, MOBILE, WORK, HOME};
    public static final CSVFormat format = CSVFormat.DEFAULT.withHeader(headers).withSkipHeaderRecord(true);

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        //parseCSV();
        //populateDB();
        System.out.println("1) Search by name\n2)Partial search by name\n3)Search by phone number\n4)Add new Customer\n5)Update Name");
        int choice = scan.nextInt();

        if (choice == 1) {
            System.out.println("Enter a name to search(Strict) : ");
            nameSearch(scan.next());
        } else if (choice == 2) {
            System.out.println("Enter a name to search(partial) : ");
            partialNameSearch(scan.next());
        } else if (choice ==3) {
            System.out.println("Enter a phone to search : ");
            phoneNumberSearch(scan.next());
        }
        else if(choice == 4){
            String name,address,work,home,mobile;
            System.out.print("Enter Customer \nName : ");
            name = scan.next();
            System.out.print("Address : ");
            address = scan.next();
            System.out.print("Mobile : ");
            mobile = scan.next();
            System.out.print("Work : ");
            work = scan.next();
            System.out.print("Home : ");
            home = scan.next();
            addNewCustomer(name,address,mobile,work,home);
        }
         else if(choice == 5){
            String old_name,new_name;
            System.out.print("Enetr existing customer name : ");
            old_name = scan.next();
            System.out.print("Enetr new name : ");
            new_name = scan.next();
            updateCustomerName(old_name,new_name);
        }
    }
    
    public static void addNewCustomer(String name,String address,String mobile,String work,String home) throws SQLException,ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "insert into CustomerDetail(name,address,mobile,work,home) values (?,?,?,?,?)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PhoneDirectory", "root", "");
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, name);
            ps.setString(2, address);
            if(mobile.equals("n")){
                ps.setNull(3, Types.VARCHAR);
            }
            else{
                ps.setString(3, mobile);
            }
            if(work.equals("n")){
                ps.setNull(4, Types.VARCHAR);
            }
            else{
                ps.setString(4, work);
            }
            if(home.equals("n")){
                ps.setNull(5, Types.VARCHAR);
            }
            else{
                ps.setString(5, home);
            }                     
            if( ps.executeUpdate() == 1 ){
                System.out.println("Customer added successfully.");
            }
            else{
                System.out.println("Error in adding customer.");
            }                
            
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static void updateCustomerName(String old_name,String new_name) throws SQLException,ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "update CustomerDetail set name = ? where name = ? ";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PhoneDirectory", "root", "");
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, new_name);
            ps.setString(2, old_name);                     
            if( ps.executeUpdate() == 1 ){
                System.out.println("Customer updates successfully.");
            }
            else{
                System.out.println("Error in updating customer.");
            }                
            
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void nameSearch(String query) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "select name,address,mobile,work,home from  CustomerDetail where name like ?";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PhoneDirectory", "root", "");
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, query);
            rs = ps.executeQuery();
            display(rs);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void partialNameSearch(String query) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "select name,address,mobile,work,home from  CustomerDetail where name like ?";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PhoneDirectory", "root", "");
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, "%" + query + "%");
            rs = ps.executeQuery();
            display(rs);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void phoneNumberSearch(String query) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "select name,address,mobile,work,home from  CustomerDetail where mobile = ? or home = ? or work = ?";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PhoneDirectory", "root", "");
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setString(3, query);

            rs = ps.executeQuery();

            display(rs);

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void display(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("name : " + rs.getString(1));
            System.out.println("address : " + rs.getString(2));
            if (!rs.getString(3).equals("")) {
                System.out.println("mobile : " + rs.getString(3));
            }
            if (!rs.getString(4).equals("")) {
                System.out.println("work : " + rs.getString(4));
            }
            if (!rs.getString(5).equals("")) {
                System.out.println("home : " + rs.getString(5));
            }
            System.out.println("----------------------------------");
        }
    }

    public static void parseCSV() throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in);
        DB.Person person;
        CSVParser parser = null;
        Reader reader = null;
        System.out.println("Enter CSV the file path : ");
        File file = new File(scan.nextLine());
        try {
            if (!file.isFile()) {
                System.exit(1);
            }

            reader = new FileReader(file);
            parser = new CSVParser(reader, format);
            for (CSVRecord record : parser.getRecords()) {
                person = new DB.Person(record.get(NAME), record.get(ADDRESS), record.get(MOBILE), record.get(HOME), record.get(WORK));
                customerList.add(person);
            }
        } finally {
            if (parser != null) {
                parser.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void populateDB() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "insert into CustomerDetail(name,address,mobile,work,home) values(?,?,?,?,?)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PhoneDirectory", "root", "");
            ps = con.prepareStatement(sqlQuery);

            for (Person person : customerList) {
                ps.setString(1, person.name);
                ps.setString(2, person.address);
                ps.setString(3, person.mobile);
                ps.setString(4, person.home);
                ps.setString(5, person.work);
                ps.addBatch();
            }

            ps.executeBatch();

        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

}

class Person {

    String name;
    String address;
    String mobile;
    String home;
    String work;

    public Person(String name, String address, String mobile, String home, String work) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
    }

}
