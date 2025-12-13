package moze_intel.projecte.gameObjs.tiles;

import moze_intel.projecte.gameObjs.blocks.PowerFlower;
import moze_intel.projecte.playerData.Transmutation;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import java.util.UUID;

public class PowerFlowerTile extends TileEntity
{
	public UUID owner = new UUID(0L, 0L);
	public String name = "";
	public double storedEMC = 0;
	private int tier;

	public PowerFlowerTile()
	{
	}

	public PowerFlowerTile(int tier)
	{
		this.tier = tier;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if (nbt.hasKey("OwnerUUID"))
		{
			owner = UUID.fromString(nbt.getString("OwnerUUID"));
		}
		name = nbt.getString("Name");
		storedEMC = nbt.getDouble("EMC");
		tier = nbt.getInteger("Tier");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setString("OwnerUUID", owner.toString());
		nbt.setString("Name", name);
		nbt.setDouble("EMC", storedEMC);
		nbt.setInteger("Tier", tier);
	}

	private void initTier()
	{
		Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
		if (block instanceof PowerFlower)
		{
			this.tier = ((PowerFlower) block).getTier();
		}
		else
		{
			this.invalidate();
		}
	}

	@Override
	public void updateEntity()
	{
		if (tier == 0)
		{
			initTier();
		}

		if (worldObj.isRemote)
		{
			return;
		}

		if (worldObj.getTotalWorldTime() % 20L != ((xCoord ^ zCoord) % 20))
		{
			return;
		}

		storedEMC += getProduction();

		EntityPlayerMP player = null;
		
		for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList)
		{
			EntityPlayerMP p = (EntityPlayerMP) obj;
			if (p.getUniqueID().equals(owner))
			{
				player = p;
				break;
			}
		}

		if (player != null)
		{
			double currentEMC = Transmutation.getEmc(player);
			Transmutation.setEmc(player, currentEMC + storedEMC);
			Transmutation.sync(player);
			storedEMC = 0;
		}
		else
		{
			this.markDirty();
		}
	}
	
	public double getProduction()
	{
		int tierIndex = Math.max(0, Math.min(tier - 1, Constants.POWER_FLOWER_GEN.length - 1));
		return Constants.POWER_FLOWER_GEN[tierIndex];
	}
}
