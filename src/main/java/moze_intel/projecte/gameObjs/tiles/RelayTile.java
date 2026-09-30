package moze_intel.projecte.gameObjs.tiles;

import moze_intel.projecte.api.tile.IEmcAcceptor;
import moze_intel.projecte.api.tile.IEmcProvider;
import moze_intel.projecte.gameObjs.blocks.Relay;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class RelayTile extends AbstractTieredEmcTile implements IEmcAcceptor, IEmcProvider
{
	private long chargeRate;

	public RelayTile()
	{
		super();
	}

	public RelayTile(int tier)
	{
		super(tier);
	}

	@Override
	protected void setupConfig()
	{
		int tierIndex = Math.max(0, Math.min(tier - 1, Constants.RELAY_MK_MAX.length - 1));

		this.setMaximumEMC(Constants.RELAY_MK_MAX[tierIndex]);
		this.chargeRate = Constants.RELAY_MK_OUTPUT[tierIndex];
	}

	@Override
	protected boolean isOwnBlock(Block block)
	{
		return block instanceof Relay;
	}

	@Override
	protected int getBlockTier(Block block)
	{
		return ((Relay) block).getTier();
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
