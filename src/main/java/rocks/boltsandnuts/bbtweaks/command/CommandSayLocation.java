package rocks.boltsandnuts.bbtweaks.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class CommandSayLocation extends CommandBase {

	@Override
	public String getCommandName() {
		return "saylocation";

	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/saylocation";
	}

	@SuppressWarnings("static-access")
	@Override
	public void processCommand(ICommandSender command, String[] myString) {
		String loc = new String();
		String name = new String();
		double x, y, z;
		EntityPlayer player = command.getEntityWorld().getPlayerEntityByName(
				command.getCommandSenderName());
		name = player.getDisplayName();
		
		if (player != null) {
			x = player.posX;
			y = player.posY;
			z = player.posZ;
			loc = String.format(EnumChatFormatting.LIGHT_PURPLE
					+ "%s is @" + EnumChatFormatting.GREEN + " ("
					+ EnumChatFormatting.ITALIC.DARK_GREEN + "%.1f"
					+ EnumChatFormatting.GREEN + ")" + EnumChatFormatting.GREEN
					+ " (" + EnumChatFormatting.ITALIC.DARK_GREEN + "%.1f"
					+ EnumChatFormatting.GREEN + ")" + EnumChatFormatting.GREEN
					+ " (" + EnumChatFormatting.ITALIC.DARK_GREEN + "%.1f"
					+ EnumChatFormatting.GREEN + ")"
					+ EnumChatFormatting.LIGHT_PURPLE + " in: "
					+ EnumChatFormatting.ITALIC.DARK_GREEN + "["
					+ EnumChatFormatting.GREEN + "%s"
					+ EnumChatFormatting.ITALIC.DARK_GREEN + "]", name, x, y, z,
					command.getEntityWorld().provider.getDimensionName());


				MinecraftServer.getServer().getConfigurationManager()
						.sendChatMsg(new ChatComponentText(loc));
		
		}
		return;
	}

}
