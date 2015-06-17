import java.util.*;
class TaxCalculator
{
	

	public static void main(String[] args)
	{

		List<Tax> taxList = new ArrayList<Tax>();
		taxList.add(new Tax("Raghu",'m',120000));
		taxList.add(new Tax("Karthik",'m',500000));
		taxList.add(new Tax("priya",'f',30000));
		taxList.add(new Tax("mahesh",'m',300000));
		taxList.add(new Tax("Roopa",'f',200000));

		for (Tax tax : taxList) 
		{			
			tax.taxAmount = Tax.calculateTax(tax.gender,tax.income);	
		}

		System.out.println();


		for (Tax tax : taxList) 
		{			
			String output = String.format("Name : %s \nGender : %c \nIncome : %f \nTaxable Income : %.2f",tax.name,tax.gender,tax.income,tax.taxAmount);
			System.out.println(output);
		}


	}
}


class Tax
{
	String name;
	char gender;
	double income;
	double taxAmount;

	public Tax(String name,char gender,double income)
	{
		this.name = name;
		this.gender = gender;
		this.income = income;
		taxAmount = 0;
	}

	public static float getTaxPercent(char gender,double income)
	{

		if(income < 150000)
		{
			return 0;
		}
		else if(income >= 150001 && income <= 500000 && gender == 'm')
		{
			return 10;
		}
		else if(income >= 500000 && income <= 1000000 && gender == 'm')
		{
			return 20;
		}
		else if(income >= 150001 && income <= 500000 && gender == 'f')
		{
			return 7;
		}
		else if(income >= 500000 && income <= 1000000 && gender == 'f')
		{
			return 17;
		}
		else
		{
			return 30;
		}
	}


	public static double calculateTax(char gender,double income)
	{
		float taxPercent;
		double taxableIncome;

		taxPercent = getTaxPercent(gender,income);
		taxableIncome = ((taxPercent/(double)100)*income);

		return taxableIncome;
	}
}
