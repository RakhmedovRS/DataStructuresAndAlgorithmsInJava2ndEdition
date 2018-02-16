package chapter10;

import java.io.IOException;

import static base.util.Util.getChar;
import static base.util.Util.getInt;
import static chapter10.Order.TREE_4_ORDER;

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
		BTree bTree = new BTree(TREE_4_ORDER);

		for (int i = 10; i < 200; i += 10)
		{
			bTree.insert(i);
		}

		bTree.insert(131);
		bTree.insert(132);
		bTree.insert(98);
		bTree.insert(99);
		bTree.insert(92);

		boolean run = true;
		while (run)
		{
			System.out.println("Enter first letter of ");
			System.out.println("show, insert, find or exit: ");

			char choice = getChar(System.in);
			switch (choice)
			{
				case 'i':
					System.out.println("Enter value to insert: ");
					bTree.insert(getInt(System.in));
					break;
				case 's':
					bTree.displayTree();
					break;
				case 'f':
					System.out.println("Enter value to find: ");
					value = getInt(System.in);
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
}
