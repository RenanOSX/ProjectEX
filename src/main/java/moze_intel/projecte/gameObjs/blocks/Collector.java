package moze_intel.projecte.gameObjs.blocks;

import moze_intel.projecte.gameObjs.tiles.CollectorTile;
import moze_intel.projecte.utils.ComparatorHelper;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Collector extends AbstractTieredEmcBlock
{
	public Collector(int tier)
	{
		super(Material.glass, "pe_collector_MK" + tier, "collectors", tier, 0.3f);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new CollectorTile(getTier());
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int meta)
	{
		return ComparatorHelper.getForCollector(world, x, y, z);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return true;
	}
}
