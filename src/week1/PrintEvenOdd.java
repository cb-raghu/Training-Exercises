
public class PrintEvenOdd {
	
	public static void main(String[] args)
	{
		int sum;			
		if ( args.length == 1)
		{		
			sum = 0;	
			char[] chars = args[0].toCharArray();
			for(char ch : chars )
			{
				sum += ch;	
			}

			String output = String.format("'%s' string is %s ",args[0],(sum % 2 == 0 ? "even" : "odd"));
			System.out.println(output);
		}
		else
		{

		}
	}
}