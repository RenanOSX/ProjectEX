package moze_intel.projecte.gameObjs.blocks;

import moze_intel.projecte.gameObjs.tiles.RelayTile;
import moze_intel.projecte.utils.ComparatorHelper;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Relay extends AbstractTieredEmcBlock
{
	public Relay(int tier)
	{
		super(Material.rock, "pe_relay_MK" + Integer.toString(tier), "relays", tier, 10.0f);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new RelayTile(getTier());
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int meta)
	{
		return ComparatorHelper.getForRelay(world, x, y, z);
	}
}
