package moze_intel.projecte.playerData;

import com.google.common.collect.Maps;
import moze_intel.projecte.gameObjs.container.AlchBagContainer;
import moze_intel.projecte.gameObjs.items.AlchemicalBag;
import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.network.PacketHandler;
import moze_intel.projecte.network.s2c.SyncBagDataPKT;
import moze_intel.projecte.utils.ItemHelper;
import moze_intel.projecte.utils.PELogger;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Map;
import java.util.Map.Entry;

public final class AlchemicalBags 
{
	public static ItemStack[] get(EntityPlayer player, byte color)
	{
		return AlchBagProps.getDataFor(player).getInv(color);
	}

	public static void set(EntityPlayer player, byte color, ItemStack[] inv)
	{
		AlchBagProps.getDataFor(player).setInv(color, inv);
	}

	public static void syncFull(EntityPlayer player)
	{
		PacketHandler.sendTo(new SyncBagDataPKT(AlchBagProps.getDataFor(player).saveForPacket()), (EntityPlayerMP) player);
		PELogger.logDebug("** SENT FULL BAG DATA **");
	}

	public static void syncPartial(EntityPlayer player, int color)
	{
		PacketHandler.sendTo(new SyncBagDataPKT(AlchBagProps.getDataFor(player).saveForPartialPacket(color)), (EntityPlayerMP) player);
		PELogger.logDebug("** SENT PARTIAL BAG DATA **");
	}

	public static boolean tryVacuumIntoOpenContainer(EntityPlayer player, AlchBagContainer container, EntityItem item)
	{
		IInventory inv = container.inventory;

		if (ItemHelper.invContainsItem(inv, new ItemStack(ObjHandler.blackHole, 1, 1)) || ItemHelper.invContainsItem(inv, new ItemStack(ObjHandler.voidRing, 1, 1))
				&& ItemHelper.hasSpace(inv, item.getEntityItem()))
		{
			finishVacuum(player, item, ItemHelper.pushStackInInv(inv, item.getEntityItem()));
			return true;
		}

		return false;
	}

	public static boolean tryVacuumIntoSuctionBag(EntityPlayer player, EntityItem item)
	{
		ItemStack bag = AlchemicalBag.getFirstBagWithSuctionItem(player, player.inventory.mainInventory);

		if (bag == null)
		{
			return false;
		}

		ItemStack[] inv = AlchemicalBags.get(player, (byte) bag.getItemDamage());

		if (ItemHelper.hasSpace(inv, item.getEntityItem()))
		{
			finishVacuum(player, item, ItemHelper.pushStackInInv(inv, item.getEntityItem()));

			AlchemicalBags.set(player, (byte) bag.getItemDamage(), inv);
			AlchemicalBags.syncPartial(player, bag.getItemDamage());

			return true;
		}

		return false;
	}

	private static void finishVacuum(EntityPlayer player, EntityItem item, ItemStack remain)
	{
		if (remain == null)
		{
			item.delayBeforeCanPickup = 10;
			item.setDead();
			player.worldObj.playSoundAtEntity(player, "random.pop", 0.2F, ((player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		}
		else
		{
			item.setEntityItemStack(remain);
		}
	}
}
