import java.util.*;	

public class Book
{
	private String name;
	private Double price;
	private int quatityInStock;	
	private List<Author> authors = new ArrayList<Author>();

	Book(String name, Author author, double price, int quatityInStock)
	{
		this.name = name;
		this.authors.add(author);
		this.authors.add(author);
		this.price = price;
		this.quatityInStock = quatityInStock;
	}

	Book(String name, List<Author> authors, double price, int quatityInStock)
	{
		this.name = name;		
		this.authors.addAll(authors);
		this.price = price;
		this.quatityInStock = quatityInStock;
	}

	public String getName()
	{
		return name;
	}

	public List<Author> getAuthors()
	{
		return authors;
	}


	public Double getPrice()
	{
		return price;
	}	

	public int getQuanity()
	{
		return quatityInStock;
	}	

	public void setQuanity(int quatityInStock)
	{
		this.quatityInStock = quatityInStock;
	}	

	public void setPrice(Double price)
	{
		this.price = price;
	}


	public void toDisplay()
	{
		System.out.print(name + " by ");
		for (Author author : authors)
		{
			System.out.println(author.getName() + " " + author.getGender() + " at " + author.getEmail());		
			
		}

		System.out.println("Price : " + price);
		System.out.println("No of books available : " + quatityInStock);
		System.out.println();

	}

	public void addAuthor()
	{	
		Scanner scan = new Scanner(System.in);	
		System.out.println("Enter Author Name");
		String name = scan.next();
		System.out.println("Enter Author Email");
		String email = scan.next();
		System.out.println("Enter Author Gender");
		char gender = scan.next().charAt(0);
		Author auth = new Author(name,email,gender);
		authors.add(auth);

	}

	public static List<Book> populateData()
	{
		List<Author> authorList1 = new ArrayList<Author>();
		authorList1.add(new Author("Raghu","raghu@gmail.com",'M'));
		authorList1.add(new Author("Yuvi","yuviu@gmail.com",'M'));

		List<Author> authorList2 = new ArrayList<Author>();
		authorList2.add(new Author("priya","priya@yahoo.com",'F'));
		authorList2.add(new Author("Dinesh","dinesh@hotmail.com",'M'));
		

		Book book1 = new Book("java",authorList1,130.56,30);
		Book book2 = new Book("javascript",authorList2,340.56,100);

		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);

		return bookList;

	}

	public static void main(String[] args)
	{	
		Scanner scan = new Scanner(System.in);
		List<Book> bookList = populateData();
		System.out.print("Displaying All book details : ");
		for (Book book : bookList ) {
			book.toDisplay();			
		}

		System.out.println("Add author to book : ");
		String bookName = scan.next();

		for(Book book : bookList)
		{
			if(book.name.equalsIgnoreCase(bookName))
			{
				book.addAuthor();
				System.out.println("Print Updated Details : ");
				book.toDisplay();
			}
		}



	}

}