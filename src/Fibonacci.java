public class Fibonacci{
	
	public static void main(String[] args)
	{
		int range,prev,bprev,temp,count;
		if(args.length == 1)
		{
			bprev = 0;
			prev = 1;
			temp = count = 0;
			range = Integer.parseInt(args[0]);			

			while(count < range )
			{
				if(count <= 1)
				{
					temp = count; 
				}
				else
				{
					temp = bprev + prev;					
					bprev = prev;
					prev = temp;
				
				}

				System.out.print(temp + " ");
				count++;
				
			}


		}
		else
		{
			System.out.println("Invalid arguments");
		}
	}
}