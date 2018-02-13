package chapter11;

import base.HashTable;
import base.LinkItem;

import java.io.IOException;

import static util.Util.getChar;
import static util.Util.getInt;

/**
 * @author rassoll
 * @created 13.02.2018
 * @$Author$
 * @$Revision$
 */
public class HashChainApp
{
	public static void main(String[] args) throws IOException
	{
		LinkItem aItem;
		int aKey;
		int size;
		int n;
		int keysPerCell;

		System.out.println("Enter size of hash table: ");

		size = getInt(System.in);
		System.out.println("Enter initial number of items: ");
		n = getInt(System.in);
		keysPerCell = 10;

		HashTable<LinkItem> hashTable = new HashChain(size);

		for (int i = 0; i < n; i++)
		{
			aKey = (int) (Math.random() * keysPerCell * size);
			hashTable.insert(new Link(aKey));
		}

		boolean run = true;
		while (run)
		{
			System.out.println("Enter first letter of ");
			System.out.println("show, insert, find, delete or exit:");
			char choice = getChar(System.in);

			switch (choice)
			{
				case 's':
					System.out.println(hashTable.getDisplayData());
					break;
				case 'i':
					System.out.println("Insert key value to insert: ");
					aKey = getInt(System.in);
					hashTable.insert(new Link(aKey));
					break;
				case 'd':
					System.out.println("Enter key value to delete: ");
					aKey = getInt(System.in);
					hashTable.delete(aKey);
					break;
				case 'f':
					System.out.println("Enter key value to find: ");
					aKey = getInt(System.in);
					aItem = hashTable.find(aKey);
					if (aItem != null)
					{
						System.out.println("Found " + aKey);
					}
					else
					{
						System.out.println("Could not find " + aKey);
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
