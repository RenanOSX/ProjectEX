package moze_intel.projecte.utils.nbt;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public final class NbtInventoryCodec
{
	public static ItemStack[] copyIndexedNBTToArray(NBTTagList list, ItemStack[] dest)
	{
		for (int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound entry = list.getCompoundTagAt(i);
			int index = entry.getByte("index");
			if (index < 0 || index >= dest.length)
			{
				continue;
			}
			dest[index] = ItemStack.loadItemStackFromNBT(entry);
		}
		return dest;
	}

	/**
	 * Returns an ItemStack with stacksize 1.
	 */

	public static ItemStack[] nbtToArray(NBTTagList list)
	{
		ItemStack[] stacks = new ItemStack[list.tagCount()];
		for (int i = 0; i < list.tagCount(); i++)
		{
			stacks[i] = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(i));
		}
		return stacks;
	}

	public static NBTTagList toIndexedNBTList(ItemStack[] stacks)
	{
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i] != null)
			{
				NBTTagCompound entry = new NBTTagCompound();
				entry.setByte("index", ((byte) i));
				stacks[i].writeToNBT(entry);
				list.appendTag(entry);
			}
		}
		return list;
	}
}
