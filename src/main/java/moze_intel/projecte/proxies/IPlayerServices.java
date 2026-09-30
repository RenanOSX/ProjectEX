package moze_intel.projecte.proxies;

import moze_intel.projecte.playerData.AlchBagProps;
import moze_intel.projecte.playerData.TransmutationProps;
import net.minecraft.entity.player.EntityPlayer;

public interface IPlayerServices
{
	void clearClientKnowledge();
	TransmutationProps getClientTransmutationProps();
	AlchBagProps getClientBagProps();
	EntityPlayer getClientPlayer();
	boolean isJumpPressed();
}
