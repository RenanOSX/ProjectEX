package moze_intel.projecte;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import moze_intel.projecte.config.CustomEMCParser;
import moze_intel.projecte.config.NBTWhitelistParser;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.events.ConnectionHandler;
import moze_intel.projecte.events.PlayerEvents;
import moze_intel.projecte.events.TickEvents;
import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.RegistryMigration;
import moze_intel.projecte.api.impl.IMCHandler;
import moze_intel.projecte.integration.Integration;
import moze_intel.projecte.lifecycle.ServerLifecycle;
import moze_intel.projecte.network.PacketHandler;
import moze_intel.projecte.proxies.IProxy;
import moze_intel.projecte.gameObjs.AchievementHandler;
import moze_intel.projecte.gameObjs.gui.GuiHandler;
import moze_intel.projecte.utils.PELogger;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod(modid = PECore.MODID, name = PECore.MODNAME, version = PECore.VERSION)
public class PECore
{
	public static final String MODID = "ProjectE";
	public static final String MODNAME = "ProjectEX";
	public static final String VERSION = Tags.VERSION;
	public static final GameProfile FAKEPLAYER_GAMEPROFILE = new GameProfile(UUID.fromString("590e39c7-9fb6-471b-a4c2-c0e539b2423d"), "[ProjectE]");
	public static File CONFIG_DIR;
	public static File PREGENERATED_EMC_FILE;

	@Instance(MODID)
	public static PECore instance;
	
	@SidedProxy(clientSide = "moze_intel.projecte.proxies.ClientProxy", serverSide = "moze_intel.projecte.proxies.ServerProxy")
	public static IProxy proxy;

	public static final List<String> uuids = new CopyOnWriteArrayList<String>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		CONFIG_DIR = new File(event.getModConfigurationDirectory(), "ProjectE");
		
		if (!CONFIG_DIR.exists())
		{
			CONFIG_DIR.mkdirs();
		}

		PREGENERATED_EMC_FILE = new File(CONFIG_DIR, "pregenerated_emc.json");
		ProjectEConfig.init(new File(CONFIG_DIR, "ProjectEX.cfg"));

		CustomEMCParser.init();

		NBTWhitelistParser.init();

		PacketHandler.register();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(PECore.instance, new GuiHandler());

		PlayerEvents pe = new PlayerEvents();
		MinecraftForge.EVENT_BUS.register(pe);
		FMLCommonHandler.instance().bus().register(pe);

		FMLCommonHandler.instance().bus().register(new TickEvents());
		FMLCommonHandler.instance().bus().register(new ConnectionHandler());

		proxy.registerClientOnlyEvents();

		ObjHandler.register();
		ObjHandler.addRecipes();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerKeyBinds();
		proxy.registerRenderers();
		AchievementHandler.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ObjHandler.registerPhiloStoneSmelting();
		NBTWhitelistParser.readUserData();
		proxy.initializeManual();
		
		Integration.init();
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		ServerLifecycle.starting(event);
	}

	@Mod.EventHandler
	public void serverStopping (FMLServerStoppingEvent event)
	{
		ServerLifecycle.stopping(event);
	}

	@Mod.EventHandler
	public void serverQuit(FMLServerStoppedEvent event)
	{
		ServerLifecycle.stopped(event);
	}

	@Mod.EventHandler
	public void onIMCMessage(FMLInterModComms.IMCEvent event)
	{
		for (FMLInterModComms.IMCMessage msg : event.getMessages())
		{
			IMCHandler.handleIMC(msg);
		}
	}

	@Mod.EventHandler
	public void remap(FMLMissingMappingsEvent event) {
		RegistryMigration.remap(event);
	}
}
