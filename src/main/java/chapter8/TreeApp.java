package chapter8;

import static base.util.Util.getChar;
import static base.util.Util.getInt;

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
		tree.insert(50, 15);
		tree.insert(25, 12);
		tree.insert(75, 17);
		tree.insert(12, 15);
		tree.insert(37, 12);
		tree.insert(43, 17);
		tree.insert(30, 15);
		tree.insert(33, 12);
		tree.insert(87, 17);
		tree.insert(93, 15);
		tree.insert(97, 15);

		boolean run = true;
		while (run)
		{
			System.out.println("Enter first letter of show, insert, find, delete or traverse:");

			int choice = getChar(System.in);
			switch (choice)
			{
				case 's':
					System.out.println(tree.displayTree());
					break;
				case 'i':
					System.out.println("Enter value to insert: ");
					value = getInt(System.in);
					tree.insert(value, value + 9);
					break;
				case 'f':
					System.out.println("Enter valuer to find: ");
					Node found = tree.find(getInt(System.in));
					if (found != null)
					{
						System.out.print("Found: ");
						found.displayNode();
						System.out.println();
					}
					break;
				case 'e':
					run = false;
					break;
				default:
					return;
			}
		}
	}
}
