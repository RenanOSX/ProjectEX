package moze_intel.projecte.gameObjs.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public abstract class AbstractTieredEmcTile extends TileEmc
{
	protected int tier;

	public AbstractTieredEmcTile()
	{
		super();
	}

	public AbstractTieredEmcTile(int tier)
	{
		this.tier = tier;
		setupConfig();
	}

	protected abstract void setupConfig();

	protected abstract boolean isOwnBlock(Block block);

	protected abstract int getBlockTier(Block block);

	protected void initTier()
	{
		Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
		if (isOwnBlock(block))
		{
			this.tier = getBlockTier(block);
			setupConfig();
		}
		else
		{
			// The block is not the expected tiered block, this TileEntity is invalid.
			// This can happen if the block was removed but the TE is still updating.
			this.invalidate();
		}
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
}
