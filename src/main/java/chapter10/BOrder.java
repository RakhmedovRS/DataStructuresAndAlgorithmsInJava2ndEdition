package chapter10;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

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
	TREE_3_ORDER(3),
	TREE_4_ORDER(4),
	TREE_5_ORDER(5),
	TREE_6_ORDER(6),
	TREE_7_ORDER(7),
	TREE_8_ORDER(8),
	TREE_9_ORDER(9),
	TREE_10_ORDER(10);

	private final Integer order;

	static final Map<Integer, BOrder> map =
		stream(BOrder.values()).collect(toMap(bOrder -> bOrder.order, bOrder -> bOrder));

	BOrder(int order)
	{
		this.order = order;
	}

	public int getBOrder()
	{
		return order;
	}

	/**
	 * Получение порядка B дерева из цифрового значения
	 *
	 * @param order цифровое значение порядка
	 * @return порядок B дерева
	 */
	public static BOrder valueOf(int order)
	{
		return map.get(order);
	}

	/**
	 * @return следующий за текущим порядок
	 */
	public BOrder nextOrder()
	{
		return BOrder.valueOf(order + 1);
	}
}
