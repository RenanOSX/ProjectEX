package moze_intel.projecte.utils.inventory;

import com.google.common.collect.Lists;
import moze_intel.projecte.gameObjs.entity.EntityLootBall;
import moze_intel.projecte.utils.stack.StackHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import java.util.List;

public final class InventoryHelper
{
	public static boolean containsItemStack(List<ItemStack> list, ItemStack toSearch)
	{
		for (ItemStack stack : list) {
			if (stack == null) {
				continue;
			}

			if (stack.getItem().equals(toSearch.getItem())) {
				if (!stack.getHasSubtypes() || stack.getItemDamage() == toSearch.getItemDamage()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean containsItemStack(ItemStack[] stacks, ItemStack toSearch)
	{
		for (ItemStack stack : stacks)
		{
			if (stack == null)
			{
				continue;
			}

			if (stack.getItem() == toSearch.getItem())
			{
				if (!stack.getHasSubtypes() || stack.getItemDamage() == toSearch.getItemDamage())
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Copy an NBTTagList that has inventory indices into the appropriate positions of provided array.
	 */

	public static ItemStack getStackFromInv(IInventory inv, ItemStack stack)
	{
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack s = inv.getStackInSlot(i);

			if (s == null)
			{
				continue;
			}

			if (StackHelper.basicAreStacksEqual(stack, s))
			{
				return s;
			}
		}

		return null;
	}

	public static ItemStack getStackFromInv(ItemStack[] inv, ItemStack stack)
	{
		for (ItemStack s : inv)
		{
			if (s == null)
			{
				continue;
			}

			if (StackHelper.basicAreStacksEqual(stack, s))
			{
				return s;
			}
		}

		return null;
	}

	/**
	 *	@throws NullPointerException
	 */

	public static boolean hasSpace(IInventory inv, ItemStack stack)
	{
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack invStack = inv.getStackInSlot(i);

			if (invStack == null)
			{
				return true;
			}

			if (StackHelper.areItemStacksEqual(stack, invStack) && invStack.stackSize < invStack.getMaxStackSize())
			{
				return true;
			}
		}

		return false;
	}

	public static boolean hasSpace(ItemStack[] inv, ItemStack stack)
	{
		for (ItemStack invStack : inv)
		{
			if (invStack == null)
			{
				return true;
			}

			if (StackHelper.areItemStacksEqual(stack, invStack) && invStack.stackSize < invStack.getMaxStackSize())
			{
				return true;
			}
		}

		return false;
	}

	public static boolean invContainsItem(IInventory inv, ItemStack toSearch)
	{
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);

			if (stack != null && StackHelper.basicAreStacksEqual(stack, toSearch))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean invContainsItem(ItemStack inv[], ItemStack toSearch)
	{
		for (ItemStack stack : inv)
		{
			if (stack != null && StackHelper.basicAreStacksEqual(stack, toSearch))
			{
				return true;
			}
		}

		return false;
	}

	public static boolean invContainsItem(ItemStack inv[], Item toSearch)
	{
		for (ItemStack stack : inv)
		{
			if (stack != null && stack.getItem() == toSearch)
			{
				return true;
			}
		}
		return false;
	}

	public static void pushLootBallInInv(IInventory inv, EntityLootBall ball)
	{
		List<ItemStack> results = Lists.newArrayList();
		for (ItemStack s : ball.getItemList())
		{
			ItemStack result = pushStackInInv(inv, s);
			if (result != null)
			{
				results.add(result);
			}
		}
		ball.setItemList(results);
	}

	/**
	 *	Returns an itemstack if the stack passed could not entirely fit in the inventory, otherwise returns null.
	 */

	public static ItemStack pushStackInInv(IInventory inv, ItemStack stack)
	{
		int limit;

		if (inv instanceof InventoryPlayer)
		{
			limit = ((InventoryPlayer) inv).mainInventory.length;
		}
		else
		{
			limit = inv.getSizeInventory();
		}

		for (int i = 0; i < limit; i++)
		{
			ItemStack invStack = inv.getStackInSlot(i);

			if (invStack == null)
			{
				inv.setInventorySlotContents(i, stack);
				return null;
			}

			if (inv.isItemValidForSlot(i, stack)
				&& StackHelper.areItemStacksEqual(stack, invStack) && invStack.stackSize < invStack.getMaxStackSize())
			{
				int remaining = invStack.getMaxStackSize() - invStack.stackSize;

				if (remaining >= stack.stackSize)
				{
					invStack.stackSize += stack.stackSize;
					inv.setInventorySlotContents(i, invStack);
					return null;
				}

				invStack.stackSize += remaining;
				inv.setInventorySlotContents(i, invStack);
				stack.stackSize -= remaining;
			}
		}

		return stack.copy();
	}

	/**
	 *	Returns an itemstack if the stack passed could not entirely fit in the inventory, otherwise returns null.
	 */

	public static ItemStack pushStackInInv(ItemStack[] inv, ItemStack stack)
	{
		for (int i = 0; i < inv.length; i++)
		{
			ItemStack invStack = inv[i];

			if (invStack == null)
			{
				inv[i] = stack;
				return null;
			}

			if (StackHelper.areItemStacksEqual(stack, invStack) && invStack.stackSize < invStack.getMaxStackSize())
			{
				int remaining = invStack.getMaxStackSize() - invStack.stackSize;

				if (remaining >= stack.stackSize)
				{
					invStack.stackSize += stack.stackSize;
					inv[i] = invStack;
					return null;
				}

				invStack.stackSize += remaining;
				inv[i] = invStack;
				stack.stackSize -= remaining;
			}
		}

		return stack.copy();
	}

	/**
	 * Takes an array of ItemStacks and turns it into an NBTTaglist.
	 */
}
