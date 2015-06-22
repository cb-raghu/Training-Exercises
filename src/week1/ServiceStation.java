import java.util.*;
class Person
{
	String name;
	int age;
	String contactNo;	

	Person(String name,int age,String contactNo)
	{
		this.name = name;
		this.age = age;
		this.contactNo = contactNo;
	}
}

class Employee extends Person
{
	String empId;

	Employee(String name,int age,String contactNo, String empId)
	{
		super(name,age,contactNo);
		this.empId = empId;
	}
}

class Customer extends Person
{
	String customerId;
	Vechile vechile;
	Invoice invoice;
	Customer(String name,int age,String contactNo,String customerId,Vechile vechile)
	{
		super(name,age,contactNo);
		this.customerId = customerId;
		this.vechile = vechile;
	}

	public void generateInvoice(String ownerName,String empName)
	{
		invoice = new Invoice(ownerName,empName,vechile.vechiletype.getServiceCharge()); 
		System.out.println("ownerName : " + ownerName + "\t Employee name : " + empName + "\t serviceCharge : " + vechile.vechiletype.getServiceCharge());
	}

}

enum VechileType {	 		
	CAR(130),
	BIKE(60),
	BUS(250);

	public final float serviceCharge;
	
	VechileType(float serviceCharge){
		this.serviceCharge = serviceCharge;
	}

	public float getServiceCharge(){
		return serviceCharge;
	}
}

class Vechile
{
	String brand;
	String color;
	VechileType vechiletype;

	Vechile(String brand,String color,VechileType vechiletype)
	{
		this.brand =brand;
		this.color = color;
		this.vechiletype = vechiletype;
	}
	
}

class Invoice 
{
	String ownerName;
	String empName;
	float amount;

	Invoice(String ownerName,String empName,float amount)
	{
		this.ownerName = ownerName;
		this.empName = empName;
		this.amount = amount;
	}

}



class ServiceStation
{
	public static void main(String[] args)
	{
		for (VechileType type : VechileType.values() ) {
			System.out.println("Type : " + type + "\t Charges : " + type.getServiceCharge());

		}


		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("raghu",25,"9834343434","cust-1",new Vechile("Tata","red",VechileType.CAR)));
		customerList.add(new Customer("dinesh",30,"8755565673","cust-2",new Vechile("BMW","black",VechileType.BUS)));
		customerList.add(new Customer("priya",21,"9834343434","cust-3",new Vechile("KTM","red",VechileType.BIKE)));
		customerList.add(new Customer("roshan",19,"9433433434","cust-4",new Vechile("Honda","blue",VechileType.CAR)));

		for (Customer customer : customerList) {
			
			customer.generateInvoice(customer.name,"ddd");
		}
	}
}