import java.util.*;
public class Animals
{
	String family;
	String eatingHabit;
	String species;

	static int count = 0;
	Animals(String family,String eatingHabit,String species)
	{
		this.family = family;
		this.eatingHabit = eatingHabit;
		this.species = species;
		count++;
	}

	public static void main(String[] args)
	{
		char flag = 'y';
		String family,eatingHabit,species;
		Scanner scan = new Scanner(System.in);
		while(flag == 'y')
		{
			System.out.println("Enter animal family : ");
			family = scan.next();
			System.out.println("Enter animal eating habits : ");
			eatingHabit = scan.next();
			System.out.println("Enter animal species : ");
			species = scan.next();

			Animals obj = new Animals(family,eatingHabit,species);
			System.out.println("Animal object created !!");
			System.out.println(String.format("Family : %s\t EatingHabit : %s \t Species : %s",family,eatingHabit,species));
			System.out.println("Total objects created : " + count);
			System.out.println("Create another animal object (y/n) : ");
			flag = scan.next(".").charAt(0);
		}

	}
}