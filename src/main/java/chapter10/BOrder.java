package chapter10;

/**
 * Перечисление порядков B дерева
 *
 * @author rassoll
 * @created 31.01.2018
 * @$Author$
 * @$Revision$
 */
enum BOrder
{
	TREE_23(3),
	TREE_234(4);

	private final int order;

	BOrder(int order)
	{
		this.order = order;
	}

	public int getBOrder()
	{
		return order;
	}
}
