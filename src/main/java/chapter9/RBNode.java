package chapter9;

/**
 * Класс описывающий сущность узла в красно-черном дереве
 *
 * @author rassoll
 * @created 15.01.2018
 * @$Author$
 * @$Revision$
 */
class RBNode
{
	int key;

	RBNode leftRBChild;
	RBNode rightRBChild;

	private boolean isBlack;

	RBNode(int key)
	{
		this(key, false);
	}

	RBNode(int key, boolean isBlack)
	{
		this.key = key;
		this.isBlack = isBlack;
	}

	/**
	 * @return признак того, что узел черный
	 */
	boolean isBlack()
	{
		return isBlack;
	}

	/**
	 * Изменить цвет узла
	 */
	void switchRBNodeColor()
	{
		isBlack = !isBlack;
	}

	/**
	 * Изменить цвет узла цвет узла и двух потомков
	 * у корневого узла цвет не изменяется
	 *
	 * @param rbRoot ссылка на корневой узел дерева
	 */
	void switchRBNodeAndDescendantsColor(RBNode rbRoot)
	{
		if (rbRoot != this)
		{
			switchRBNodeColor();
		}
		leftRBChild.switchRBNodeColor();
		rightRBChild.switchRBNodeColor();
	}

	/**
	 * @return признак того, что узел черный и имеет двух красных потомков
	 */
	boolean isBlackNodeWithTwoRedDescendants()
	{
		return (isBlack()
			&& (leftRBChild != null && !leftRBChild.isBlack())
			&& (rightRBChild != null && !rightRBChild.isBlack()));
	}

	/**
	 * @return признак нарушения 3-го правила красно черного дерева {@link RBTree}
	 */
	boolean isViolationThirdRule()
	{
		return (!isBlack()
			&& ((leftRBChild != null && !leftRBChild.isBlack())
			|| (rightRBChild != null && !rightRBChild.isBlack())));
	}

	/**
	 * @return данные о ноде красно-черного дерева для печати
	 */
	String getRBNodePrintData()
	{
		if (this.isBlack())
		{
			return String.format("[%s]", this.key);
		}
		else
		{
			return String.format("%s", this.key);
		}
	}
}
