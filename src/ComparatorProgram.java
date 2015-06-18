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

class AgeComparator implements Comparator<Employee>
{
	@Override
	public int compare(Employee emp1,Employee emp2)
	{		

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

class NameComparator implements Comparator<Employee>
{
	@Override
	public int compare(Employee emp1,Employee emp2)
	{
		return emp1.name.compareTo(emp2.name);
	}
}

class SalaryComparator implements Comparator<Employee>
{
	@Override
	public int compare(Employee emp1,Employee emp2)
	{		

		if(emp1.salary > emp2.salary)
		{
			return 1;
		}
		else if(emp1.salary < emp2.salary)
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
		Scanner scan = new Scanner(System.in);
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("raghu",25,1000));
		empList.add(new Employee("vignesh",20,700));
		empList.add(new Employee("dinesh",30,1200));
		empList.add(new Employee("mahesh",10,100));

		System.out.println("1) Sort by Name \n2)Sort by Age \n3)Sort by Salary");
		int choice = scan.nextInt();
		switch(choice)
		{
			case 1 : Collections.sort(empList,new NameComparator());
					 break;
			case 2 : Collections.sort(empList,new AgeComparator());
					 break;
			case 3 : Collections.sort(empList,new SalaryComparator());
					 break;		 		 
		}
		

		for (Employee emp : empList ) 
		{
			String output = String.format("Name : %s \n Age : %d \n Salary : %f",emp.name,emp.age,emp.salary);
			System.out.println(output);	
		}

	}
}