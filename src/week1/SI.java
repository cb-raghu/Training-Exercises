import java.util.Scanner;
class SI
{
	public static void main(String[] args)
	{
		float p,r,si;
		int n;

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter principal : ");
		p = scan.nextFloat();
		System.out.println("Enter no of years : ");
		n = scan.nextInt();
		System.out.println("Enter rate of intrest : ");
		r = scan.nextFloat();

		si =  (p * n * r)/100;

		System.out.println("SI : " + si);
		System.out.println("Total amount : " + (si + p));	

	}	
}