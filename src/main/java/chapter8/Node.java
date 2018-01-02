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
	public double value;
	public Node leftChild;
	public Node rightChild;

	Node(int key, double value)
	{
		this.key = key;
		this.value = value;
	}

	public void displayNode()
	{
		System.out.println(String.format("{%s,%s}", key, value));
	}

	public void displayStringNode()
	{
		System.out.println(String.format("{%s,%s}", (char)key, (char)value));
	}
}