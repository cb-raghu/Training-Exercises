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
	Person(String name,int age,String contactNo,String customerId)
	{
		super(name,age,contactNo);
		this.customerId = customerId;
	}

	public void generateInvoice(String ownerName,String empName,float amount)
	{
		invoice = new Invoice(ownerName,empName,amount); 

	}
}

Enum VechileType {	 		
	CAR(130),
	BIKE(60),
	BUS(250)

	public final float serviceCharge;
	
	VechileType(float serviceCharge){
		serviceCharge = serviceCharge;
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
	}
}