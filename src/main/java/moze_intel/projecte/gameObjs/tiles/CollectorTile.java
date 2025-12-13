package moze_intel.projecte.gameObjs.tiles;

import moze_intel.projecte.api.tile.IEmcProvider;
import moze_intel.projecte.gameObjs.blocks.Collector;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Map;

public class CollectorTile extends TileEmc implements IEmcProvider
{
	private long emcGen;
	private int tier;
	
	public CollectorTile()
	{
		super();
	}

	public CollectorTile(int tier)
	{
		this.tier = tier;
		setupConfig();
	}
	
	@Override
	public void validate()
	{
		super.validate();
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("Tier", tier);
		tag.setDouble("EMC", this.getStoredEmc());
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}

	private void setupConfig()
	{
		// Default to MK1 if tier is out of bounds (shouldn't happen if config matches)
		int tierIndex = Math.max(0, Math.min(tier - 1, Constants.COLLECTOR_MK_MAX.length - 1));
		
		this.setMaximumEMC(Constants.COLLECTOR_MK_MAX[tierIndex]);
		this.emcGen = Constants.COLLECTOR_MK_GEN[tierIndex];
	}

	private void initTier()
	{
		Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
		if (block instanceof Collector)
		{
			this.tier = ((Collector) block).getTier();
			setupConfig();
		}
		else
		{
			// The block is not a Collector, this TileEntity is invalid.
			// This can happen if the block was removed but the TE is still updating.
			this.invalidate();
		}
	}
	
	@Override
	public void updateEntity()
	{
		if (emcGen == 0)
		{
			initTier();
		}

		if (worldObj.isRemote) 
		{
			return;
		}
		
		if (!this.hasMaxedEmc())
		{
			this.addEMC(getSunRelativeEmc(emcGen) / 20.0f);
		}
		
		updateEmc();
	}
	
	public void updateEmc()
	{
		if (this.getStoredEmc() == 0)
		{
			return;
		}
		
		double toSend = this.getStoredEmc() < emcGen ? this.getStoredEmc() : emcGen;
		this.sendToAllAcceptors(toSend);
		this.sendRelayBonus();
	}
	
	private double getSunRelativeEmc(long emc)
	{
		return (double) getSunLevel() * emc / 16;
	}
	
	public int getSunLevel()
	{
		if (worldObj.provider.isHellWorld)
		{
			return 16;
		}
		return worldObj.getBlockLightValue(xCoord, yCoord + 1, zCoord) + 1;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if (nbt.hasKey("Tier"))
		{
			this.tier = nbt.getInteger("Tier");
			setupConfig();
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble("EMC", this.getStoredEmc());
		nbt.setInteger("Tier", tier);
	}

	private void sendRelayBonus()
	{
		for (Map.Entry<ForgeDirection, TileEntity> entry: WorldHelper.getAdjacentTileEntitiesMapped(worldObj, this).entrySet())
		{
			ForgeDirection dir = entry.getKey();
			TileEntity tile = entry.getValue();

			if (tile instanceof RelayTile)
			{
				((RelayTile) tile).acceptEMC(dir, ((RelayTile) tile).getBonus());
			}
		}
	}

	@Override
	public double provideEMC(ForgeDirection side, double toExtract)
	{
		double toRemove = Math.min(currentEMC, toExtract);
		removeEMC(toRemove);
		return toRemove;
	}
}
