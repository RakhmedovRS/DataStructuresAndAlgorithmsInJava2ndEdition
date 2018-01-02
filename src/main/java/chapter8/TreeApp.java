package chapter8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author rassoll
 * @created 01.01.2018
 * @$Author$
 * @$Revision$
 */
public class TreeApp
{
	public static void main(String[] args) throws Exception
	{
		int value;
		Tree tree = new Tree();
		tree.insert(50, 1.5);
		tree.insert(25, 1.2);
		tree.insert(75, 1.7);
		tree.insert(12, 1.5);
		tree.insert(37, 1.2);
		tree.insert(43, 1.7);
		tree.insert(30, 1.5);
		tree.insert(33, 1.2);
		tree.insert(87, 1.7);
		tree.insert(93, 1.5);
		tree.insert(97, 1.5);

		while (true)
		{
			System.out.println("Enter first letter of show, insert, find, delete or traverse:");

			int choice = getChar();
			switch (choice)
			{
				case 's':
					tree.displayTree();
					break;
				case 'i':
					System.out.println("Enter value to insert: ");
					value = getInt();
					tree.insert(value, value + 0.9);
					break;
				case 'f':
					System.out.println("Enter valuer to find: ");
					Node found = tree.find(getInt());
					if (found != null)
					{
						System.out.print("Found: ");
						found.displayNode();
						System.out.println();
					}
				default:
					return;
			}
		}
	}

	private static int getInt() throws Exception
	{
		return Integer.parseInt(getString());
	}

	private static int getChar() throws Exception
	{
		String s = getString();
		return s.charAt(0);
	}

	private static String getString() throws Exception
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);
		return reader.readLine().toLowerCase();
	}
}
