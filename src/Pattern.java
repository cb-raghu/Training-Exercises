public class Pattern {
	
	public static void main(String[] args)
	{
		int size = 0,k;		
		if ( args.length == 1)
		{
			size= Integer.parseInt(args[0]);
			for(int i = 0 ; i < size ; i++ )
			{
				k=0;
				for (int j= size -1; j > i ; j-- ) {
					System.out.print(" ");
				}
				
				for (; k <= i; k++ ) 
				{
					System.out.print(k+1);
				}

				for (int l = k; k > 1	  ; k-- ) 
				{
					System.out.print(k-1);
				}								
									
				System.out.println();
			}
		}
		else
		{
			System.out.println("Invalid arguments");
		}
	}
}