package chapter8;

/**
 * Класс представляющий узел дерева
 *
 * @author rassoll
 * @created 01.01.2018
 * @$Author$
 * @$Revision$
 */
class Node
{
	public int key;
	public int value;
	Node leftChild;
	Node rightChild;

	Node(int key, int value)
	{
		this.key = key;
		this.value = value;
	}

	void displayNode()
	{
		System.out.println(String.format("{%s,%s}", key, value));
	}
}