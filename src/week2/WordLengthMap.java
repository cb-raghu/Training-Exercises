import java.util.*;
class WordLengthMap
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String sentence;
		Map<Integer,Set<String>> wordMap = new HashMap<Integer,Set<String>>();
		System.out.println("Enter a sentence : ");
		sentence = scan.nextLine();
		Set<String> wordList;
		for (String word : sentence.split(" "))
		{
			int length = word.length();
			wordList = wordMap.get(length);
			
			if(wordList == null)
			{
				wordList = new HashSet<String>();
				wordMap.put(length,wordList);				 
			}			
			wordList.add(word);
			
		}

		for (Map.Entry<Integer,Set<String>>entry : wordMap.entrySet())
		{
			System.out.print(entry.getKey() + "  --->  ");
			Set<String> words = entry.getValue();
			for (String word : words)
			{
					System.out.print(word + " ");
			}	
			System.out.println();
		}

	}
}