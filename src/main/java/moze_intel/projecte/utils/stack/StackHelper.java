package moze_intel.projecte.utils.stack;

import moze_intel.projecte.utils.Comparators;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class StackHelper
{
	public static boolean areItemStacksEqual(ItemStack stack1, ItemStack stack2)
	{
		return ItemStack.areItemStacksEqual(getNormalizedStack(stack1), getNormalizedStack(stack2));
	}

	public static boolean areItemStacksEqualIgnoreNBT(ItemStack stack1, ItemStack stack2)
	{
		if (stack1.getItem() != stack2.getItem())
		{
			return false;
		}


		if (stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE)
		{
			return true;
		}

		return stack1.getItemDamage() == stack2.getItemDamage();
	}

	public static boolean basicAreStacksEqual(ItemStack stack1, ItemStack stack2)
	{
		return (stack1.getItem() == stack2.getItem()) && (stack1.getItemDamage() == stack2.getItemDamage());
	}

	public static void compactItemList(List<ItemStack> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			ItemStack s = list.get(i);
			for (int j = i + 1; j < list.size(); j++)
			{
				ItemStack s1 = list.get(j);
				if (areItemStacksEqual(s, s1))
				{
					if (s.stackSize + s1.stackSize <= s.getMaxStackSize())
					{
						s.stackSize += s1.stackSize;
						s1.stackSize = 0;
					}
					else
					{
						s1.stackSize = (s1.stackSize + s.stackSize) - s.getMaxStackSize();
						s.stackSize = s.getMaxStackSize();
					}
				}
			}
		}

		Collections.sort(list, Comparators.ITEMSTACK_ASCENDING);
		trimItemList(list);
	}

	/**
	 * Compacts and sorts list of items, without regard for stack sizes
	 */

	public static void compactItemListNoStacksize(List<ItemStack> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			ItemStack s = list.get(i);
			for (int j = i + 1; j < list.size(); j++)
			{
				ItemStack s1 = list.get(j);
				if (areItemStacksEqual(s, s1))
				{
					s.stackSize += s1.stackSize;
					s1.stackSize = 0;
				}
			}
		}

		Collections.sort(list, Comparators.ITEMSTACK_ASCENDING);
		trimItemList(list);
	}

	public static ItemStack getNormalizedStack(ItemStack stack)
	{
		ItemStack result = stack.copy();
		result.stackSize = 1;
		return result;
	}

	/**
	 * Get a List of itemstacks from an OD name.<br>
	 * It also makes sure that no items with damage 32767 are included, to prevent errors.
	 */

	public static void trimItemList(List<ItemStack> list)
	{
		Iterator<ItemStack> iter = list.iterator();
		while (iter.hasNext())
		{
			ItemStack s = iter.next();
			if (s.stackSize <= 0)
			{
				iter.remove();
			}
		}
	}
}
