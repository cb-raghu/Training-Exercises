import java.util.*;

class PhoneDirectory
{
	public static Map<String,Person> phoneDirectory = HashMap<String,Person>();
	List<Phone> phoneList = new ArrayList<Phone>();

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		populateData();

		System.out.println("1) Search by name\nPartial search by name\nSearch by phone number");
		int choice = scan.nextInt();

		if(choice == 1)
		{
			nameSearch();
		}
		else if(choice == 2)
		{

		}
		else
		{

		}

	}

	public static void nameSearch()
	{

	}


	public static void addPerson(Person person)
	{
		if(phoneDirectory.get(name) == null)
		{
			personList = new ArrayList<Person>();
			phoneDirectory.put(name,person);
		}
		personList.add(person);

	}

	pubic static void populateData()
	{
		Phone phone;
		Person person;
		
		phoneList =  new ArrayList<Phone>(); 
		phone = Phone.MOBILE;
		phone.addNumber("12323224");		 
		phoneList.add(phone); 
		person = new Person("raghu","xxxxx",phoneList);            
		addPerson(person);

		phoneList =  new ArrayList<Phone>();  
		phone = Phone.MOBILE;
		phone.addNumber("884344");
		phoneList.add(phone);  
		phone = Phone.HOME;
		phone.addNumber("997766656");
		phoneList.add(phone); 
		person = new Person("raghu","xxxxx",phoneList);		            
		addPerson(person);


		phoneList =  new ArrayList<Phone>(); 
		phone = Phone.MOBILE;
		phone.addNumber("7777773333");		 
		phoneList.add(phone); 
		person =   new Person("raghu","ttttttt",phoneList);	           
		addPerson(person);
	}
	
}

class Person
{
	String name;
	String address;
	List<Phone> phoneList; 

	public Person(String name,String address,List<Phone> phoneList)
	{
		this.name= name;
		this.address = address;
		this.phoneList = phoneList;
	}

}

enum Phone{
	MOBILE,
	HOME,
	WORK;

	public String number;

	public void addNumber(String number)
	{
		this.number = number;
	}

	public String getNumber(String number)
	{
		return number;
	}
	
}

