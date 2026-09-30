package moze_intel.projecte.utils;

import moze_intel.projecte.gameObjs.entity.EntityLootBall;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import java.util.List;
import moze_intel.projecte.utils.inventory.InventoryHelper;
import moze_intel.projecte.utils.nbt.NbtInventoryCodec;
import moze_intel.projecte.utils.oredict.OreDictHelper;
import moze_intel.projecte.utils.stack.StackHelper;

import com.google.common.collect.Lists;
import moze_intel.projecte.gameObjs.entity.EntityLootBall;
import net.minecraft.block.Block;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Helpers for Inventories, ItemStacks, Items, and the Ore Dictionary
 * Notice: Please try to keep methods tidy and alphabetically ordered. Thanks!
 */
public final class ItemHelper
{
	/**
	 * @return True if the only aspect these stacks differ by is stack size, false if item, meta, or nbt differ.
	 */

	public static boolean areItemStacksEqual(ItemStack stack1, ItemStack stack2)
	{
		return StackHelper.areItemStacksEqual(stack1, stack2);
	}

	public static boolean areItemStacksEqualIgnoreNBT(ItemStack stack1, ItemStack stack2)
	{
		return StackHelper.areItemStacksEqualIgnoreNBT(stack1, stack2);
	}

	public static boolean basicAreStacksEqual(ItemStack stack1, ItemStack stack2)
	{
		return StackHelper.basicAreStacksEqual(stack1, stack2);
	}

	public static void compactItemList(List<ItemStack> list)
	{
		StackHelper.compactItemList(list);
	}

	public static void compactItemListNoStacksize(List<ItemStack> list)
	{
		StackHelper.compactItemListNoStacksize(list);
	}

	public static boolean containsItemStack(List<ItemStack> list, ItemStack toSearch)
	{
		return InventoryHelper.containsItemStack(list, toSearch);
	}

	public static boolean containsItemStack(ItemStack[] stacks, ItemStack toSearch)
	{
		return InventoryHelper.containsItemStack(stacks, toSearch);
	}

	public static ItemStack[] copyIndexedNBTToArray(NBTTagList list, ItemStack[] dest)
	{
		return NbtInventoryCodec.copyIndexedNBTToArray(list, dest);
	}

	public static ItemStack getNormalizedStack(ItemStack stack)
	{
		return StackHelper.getNormalizedStack(stack);
	}

	public static List<ItemStack> getODItems(String oreName)
	{
		return OreDictHelper.getODItems(oreName);
	}

	public static String getOreDictionaryName(ItemStack stack)
	{
		return OreDictHelper.getOreDictionaryName(stack);
	}

	public static ItemStack getStackFromInv(IInventory inv, ItemStack stack)
	{
		return InventoryHelper.getStackFromInv(inv, stack);
	}

	public static ItemStack getStackFromInv(ItemStack[] inv, ItemStack stack)
	{
		return InventoryHelper.getStackFromInv(inv, stack);
	}

	public static ItemStack getStackFromString(String internal, int metaData)
	{
		return OreDictHelper.getStackFromString(internal, metaData);
	}

	public static boolean hasSpace(IInventory inv, ItemStack stack)
	{
		return InventoryHelper.hasSpace(inv, stack);
	}

	public static boolean hasSpace(ItemStack[] inv, ItemStack stack)
	{
		return InventoryHelper.hasSpace(inv, stack);
	}

	public static boolean invContainsItem(IInventory inv, ItemStack toSearch)
	{
		return InventoryHelper.invContainsItem(inv, toSearch);
	}

	public static boolean invContainsItem(ItemStack inv[], ItemStack toSearch)
	{
		return InventoryHelper.invContainsItem(inv, toSearch);
	}

	public static boolean invContainsItem(ItemStack inv[], Item toSearch)
	{
		return InventoryHelper.invContainsItem(inv, toSearch);
	}

	public static boolean isOre(Block block, int meta)
	{
		return OreDictHelper.isOre(block, meta);
	}

	public static ItemStack[] nbtToArray(NBTTagList list)
	{
		return NbtInventoryCodec.nbtToArray(list);
	}

	public static void pushLootBallInInv(IInventory inv, EntityLootBall ball)
	{
		InventoryHelper.pushLootBallInInv(inv, ball);
	}

	public static ItemStack pushStackInInv(IInventory inv, ItemStack stack)
	{
		return InventoryHelper.pushStackInInv(inv, stack);
	}

	public static ItemStack pushStackInInv(ItemStack[] inv, ItemStack stack)
	{
		return InventoryHelper.pushStackInInv(inv, stack);
	}

	public static NBTTagList toIndexedNBTList(ItemStack[] stacks)
	{
		return NbtInventoryCodec.toIndexedNBTList(stacks);
	}

	public static void trimItemList(List<ItemStack> list)
	{
		StackHelper.trimItemList(list);
	}
}
