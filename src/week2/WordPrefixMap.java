import java.util.*;
class WordPrefixMap
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String sentence;
		Map<String,Set<String>> prefixMap = new TreeMap<String,Set<String>>();
		System.out.println("Enter a sentence");
		sentence = scan.nextLine();
		String prefix;
		Set<String> wordList;
		for(String word : sentence.split(" "))
		{
			if(word.length() >= 3)
			{
				prefix = word.substring(0,3);
				wordList =  prefixMap.get(prefix);
				if(wordList == null)
				{
					wordList  =new HashSet<String>();
					prefixMap.put(prefix,wordList);
				}

				wordList.add(word);
			}

		}

		for(Map.Entry<String,Set<String>> entry : prefixMap.entrySet())
		{
			System.out.print(entry.getKey() + " ");
			for(String word : entry.getValue())
			{
				System.out.print(word + " ");
			}

			System.out.println();
		}
	}
}