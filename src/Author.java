import java.util.*;
class Author
{
	private String name;
	private String email;
	private char gender;

	Author(String name,String email,char gender)
	{
		this.name = name;
		this. email = email;
		this.gender = gender;
	}

	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}

	public char getGender()
	{
		return gender;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void toDisplay()
	{
		System.out.println(name + "," + gender + " at " + email);
	}

	public static  void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String email;
		Author auth = new Author("raghu","raghu@gmail.com",'M');
		auth.toDisplay();
		System.out.println("Enter new email address : ");
		email = scan.next();
		auth.setEmail(email);
		auth.toDisplay();
	}

}

