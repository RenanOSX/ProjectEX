package moze_intel.projecte.gameObjs.tiles;

import moze_intel.projecte.api.tile.IEmcAcceptor;
import moze_intel.projecte.api.tile.IEmcProvider;
import moze_intel.projecte.gameObjs.blocks.Relay;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RelayTile extends TileEmc implements IEmcAcceptor, IEmcProvider
{
	private long chargeRate;
	private int tier;
	
	public RelayTile()
	{
		super();
	}

	public RelayTile(int tier)
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
		int tierIndex = Math.max(0, Math.min(tier - 1, Constants.RELAY_MK_MAX.length - 1));
		
		this.setMaximumEMC(Constants.RELAY_MK_MAX[tierIndex]);
		this.chargeRate = Constants.RELAY_MK_OUTPUT[tierIndex];
	}

	private void initTier()
	{
		Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
		if (block instanceof Relay)
		{
			this.tier = ((Relay) block).getTier();
			setupConfig();
		}
		else
		{
			this.invalidate();
		}
	}
	
	public int getTier()
	{
		return tier;
	}
	
	public double getBonus()
	{
		int tierIndex = Math.max(0, Math.min(tier - 1, Constants.RELAY_MK_BONUS.length - 1));
		return Constants.RELAY_MK_BONUS[tierIndex];
	}
	
	@Override
	public void updateEntity()
	{	
		if (chargeRate == 0)
		{
			initTier();
		}

		if (worldObj.isRemote) 
		{
			return;
		}

		sendEmc();
	}
	
	private void sendEmc()
	{
		if (this.getStoredEmc() == 0) return;

		if (this.getStoredEmc() <= chargeRate)
		{
			this.sendToAllAcceptors(this.getStoredEmc());
		}
		else 
		{
			this.sendToAllAcceptors(chargeRate);
		}
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
		nbt.setInteger("Tier", tier);
	}

	@Override
	public double acceptEMC(ForgeDirection side, double toAccept)
	{
		if (worldObj.getTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ) instanceof RelayTile)
		{
			return 0;
		}
		else
		{
			double toAdd = Math.min(maximumEMC - currentEMC, toAccept);
			currentEMC += toAdd;
			return toAdd;
		}
	}

	@Override
	public double provideEMC(ForgeDirection side, double toExtract)
	{
		double toRemove = Math.min(currentEMC, toExtract);
		currentEMC -= toRemove;
		return toRemove;
	}
}
