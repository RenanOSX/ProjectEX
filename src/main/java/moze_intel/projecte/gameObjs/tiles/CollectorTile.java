package moze_intel.projecte.gameObjs.tiles;

import moze_intel.projecte.api.tile.IEmcProvider;
import moze_intel.projecte.gameObjs.blocks.Collector;
import moze_intel.projecte.utils.Constants;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;


public class CollectorTile extends AbstractTieredEmcTile implements IEmcProvider
{
	private long emcGen;

	public CollectorTile()
	{
		super();
	}

	public CollectorTile(int tier)
	{
		super(tier);
	}

	@Override
	protected void setupConfig()
	{
		// Default to MK1 if tier is out of bounds (shouldn't happen if config matches)
		int tierIndex = Math.max(0, Math.min(tier - 1, Constants.COLLECTOR_MK_MAX.length - 1));

		this.setMaximumEMC(Constants.COLLECTOR_MK_MAX[tierIndex]);
		this.emcGen = Constants.COLLECTOR_MK_GEN[tierIndex];
	}

	@Override
	protected boolean isOwnBlock(Block block)
	{
		return block instanceof Collector;
	}

	@Override
	protected int getBlockTier(Block block)
	{
		return ((Collector) block).getTier();
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
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble("EMC", this.getStoredEmc());
		nbt.setInteger("Tier", tier);
	}

	private void sendRelayBonus()
	{
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

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
