package com.revature.eval.java.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string)
	{
		char[] reversed = new char[string.length()];

		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++)
		{
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase)
	{
		//split the string based on the char '-' to separate any hyphenated words
		String[] sep = phrase.split("-");
		String rej = "";

		//recombine and re-split on ' ' to get all the individual words in an array
		for(int i = 0; i < sep.length; i++)
			rej += sep[i] + " ";
		sep = rej.split(" ");

		//create char array to hold the first letter of every word
		//then loop through the words, converting to uppercase, and add to char array
		char[] preAcr = new char[sep.length];
		for(int i = 0; i < sep.length; i++)
			preAcr[i] = Character.toUpperCase(sep[i].charAt(0));

		return new String(preAcr);
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral()
		{
			if(sideOne == sideTwo && sideOne == sideThree)
				return true;
			return false;
		}

		public boolean isIsosceles()
		{
			if(sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree)
				return true;
			return false;
		}

		public boolean isScalene()
		{
			if(sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree)
				return false;
			return true;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string)
	{
		//first convert the whole string to lower case to have only one range of chars to check
		String lowerstring = string.toLowerCase();

		//creates string of the alphebet and array of the scores to map the letters to scores
		String letters = "abcdefghijklmnopqrstuvwxyz";
		int[] points = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

		//loops through the input string and matches each letter to its corresponding score
		int score = 0;
		for(int i = 0; i < lowerstring.length(); i++)
		{
			for(int j = 0; j < letters.length(); j++)
			{
				if(lowerstring.charAt(i) == letters.charAt(j))
					score += points[j];
			}

		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string)
	{
		//creates a string of number digits to check each char in the input against to filter out
		//any non digit characters.
		String numbers = "1234567890";
		ArrayList<Character> cleaned = new ArrayList<>();

		//filter exploits the return of the indexOf method to determine if a string contains a single char
		for(int i = 0; i < string.length(); i++)
		{
			if(numbers.indexOf(string.charAt(i)) != -1)
				cleaned.add(string.charAt(i));
		}

		//checks for any illegal arguments such as phone number too large
		if(cleaned.size() > 11 || cleaned.size() < 10)
			throw new IllegalArgumentException();
		if(cleaned.size() == 11 && cleaned.get(0) != 1)
			throw new IllegalArgumentException();
		if(cleaned.size() > 10 && cleaned.get(0) == '1')
			cleaned.remove(0);

		//converts arraylist of chars to array for string conversion on return
		char[] c = new char[10];
		for(int i = 0; i < 10; i++)
			c[i] = cleaned.get(i);
		return new String(c);
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string)
	{
		//splits the input strings into separate words and creates map to hold word counts
		String[] split = string.split(" |,|\n");
		Map<String, Integer> count = new HashMap<>();

		//loops through the words and either increments the value if the key exists or
		//adds a new key with value 1 to count all the words
		for(int i = 0; i < split.length; i++)
		{
			if(count.containsKey(split[i]))
			{
				int num = count.get(split[i]);
				count.put(split[i], ++num);
			}
			else if(split[i].length() != 0)
				count.put(split[i], 1);
		}
		return count;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>>
	{
		private List<T> sortedList;

		public int indexOf(T t)
		{
			int start = 0;
			int end = sortedList.size()-1;
			while(start <= end)
			{
				int mid = start + (end-start)/2;
				T value = sortedList.get(mid);
				if(t.compareTo(value) == 0)
					return mid;
				else if(t.compareTo(value) > 0) // t greater than mid
					start = mid+1;
				else if(t.compareTo(value) < 0) // t less than mid
					end = mid;
			}
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string)
	{
		String vowels = "aeiou";
		String[] words = string.split(" ");
		StringBuilder sentance = new StringBuilder();

		for(int i = 0; i < words.length; i++)
		{
			StringBuilder pig = new StringBuilder(words[i]);
			while (vowels.indexOf(pig.charAt(0)) == -1)
			{
				char first = pig.charAt(0);
				pig.deleteCharAt(0);
				pig.append(first);
			}
			if(pig.charAt(pig.length()-1) == 'q' && pig.charAt(0) == 'u')
			{
				char first = pig.charAt(0);
				pig.deleteCharAt(0);
				pig.append(first);
			}
			pig.append("ay ");
			sentance.append(pig);
		}
		sentance.deleteCharAt(sentance.length()-1);
		return sentance.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input)
	{
		Integer temp = new Integer(input);
		char[] cd = temp.toString().toCharArray();
		int size = cd.length;
		int sum = 0;

		for(int i = 0; i < cd.length; i++)
			sum += Math.pow(Character.getNumericValue(cd[i]), size);

		if(sum == input)
			return true;
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l)
	{
		ArrayList<Long> primes = new ArrayList<>();
		primes.add(2L);
		primes.add(3L);
		primes.add(5L);
		primes.add(7L);
		primes.add(11L);
		long count = 12;
		while(count <= 500)
		{
			boolean isprime = true;
			for(int i = 0; i < primes.size(); i++)
			{
				if(primes.get(i) < count && count%primes.get(i) == 0)
					isprime = false;
			}
			if(isprime)
				primes.add(count);
			count++;
		}

		ArrayList<Long> factors = new ArrayList<>();
		long current = l;
		int primeindex = 0;
		while(primeindex < primes.size() && l >= primes.get(primeindex))
		{
			if(current%primes.get(primeindex) == 0)
			{
				factors.add(primes.get(primeindex));
				current /= primes.get(primeindex);
			}
			else
				primeindex++;
		}
		return factors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string)
		{
			char[] letters = string.toCharArray(); //65-90 caps || 97-122 lower
			for(int i = 0; i < letters.length; i++)
			{
				int charnum = (int)letters[i];
				if(charnum >= 65 && charnum <= 90) //caps
				{
					charnum += key;
					if(charnum > 90)
						charnum = 65 + (charnum-91);
					letters[i] = (char)charnum;
				}
				else if(charnum >= 97 && charnum <= 122)// lower
				{
					charnum += key;
					if(charnum > 122)
						charnum = 97 + (charnum-123);
					letters[i] = (char)charnum;
				}
			}
			return new String(letters);
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// TODO Write an implementation for this method declaration
		if(i == 0)
			throw new IllegalArgumentException();
		int count = 2;
		ArrayList<Integer> primes = new ArrayList<>();
		if(i == 1)
			return 2;
		while(primes.size() < i)
		{
			boolean isprime = true;
			for(int c = 0; c < primes.size(); c++)
			{
				if(c < primes.size() && count%primes.get(c) == 0)
					isprime = false;
			}
			if(isprime)
				primes.add(count);
			count++;
		}
		return primes.get(primes.size()-1);
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		static String norm = "abcdefghijklmnopqrstuvwxyz123456789";
		static String back = "zyxwvutsrqponmlkjihgfedcba123456789";

		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			HashMap<Character, Character> cipher = new HashMap<>();
			for(int i = 0; i < norm.length(); i++)
				cipher.put(norm.charAt(i), back.charAt(i));

			String[] split = string.toLowerCase().split(" ");
			String comp = "";
			for(int i = 0; i < split.length; i++)
				comp += split[i];

			StringBuilder cleaned = new StringBuilder(comp);
			for(int i = 0; i < cleaned.length(); i++)
			{
				int cint = (int)cleaned.charAt(i);
				if((cint >= 97 && cint <=122) || (cint >= 48 && cint <= 57))
				{}
				else
					cleaned.deleteCharAt(i);
			}
			comp = cleaned.toString();

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < comp.length(); i++)
			{
				char current = comp.charAt(i);
				int cint = (int)current;

				{
					sb.append(cipher.get(current));
					if ((i + 1) % 5 == 0)
						sb.append(" ");
				}
			}
			if(sb.charAt(sb.length()-1) == ' ')
				sb.deleteCharAt(sb.length()-1);
			return sb.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			HashMap<Character, Character> decode = new HashMap<>();
			for(int i = 0; i < back.length(); i++)
				decode.put(back.charAt(i), norm.charAt(i));

			String[] split = string.split(" ");
			String comp = "";
			for(int i = 0; i < split.length; i++)
				comp += split[i];

			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < comp.length(); i++)
				sb.append(decode.get(comp.charAt(i)));
			return sb.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		String[] split = string.split("-");
		String comp = "";
		for(int i = 0; i < split.length; i++)
			comp += split[i];
		char[] chars = comp.toCharArray();

		ArrayList<Integer> numbers = new ArrayList<>();
		for(int i = 0; i < chars.length-1; i++)
		{
			try
			{
				int value = Character.getNumericValue(chars[i]);
				if(value < 10 && chars[i] != 'A')
					numbers.add(value);
			}
			catch (Exception e){return false;}
		}
		if(chars[chars.length-1] == 'X')
			numbers.add(10);
		else
		{
			try
			{
				int value = Character.getNumericValue(chars[chars.length -1 ]);
				if(value < 10 && chars[chars.length-1] != 'A')
					numbers.add(value);
			}
			catch (Exception e){return false;}
		}

		if(numbers.size() == 10)
		{
			int sum = 0;
			for(int i = 0,  j=10; i < numbers.size(); i++, j--)
				sum += numbers.get(i)*j;
			if(sum%11 == 0)
				return true;
		}
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// TODO Write an implementation for this method declaration
		String lower = string.toLowerCase();
		char[] letters = lower.toCharArray();
		ArrayList<Character> found = new ArrayList<>();

		for(int i = 0; i < letters.length; i++)
		{
			if(letters[i] != ' ' && !found.contains(letters[i]))
				found.add(letters[i]);
		}
		if(found.size() == 26)
			return true;
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		int year = given.get(ChronoField.YEAR);
		int month = given.get(ChronoField.MONTH_OF_YEAR);
		int day = given.get(ChronoField.DAY_OF_MONTH);
		int hour = 0;
		int min = 0;
		int sec = 0;
		if(given instanceof LocalDateTime)
		{
			hour = given.get(ChronoField.HOUR_OF_DAY);
			min = given.get(ChronoField.MINUTE_OF_HOUR);
			sec = given.get(ChronoField.SECOND_OF_MINUTE);
		}
		return LocalDateTime.of(year, month, day, hour, min, sec).plus(1000000000, ChronoUnit.SECONDS);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		ArrayList<Integer> numbers = new ArrayList<>();
		for(int j = 0; j < set.length; j++)
		{
			int temp = set[j];
			while(temp < i)
			{
				if(!numbers.contains(temp))
					numbers.add(temp);
				temp += set[j];
			}
		}
		int sum = 0;
		for(int j = 0; j < numbers.size(); j++)
			sum += numbers.get(j);
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		StringBuilder sb = new StringBuilder(string);
		for(int i = 0; i < sb.length(); i++)
		{
			if(sb.charAt(i) == ' ')
				sb.deleteCharAt(i);
		}
		char[] chars = sb.toString().toCharArray();
		int[] numbers = new int[chars.length];
		for(int i = 0; i < chars.length; i++)
			numbers[i] = Character.getNumericValue(chars[i]);
		for(int i = numbers.length-2; i >= 0; i -= 2)
		{
			numbers[i] *= 2;
			if(numbers[i] > 9)
				numbers[i] -= 9;
		}
		int sum = 0;
		for(int i = 0; i < numbers.length; i++)
			sum += numbers[i];
		if(sum % 10 == 0)
			return true;
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		StringBuilder sb = new StringBuilder(string);
		if(sb.charAt(sb.length()-1) == '?')
			sb.deleteCharAt(sb.length()-1);
		String newstring = sb.toString();
		String[] words = newstring.split(" ");
		ArrayList<Integer> numbers = new ArrayList<>();
		for(int i = 0; i < words.length; i++)
		{
			try
			{
				numbers.add(Integer.parseInt(words[i]));
			}
			catch(NumberFormatException e){};
		}
		String operator = words[3];
		if(operator.equals("plus"))
			return numbers.get(0) + numbers.get(1);
		else if(operator.equals("minus"))
			return numbers.get(0) - numbers.get(1);
		else if(operator.equals("multiplied"))
			return numbers.get(0) * numbers.get(1);
		else if(operator.equals("divided"))
			return numbers.get(0) / numbers.get(1);
		return 0;
	}

}
