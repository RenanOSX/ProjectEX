package moze_intel.projecte.utils;

import cpw.mods.fml.common.network.IGuiHandler;
import moze_intel.projecte.gameObjs.container.AlchBagContainer;
import moze_intel.projecte.gameObjs.container.AlchChestContainer;
import moze_intel.projecte.gameObjs.container.CondenserContainer;
import moze_intel.projecte.gameObjs.container.CondenserMK2Container;
import moze_intel.projecte.gameObjs.container.DMFurnaceContainer;
import moze_intel.projecte.gameObjs.container.EternalDensityContainer;
import moze_intel.projecte.gameObjs.container.MercurialEyeContainer;
import moze_intel.projecte.gameObjs.container.PedestalContainer;
import moze_intel.projecte.gameObjs.container.PhilosStoneContainer;
import moze_intel.projecte.gameObjs.container.RMFurnaceContainer;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import moze_intel.projecte.gameObjs.container.inventory.AlchBagInventory;
import moze_intel.projecte.gameObjs.container.inventory.EternalDensityInventory;
import moze_intel.projecte.gameObjs.container.inventory.MercurialEyeInventory;
import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
import moze_intel.projecte.gameObjs.gui.GUIAlchChest;
import moze_intel.projecte.gameObjs.gui.GUICondenser;
import moze_intel.projecte.gameObjs.gui.GUICondenserMK2;
import moze_intel.projecte.gameObjs.gui.GUIDMFurnace;
import moze_intel.projecte.gameObjs.gui.GUIEternalDensity;
import moze_intel.projecte.gameObjs.gui.GUIMercurialEye;
import moze_intel.projecte.gameObjs.gui.GUIPedestal;
import moze_intel.projecte.gameObjs.gui.GUIPhilosStone;
import moze_intel.projecte.gameObjs.gui.GUIRMFurnace;
import moze_intel.projecte.gameObjs.gui.GUITransmutation;
import moze_intel.projecte.gameObjs.tiles.AlchChestTile;
import moze_intel.projecte.gameObjs.tiles.CondenserMK2Tile;
import moze_intel.projecte.gameObjs.tiles.CondenserTile;
import moze_intel.projecte.gameObjs.tiles.DMFurnaceTile;
import moze_intel.projecte.gameObjs.tiles.DMPedestalTile;
import moze_intel.projecte.gameObjs.tiles.RMFurnaceTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		
		switch (ID)
		{
			case Constants.ALCH_CHEST_GUI:
				if (tile != null && tile instanceof AlchChestTile)
					return new AlchChestContainer(player.inventory, (AlchChestTile) tile);
				break;
			case Constants.ALCH_BAG_GUI:
				return new AlchBagContainer(player.inventory, new AlchBagInventory(player, player.getHeldItem()));
			case Constants.CONDENSER_GUI:
				if (tile != null && tile instanceof CondenserTile)
					return new CondenserContainer(player.inventory, (CondenserTile) tile);
				break;
			case Constants.RM_FURNACE_GUI:
				if (tile != null && tile instanceof RMFurnaceTile)
					return new RMFurnaceContainer(player.inventory, (RMFurnaceTile) tile);
				break;
			case Constants.DM_FURNACE_GUI:
				if (tile != null && tile instanceof DMFurnaceTile)
					return new DMFurnaceContainer(player.inventory, (DMFurnaceTile) tile);
				break;
			case Constants.MERCURIAL_GUI:
				return new MercurialEyeContainer(player.inventory, new MercurialEyeInventory(player.getHeldItem()));
			case Constants.PHILOS_STONE_GUI:
				return new PhilosStoneContainer(player.inventory);
			case Constants.TRANSMUTATION_GUI:
				return new TransmutationContainer(player.inventory, new TransmutationInventory(player));
			case Constants.ETERNAL_DENSITY_GUI:
				return new EternalDensityContainer(player.inventory, new EternalDensityInventory(player.getHeldItem(), player));
			case Constants.CONDENSER_MK2_GUI:
				return new CondenserMK2Container(player.inventory, (CondenserMK2Tile) tile);
			case Constants.PEDESTAL_GUI:
				return new PedestalContainer(player.inventory, ((DMPedestalTile) tile));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		
		switch (ID)
		{
			case Constants.ALCH_CHEST_GUI:
				if (tile != null && tile instanceof AlchChestTile)
					return new GUIAlchChest(player.inventory, (AlchChestTile) tile);
				break;
			case Constants.ALCH_BAG_GUI:
				return new GUIAlchChest(player.inventory, new AlchBagInventory(player, player.getHeldItem()));
			case Constants.CONDENSER_GUI:
				if (tile != null && tile instanceof CondenserTile)
					return new GUICondenser(player.inventory, (CondenserTile) tile);
				break;
			case Constants.RM_FURNACE_GUI:
				if (tile != null && tile instanceof RMFurnaceTile)
					return new GUIRMFurnace(player.inventory, (RMFurnaceTile) tile);
				break;
			case Constants.DM_FURNACE_GUI:
				if (tile != null && tile instanceof DMFurnaceTile)
					return new GUIDMFurnace(player.inventory, (DMFurnaceTile) tile);
				break;
			case Constants.MERCURIAL_GUI:
				return new GUIMercurialEye(player.inventory, new MercurialEyeInventory(player.getHeldItem()));
			case Constants.PHILOS_STONE_GUI:
				return new GUIPhilosStone(player.inventory);
			case Constants.TRANSMUTATION_GUI:
				return new GUITransmutation(player.inventory, new TransmutationInventory(player));
			case Constants.ETERNAL_DENSITY_GUI:
				player.getHeldItem();
				return new GUIEternalDensity(player.inventory, new EternalDensityInventory(player.getHeldItem(), player));
			case Constants.CONDENSER_MK2_GUI:
				return new GUICondenserMK2(player.inventory, (CondenserMK2Tile) tile);
			case Constants.PEDESTAL_GUI:
				return new GUIPedestal(player.inventory, ((DMPedestalTile) tile));
		}
		
		return null;
	}
}
