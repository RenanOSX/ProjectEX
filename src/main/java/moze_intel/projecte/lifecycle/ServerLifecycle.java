package moze_intel.projecte.lifecycle;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import moze_intel.projecte.config.CustomEMCParser;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.network.ThreadCheckUUID;
import moze_intel.projecte.network.ThreadCheckUpdate;
import moze_intel.projecte.network.commands.ProjectECMD;
import moze_intel.projecte.playerData.Transmutation;
import moze_intel.projecte.playerData.TransmutationOffline;
import moze_intel.projecte.server.PlayerChecks;
import moze_intel.projecte.server.TileEntityHandler;
import moze_intel.projecte.utils.PELogger;

import java.io.File;

public final class ServerLifecycle
{
	private ServerLifecycle() {}

	public static void starting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new ProjectECMD());

		if (!ThreadCheckUpdate.hasRunServer())
		{
			new ThreadCheckUpdate(true).start();
		}

		if (!ThreadCheckUUID.hasRunServer())
		{
			new ThreadCheckUUID(true).start();
		}

		long start = System.currentTimeMillis();

		CustomEMCParser.readUserData();

		PELogger.logInfo("Starting server-side EMC mapping.");

		EMCMapper.map();

		PELogger.logInfo("Registered " + EMCMapper.emc.size() + " EMC values. (took " + (System.currentTimeMillis() - start) + " ms)");

		File dir = new File(event.getServer().getEntityWorld().getSaveHandler().getWorldDirectory(), "ProjectE");

		if (!dir.exists())
		{
			dir.mkdirs();
		}
	}

	public static void stopping(FMLServerStoppingEvent event)
	{
		TransmutationOffline.cleanAll();
	}

	public static void stopped(FMLServerStoppedEvent event)
	{
		TileEntityHandler.clearAll();
		PELogger.logDebug("Cleared tile entity maps.");

		Transmutation.clearCache();
		PELogger.logDebug("Cleared cached tome knowledge");

		PlayerChecks.clearLists();
		PELogger.logDebug("Cleared player check-lists: server stopping.");

		EMCMapper.clearMaps();
		PELogger.logInfo("Completed server-stop actions.");
	}
}
