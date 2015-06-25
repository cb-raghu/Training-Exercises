/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JSON;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cb-raghu
 */
public class StudentTeacher {
    
    public static void main(String[] args) throws IOException,JSONException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter json fle path(Absolute path) : ");
        File file = new File(scan.next());
        if(!file.isFile())
            System.exit(1);
        String jsonStr = FileUtils.readFileToString(file);
        JSONObject jsonRoot = new JSONObject(jsonStr);
        parseJSON(jsonRoot);
    }
    
    public static void parseJSON(JSONObject jsonRoot) throws JSONException
    {
        JSONObject jStudent =    jsonRoot.optJSONObject("Student");
        JSONObject jTeacher = jsonRoot.optJSONObject("Teacher");
        Student student  = parseStudent(jStudent);
        Teacher teacher = parseTeacher(jTeacher);
        printStudent(student);
        printTeacher(teacher);
    }
    
    public static void printStudent(Student student)
    {
        String output = String.format("STUDENT \nName : %s \nId : %s \nStd : %s \nDOJ : %s \nMarkList : ", student.name,student.Id,student.std,student.dateOfJoining);
        System.out.println(output);
        for (Mark mark : student.marksList) {
            System.out.println(" " + mark.subject + " : " + mark.mark);
        }
    }
    
    public static void printTeacher(Teacher teacher)
    {
        String output = String.format("TEACHER \nName : %s \nId : %s \nSalary : %s \nDOJ : %s \nClassList : ", teacher.name,teacher.Id,teacher.salary,teacher.dateOfJoining);
        System.out.println(output);
        for (String classe : teacher.classes) {
            System.out.println(" " + classe);
        }
    }
    
    public  static Student parseStudent(JSONObject jStudent) throws JSONException
    {
        String id = jStudent.optString("ID");
        String name = jStudent.optString("Name");
        String std = jStudent.optString("Std");
        String doj= jStudent.optString("Date Of Joining");
        JSONArray jMarks = jStudent.optJSONArray("Marks");
        List<Mark> markList = new ArrayList<Mark>();
        for (int i = 0; i < jMarks.length(); i++) {
            JSONObject mark = jMarks.getJSONObject(i);
            markList.add(new Mark(mark.optString("Subject"),mark.optInt("Mark")));
        }
         return new Student(id,name,std,doj,markList);
    }
    
    public  static Teacher parseTeacher(JSONObject jTeacher) throws JSONException
    {
        String id = jTeacher.optString("ID");
        String name = jTeacher.optString("Name");
        double salary = jTeacher.optDouble("Salary");
        String doj= jTeacher.optString("Date Of Joining");
        JSONArray jClasses = jTeacher.optJSONArray("Classes Taking Care Of");
        List<String> classes = new ArrayList<String>();
        for (int i = 0; i < jClasses.length(); i++) {
            classes.add(jClasses.optString(i));
            
        }
         return new Teacher(id, name, salary, doj, classes);
    }
}

class Student{
    
    String Id;
    String name;
    String std;
    String dateOfJoining;
    List<Mark> marksList;
    
    public Student(String Id, String name,String std ,String dateOfjoining,List<Mark> markList)
    {
        this.Id = Id; 
        this.name = name; 
        this.std = std;
        this.dateOfJoining = dateOfjoining;
        this.marksList = markList; 
    }
}

class Teacher{
    
    String Id;
    String name;
    double salary;
    String dateOfJoining;
    List<String> classes; 
    
    public Teacher(String Id, String name,double salary ,String dateOfjoining,List<String> classes)
    {
        this.Id = Id; 
        this.name = name; 
        this.salary = salary;
        this.dateOfJoining = dateOfjoining;
        this.classes = classes; 
    }
}

class Mark
{
    int mark;
    String subject;
    
    public Mark(String subject,int mark)
    {
        this.mark = mark;
        this.subject = subject;
    }
}
