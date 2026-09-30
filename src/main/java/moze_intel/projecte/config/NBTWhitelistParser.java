package moze_intel.projecte.config;

import moze_intel.projecte.PECore;
import moze_intel.projecte.utils.FileHelper;
import moze_intel.projecte.utils.ItemHelper;
import moze_intel.projecte.registry.NBTWhitelist;
import moze_intel.projecte.utils.PELogger;
import net.minecraft.item.ItemStack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public final class NBTWhitelistParser
{
	private static final String VERSION = "#0.1a";
	private static File CONFIG;
	private static boolean loaded;

	public static void init()
	{
		CONFIG = new File(PECore.CONFIG_DIR, "nbt_whitelist.cfg");
		loaded = ConfigFileBootstrap.bootstrap(CONFIG, VERSION, "Found old NBT whitelist file: resetting.", new ConfigFileBootstrap.DefaultWriter()
		{
			@Override
			public void write() throws IOException
			{
				writeDefaultFile();
			}
		});
	}

	public static void readUserData()
	{
		if (!loaded)
		{
			PELogger.logFatal("ERROR: configurations files are not loaded!");
			return;
		}

		LineNumberReader reader = null;

		try
		{
			reader = new LineNumberReader(new FileReader(CONFIG));

			String line;

			while ((line = reader.readLine()) != null)
			{
				line = line.trim();

				if (line.isEmpty() || line.charAt(0) == '#')
				{
					continue;
				}

				ItemStack stack = ItemHelper.getStackFromString(line, 0);

				if (stack == null)
				{
					PELogger.logFatal("Error in NBT whitelist file: no item stack found for " + line);
					PELogger.logFatal("At line: " + reader.getLineNumber());
					continue;
				}

				if (NBTWhitelist.register(stack))
				{
					PELogger.logInfo("Registered user-defined NBT whitelist for: " + line);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			FileHelper.closeStream(reader);
		}
	}

	private static void writeDefaultFile()
	{
		PrintWriter writer = null;

		try
		{
			writer = new PrintWriter(CONFIG);

			writer.println(VERSION);
			writer.println("#Custom NBT whitelist file");
			writer.println("#This file is used for items that should keep NBT data when condensed/transmuted.");
			writer.println("#To add an item, just put it's unlocalized name on a new line. Here's some examples:");
			writer.println("TConstruct:pickaxe");
			writer.println("ExtraUtilities:unstableingot");
			writer.println("ExtraUtilities:unstableIngot");
			writer.println("Botania:specialFlower");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			FileHelper.closeStream(writer);
		}
	}
}
