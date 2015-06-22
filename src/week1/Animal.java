import java.util.*;
interface Fly
{
	public void canFly();
}

abstract class Mammal
{	
	int lifeTime;

	public Mammal(int lifeTime)
	{
		this.lifeTime = lifeTime; 
	}

	public abstract void movement();
}

abstract class Bird implements Fly
{	
	int lifeTime;
	public Bird(int lifeTime)
	{
		this.lifeTime = lifeTime; 			
	}

	public abstract void movement();
}

class Bat extends Mammal implements Fly
{
	String type;

	Bat(int lifeTime,String type)
	{
		super(lifeTime);	
		this.type = type;	
	}

	@Override
	public void movement()
	{
		System.out.println("Fly");
	}

	@Override
	public void canFly()
	{
		System.out.println("True");

	}

}

class Dog extends Mammal
{	
	String breed;

	Dog(int lifeTime,String breed)
	{
		super(lifeTime);	
		this.breed = breed;	
	}

	@Override
	public void movement()
	{
		System.out.println("walk");
	}
}


public class Animal
{
	public static void main(String[] args)
	{
		List<Mammal> mammalList = new ArrayList<Mammal>();
		mammalList.add(new Dog(10,"aaaa"));
		mammalList.add(new Bat(20,"aaaafdf"));
		mammalList.add(new Dog(15,"zzzzzz"));
		mammalList.add(new Bat(20,"yyyyyyy"));

		for (Mammal mammal : mammalList) {

			if(mammal instanceof Dog)
			{

				System.out.println("Breed : " + ((Dog)mammal).breed + "\nLife Time : " + mammal.lifeTime);
			}

			if(mammal instanceof Bat )
			{
				System.out.println("Type : " + ((Bat)mammal).type + "\nLife Time : " + mammal.lifeTime);
			}
		}

	}
}

