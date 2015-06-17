import java.util.*;

class Employee
{
	String name;
	int age;
	float salary;

	Employee(String name,int age,float salary)
	{
		this.name =  name;
		this.age = age;
		this.salary = salary;
	}	
}

class AgeComparator implements Comparator
{
	public int compare(Object obj1,Object obj2)
	{
		Employee emp1 = (Employee) obj1;
		Employee emp2 = (Employee) obj2;

		if(emp1.age > emp2.age)
		{
			return 1;
		}
		else if(emp1.age < emp2.age)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}


class ComparatorProgram
{
	public static void main(String[] args)
	{
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("raghu",25,1000));
		empList.add(new Employee("vignesh",20,700));
		empList.add(new Employee("dinesh",30,1200));
		empList.add(new Employee("mahesh",10,100));
		
		Collections.sort(empList,new AgeComparator());

		for (Employee emp : empList ) 
		{
			String output = String.format("Name : %s \n Age : %d \n Salary : %f",emp.name,emp.age,emp.salary);
			System.out.println(output);	
		}

	}
}