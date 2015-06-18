class Car
{
	int speed;
	double regularPrice;
	String color;

	public Car(int speed,double regularPrice, String color )
	{
		this.speed = speed;
		this.regularPrice = regularPrice;
		this.color = color;

	}

	public void toDisplay()
	{
		System.out.print("speed : " + speed + "\t regularPrice : " + regularPrice + "\t color : " + color);
	}

	public double getSalePrice()
	{
		return (regularPrice - ((10/(double)100)*regularPrice));
	}
}

class Truck extends Car
{
	int weight;

	public Truck(int speed,double regularPrice,String color,int weight)
	{
		super(speed,regularPrice,color);
		this.weight = weight;
	}
		

	public double getSalePrice()
	{
		
		if(weight >2000)
		{			
			return (regularPrice - ((10/(double)100)*regularPrice));
		}
		else
		{			
			return (regularPrice - ((20/(double)100)*regularPrice));
		}
	}
}

class Ford extends Car
{
	int year;
	int manufacturerDiscount;

	public Ford(int speed,double regularPrice,String color,int year,int manufacturerDiscount)
	{
		super(speed,regularPrice,color);
		this.year = year;
		this.manufacturerDiscount = manufacturerDiscount;
	}

	public double getSalePrice()
	{
		return (super.getSalePrice() - manufacturerDiscount);
	}
}

class Sedan extends Car
{
	int lenght;

	public Sedan(int speed,double regularPrice,String color,int lenght)
	{
		super(speed,regularPrice,color);
		this.lenght = lenght;
	}

	public double getSalePrice()
	{
		if(lenght > 20)
		{
			return (regularPrice - ((5/(double)100)*regularPrice));	
		}
		else
		{
			return (regularPrice - ((10/(double)100)*regularPrice));
		}
	}


}


class CarProgram
{
	public static void main(String[] args)
	{
		System.out.println("Truck Object");

		Truck truck = new Truck(600,123493,"red",30000);
		truck.toDisplay();		
		System.out.print("\tSale Price : " + truck.getSalePrice() + "\n");

		System.out.println("Ford Objects");
		Ford ford1 = new Ford(70,123493,"blue",1989,2000);
		ford1.toDisplay();		
		System.out.print("\tSale Price : " + ford1.getSalePrice() + "\n");

		Ford ford2 = new Ford(100,123493,"teal",2010,5000);
		ford2.toDisplay();		
		System.out.println("\tSale Price : " + ford2.getSalePrice() +  "\n");

		System.out.println("Sedan Objects");
		Sedan sedan = new Sedan(120,123493,"black",10);
		sedan.toDisplay();		
		System.out.println("\tSale Price : " + sedan.getSalePrice() + "\n");

	}
}