
public class SmallestPower {
	
	public static void main(String[] args)
	{
		int x,y,power = 1,temp=1;		
		if ( args.length == 2)
		{
			x = Integer.parseInt(args[0]);
			y= Integer.parseInt(args[1]);

			while(true)
			{
				temp = temp * x;
				if(temp > y )
				{
					break;
				}
				
				power++;				
			}

			String output = String.format("Smallest power of %d greater than %d is %d",x,y,power);
			System.out.println(output);
		}
		else
		{
			System.out.println("Invalid arguments");
		}	
		
	}
}