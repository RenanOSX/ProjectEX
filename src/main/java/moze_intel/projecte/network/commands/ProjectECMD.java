package moze_intel.projecte.network.commands;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProjectECMD extends ProjectEBaseCMD
{
	private static final List<String> commands = Lists.newArrayList("changelog", "clearKnowledge", "setEMC", "reloadEMC", "removeEMC", "resetEMC");

	ChangelogCMD changelogcmd = new ChangelogCMD();
	ReloadEmcCMD reloademccmd = new ReloadEmcCMD();
	SetEmcCMD setemccmd = new SetEmcCMD();
	RemoveEmcCMD removeemccmd = new RemoveEmcCMD();
	ResetEmcCMD resetemccmd = new ResetEmcCMD();
	ClearKnowledgeCMD clearknowledgecmd = new ClearKnowledgeCMD();

	private final Map<String, ProjectEBaseCMD> subCommandMap = new LinkedHashMap<String, ProjectEBaseCMD>();
	{
		subCommandMap.put("changelog", changelogcmd);
		subCommandMap.put("clearknowledge", clearknowledgecmd);
		subCommandMap.put("setemc", setemccmd);
		subCommandMap.put("reloademc", reloademccmd);
		subCommandMap.put("removeemc", removeemccmd);
		subCommandMap.put("resetemc", resetemccmd);
	}

	@Override
	public String getCommandName() 
	{
		return "projecte";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "pe.command.main.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() 
	{
		return 0;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] params)
	{
		if (params.length == 1)
		{
			return Lists.newArrayList(Iterables.filter(commands, new LowerCasePrefixPredicate(params[0])));
		}

		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params) 
	{
		if (params.length < 1)
		{
			sendError(sender, new ChatComponentTranslation("pe.command.main.usage"));
			return;
		}

		String[] relayparams = new String[0];

		if (params.length > 1)
		{
			relayparams = Arrays.copyOfRange(params, 1, params.length);
		}

		String subName = params[0].toLowerCase(Locale.ROOT);

		ProjectEBaseCMD subCommand = subCommandMap.get(subName);

		if (subCommand != null)
		{
			if (subCommand.canCommandSenderUseCommand(sender))
			{
				subCommand.processCommand(sender, relayparams);
			}
			else
			{
				sendError(sender, new ChatComponentTranslation("commands.generic.permission"));
			}
		}
		else
		{
			sendError(sender, new ChatComponentTranslation("pe.command.main.usage"));
		}

	}


	private static class LowerCasePrefixPredicate implements Predicate<String>
	{
		private final String prefix;
		public LowerCasePrefixPredicate(String prefix)
		{
			this.prefix = prefix;
		}

		@Override
		public boolean apply(String input)
		{
			return input.toLowerCase(Locale.ROOT).startsWith(prefix.toLowerCase(Locale.ROOT));
		}
	}
}
