/* Program by Vytautë Lipeikaitë, 2019.09.26
 * This program is to check if a number is a magic or not.

A magic number is an integer in which permutations of the digits are successive multiples of the number
(number of digits and order does not change, but can start from different position).

The most widely known is 142857:
142857 × 1 = 142857
142857 × 2 = 285714
142857 × 3 = 428571
142857 × 4 = 571428
142857 × 5 = 714285
142857 × 6 = 857142

Input Data:
> Input a number: 142857
Expected Output
> It's magic!  */

import java.util.Scanner;
public class Magic_numbers
{
	public static void main(String[] args)
	{		  
			Scanner input = new Scanner(System.in);
	    	System.out.print("Enter an integer: ");
	    	int number = 0;
	    	
	    	// if input is correct
	    	if(input.hasNextInt()) 
	    	{
	    		number = input.nextInt();

				int [] Magic_multipliers = new int[10000];
				int magic_count;
		    	
				if (number < 0)
					magic_count = MagicCount(Math.abs(number), Magic_multipliers);			
				else
					magic_count = MagicCount(number, Magic_multipliers);
				
		    	PrintMagicNumbers(magic_count, number, Magic_multipliers);
	    	}
	    	else
	    	{
		    	System.out.print("Enter valid integer!");
	    	}
	}
	
	// counts the number of times the number proves to be magic
	// Magic_multipliers[] - array of multipliers who make number magic
	static int MagicCount(int number, int Magic_multipliers[])
	{
		int [] digits = new int[10000];		
		int digits_count = NumberToDigitsArray(digits, number);

		Magic_multipliers[0] = 1;
		int magic_count = 1; // number of times number proved magic
		if ( digits_count == 1)
		{
			return magic_count;
		}
		
		int multiplier = 2;

		while (true)
		{
			int temp_number = number * multiplier;
			if ( temp_number >=  (Math.pow(10, digits_count)))
			{
				break;
			}
			
			if (PermutationsCorrect(temp_number, digits, digits_count))
			{
				Magic_multipliers[magic_count] = multiplier;
				magic_count++;
			}
			
			multiplier++;
		}
		
		return magic_count;
	}
	
	// converts number to array of its digits
	static int NumberToDigitsArray(int [] digits, int number)
	{
		int digits_count = 0;
		String number_string = Integer.toString(number); 
		  
		// get all digits into array
		for ( int i = 0; i < number_string.length(); i++ )
		{
			char d = number_string.charAt(i);
			int d_int = Character.getNumericValue(d);  
			digits[i] = d_int;
			digits_count ++;
		}
		
		return digits_count;
	}
	
	// return true if number can be permutated successfully, false - if cannot
	static boolean PermutationsCorrect(int new_number, int [] digits, int digits_count)
	{
		int [] new_digits = new int[10000];
		int new_digits_count = NumberToDigitsArray(new_digits, new_number);
		
		if ( digits_count == new_digits_count )
		{
			for ( int i = 0; i < digits_count; i++ )
			{
				// find number which is equal in both numbers
				for ( int j = 0; j < new_digits_count; j++ )
				{
					if (digits[i] == new_digits[j])
					{
						int matches = 1;
						int d = i;
						int nd = j;
						// check if all numbers order matches
						while ( matches < digits_count)
						{
							d++;
							nd++;
							if ( d >= digits_count)
								d = 0;
							if ( nd >= new_digits_count)
								nd = 0;
							
							if (digits[d] == new_digits[nd])
							{
								matches++;
							}
							else 
								break;
						}
						
						if ( matches == digits_count )
						{
							return true;
						}
					}
				}
			}
		}
			
		return false;
	}
	
	// prints result to console
	static void PrintMagicNumbers(int magic_count, int number, int Magic_multipliers[])
	{
		if ( magic_count > 1 )
		{
			System.out.println(number + " is magic!");
		
			for ( int i = 0; i < magic_count; i++ )
			{
				System.out.println(number + " X " + Magic_multipliers[i] + " = " + number * Magic_multipliers[i]);
			}
		}
		else
		{
			System.out.println(number + " has no magic...");
		}
	}	
}