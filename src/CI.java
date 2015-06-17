import java.util.Scanner;
class CI
{
	public static void main(String[] args)
	{
		double p,r,ci,amount;
		int n,t;

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter principal : ");
		p = scan.nextFloat();
		System.out.println("Enter no of years : ");
		n = scan.nextInt();
		System.out.println("Enter rate of intrest : ");
		r = scan.nextFloat();
		System.out.println("No of times the intrest is compounded in a year : ");
		t = scan.nextInt();

		amount =  p * (Math.pow((1 + ((r/100)/n)),n*t));

		System.out.println("CI : " + (amount - p));
		System.out.println("Total amount : " + amount);	

	}	
}