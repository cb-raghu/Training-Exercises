class SmallestLargest
{
	public static void main(String[] args)
	{		
		if(args.length == 1)
		{			
			String[] strArray = args[0].split(",");
			int[] intArray = new int[strArray.length];
			int count = 0;
			for (String str : strArray) 
			{
				intArray[count++] = Integer.parseInt(str);
			}

			int small = intArray[0];
			int large = intArray[0];
			for (int i= 1 ; i < intArray.length; i++ ) 
			{
				if(intArray[i] < small)
				{
					small = intArray[i];
				}	

				if(intArray[i] > large)
				{
					large = intArray[i];
				}
			}

			System.out.println("Smallest No : " + small + "\nLargest No : " + large);


		}
		else
		{
			System.out.println("Invalid arguments");
		}
	}
}