import java.util.Random;

class Mobile
{
	String name;
	int remainingCharge;

	public void setRemainingCharge()
	{
		Random randomGenerator = new Random();
		remainingCharge = randomGenerator.nextInt(100);

	}
	public void setName()
	{
		name =  "Mobile";
	}

	public static void main(String[] args)
	{
		Mobile nokia = new Mobile(){

			public void setRemainingCharge()
			{
				Random randomGenerator = new Random();
				remainingCharge =  randomGenerator.nextInt(100);
				System.out.println("Remaining Charge : " + remainingCharge);
			}
			public void setName()
			{
				name = "nokia";
				System.out.println("Name: " + name);
			}
		};


		Mobile iPhone = new Mobile(){

			public void setRemainingCharge()
			{
				Random randomGenerator = new Random();
				remainingCharge =  randomGenerator.nextInt(100);
				System.out.println("Remaining Charge : " + remainingCharge);
			}
			public void setName()
			{
				name = "iPhone";
				System.out.println("Name: " + name);
			}
		};

		nokia.setName();
		nokia.setRemainingCharge();

		iPhone.setName();
		iPhone.setRemainingCharge();

	}
}