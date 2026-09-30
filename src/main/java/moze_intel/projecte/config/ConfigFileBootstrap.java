package moze_intel.projecte.config;

import moze_intel.projecte.utils.PELogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class ConfigFileBootstrap
{
	public interface DefaultWriter
	{
		void write() throws IOException;
	}

	private ConfigFileBootstrap() {}

	public static boolean bootstrap(File config, String version, String staleMessage, DefaultWriter writer)
	{
		if (!config.exists())
		{
			try
			{
				if (config.createNewFile())
				{
					writer.write();
					return true;
				}
			}
			catch (IOException e)
			{
				PELogger.logFatal("Exception in file I/O: couldn't create custom configuration files.");
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			try (BufferedReader reader = new BufferedReader(new FileReader(config)))
			{
				String line = reader.readLine();

				if (line == null || !line.equals(version))
				{
					PELogger.logFatal(staleMessage);
					writer.write();
				}
			}
			catch (IOException e)
			{
				PELogger.logFatal("Exception in file I/O: couldn't create custom configuration files.");
				e.printStackTrace();
			}

			return true;
		}

		return false;
	}
}
