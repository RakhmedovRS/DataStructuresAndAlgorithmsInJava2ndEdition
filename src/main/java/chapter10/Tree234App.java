package chapter10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static chapter10.BOrder.*;
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
		BTree bTree = new BTree(TREE_3_ORDER);

		bTree.insert(50);
		bTree.insert(40);
		bTree.insert(60);
		bTree.insert(30);
		bTree.insert(70);

		boolean run = true;
		while (run)
		{
			System.out.println("Enter first letter of ");
			System.out.println("show, insert, find or exit: ");

			char choice = getChar();
			switch (choice)
			{
				case 's':
					bTree.displayTree();
					break;
				case 'i':
					System.out.println("Enter value to insert: ");
					bTree.insert(getInt());
					break;
				case 'f':
					System.out.println("Enter value to find: ");
					value = getInt();
					if (bTree.find(value) != -1)
					{
						System.out.println("Found " + value);
					}
					else
					{
						System.out.println("Could not find " + value);
					}
					break;
				case 'e':
					run = false;
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
