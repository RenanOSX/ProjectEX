package moze_intel.projecte.gameObjs;

import com.google.common.base.Throwables;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import moze_intel.projecte.PECore;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.PELogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public final class RegistryMigration
{
	private RegistryMigration() {}

	public static void remap(FMLMissingMappingsEvent event) {
		for (FMLMissingMappingsEvent.MissingMapping mapping : event.get())
		{
			try
			{
				String subName = mapping.name.split(":")[1];
				if (mapping.type == GameRegistry.Type.ITEM)
				{
					Item remappedItem = GameRegistry.findItem(PECore.MODID, "item.pe_" + subName.substring(5)); // strip "item." off of subName
					if (remappedItem != null)
					{
						// legacy remap (adding pe_ prefix)
						mapping.remap(remappedItem);
					}
					else
					{
						// Space strip remap - ItemBlocks
						String newSubName = Constants.SPACE_STRIP_NAME_MAP.get(subName);
						remappedItem = GameRegistry.findItem(PECore.MODID, newSubName);

						if (remappedItem != null)
						{
							mapping.remap(remappedItem);
							PELogger.logInfo(String.format("Remapped ProjectE ItemBlock from %s to %s", mapping.name, PECore.MODID + ":" + newSubName));
						}
						else
						{
							PELogger.logFatal("Failed to remap ProjectE ItemBlock: " + mapping.name);
						}
					}
				}
				if (mapping.type == GameRegistry.Type.BLOCK)
				{
					// Space strip remap - Blocks
					String newSubName = Constants.SPACE_STRIP_NAME_MAP.get(subName);
					Block remappedBlock = GameRegistry.findBlock(PECore.MODID, newSubName);

					if (remappedBlock != null)
					{
						mapping.remap(remappedBlock);
						PELogger.logInfo(String.format("Remapped ProjectE Block from %s to %s", mapping.name, PECore.MODID + ":" + newSubName));
					}
					else
					{
						PELogger.logFatal("Failed to remap PE Block: " + mapping.name);
					}
				}
			} catch (Throwable t)
			{
				// Should never happen
				throw Throwables.propagate(t);
			}
		}
	}
}
