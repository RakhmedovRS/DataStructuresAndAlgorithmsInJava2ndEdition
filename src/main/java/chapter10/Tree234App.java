package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rassoll
 * @created 28.01.2018
 * @$Author$
 * @$Revision$
 */
public class Tree234App
{
	public static void main(String[] args) throws IOException
	{
		long value;
		Tree234 tree234 = new Tree234();

		tree234.insert(50);
		tree234.insert(40);
		tree234.insert(60);
		tree234.insert(30);
		tree234.insert(70);

		while (true)
		{
			System.out.println("Enter first letter of ");
			System.out.println("show, insert, or find: ");

			char choice = getChar();
			switch (choice)
			{
				case 's':
					tree234.displayTree();
					break;
				case 'i':
					System.out.println("Enter value to insert: ");
					tree234.insert(getInt());
					break;
				case 'f':
					System.out.println("Enter value to find: ");
					value = getInt();
					if (tree234.find(value) != -1)
					{
						System.out.println("Found " + value);
					}
					else
					{
						System.out.println("Could not find " + value);
					}
					break;
				default:
					System.out.println("Invalid entry\n");
			}
		}
	}

	private static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}

	private static char getChar() throws IOException
	{
		return getString().charAt(0);
	}

	private static int getInt() throws IOException
	{
		return Integer.parseInt(getString());
	}
}
