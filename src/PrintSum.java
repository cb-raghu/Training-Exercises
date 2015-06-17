import java.util.Scanner;
public class PrintSum
{
	public static void main(String[] args) 
	{
		int x,y,z;
		Scanner scan = new Scanner(System.in);
		x = scan.nextInt();
		y = scan.nextInt();
		String output = String.format("Sum  = %d", x + y);
		System.out.println(output);

	}
}