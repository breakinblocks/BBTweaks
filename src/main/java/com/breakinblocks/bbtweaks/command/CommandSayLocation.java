package com.breakinblocks.bbtweaks.command;

import com.breakinblocks.bbtweaks.util.TextHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;



@SuppressWarnings("deprecation")
public class CommandSayLocation extends CommandBase {

	@Override
	public String getCommandName() {
		return "saylocation";

	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/saylocation";
	}


	@Override
	public void execute(MinecraftServer server, ICommandSender command, String[] args) throws CommandException {
		String loc = new String();
		String name = new String();
		TextComponentTranslation message = null;
		EntityPlayer player = null;
		double x, y, z;
		
		if (command.getCommandSenderEntity() instanceof EntityPlayer)
			player = (EntityPlayer) command.getCommandSenderEntity();
		else
			return;
		
		name = player.getName();
		
		if (player != null) {
			x = player.posX;
			y = player.posY;
			z = player.posZ;
			loc = String.format(TextFormatting.LIGHT_PURPLE
					+ "%s is @" + TextFormatting.GREEN + " ("
					+ TextFormatting.DARK_GREEN + "%.1f"
					+ TextFormatting.GREEN + ")" + TextFormatting.GREEN
					+ " (" + TextFormatting.DARK_GREEN + "%.1f"
					+ TextFormatting.GREEN + ")" + TextFormatting.GREEN
					+ " (" + TextFormatting.DARK_GREEN + "%.1f"
					+ TextFormatting.GREEN + ")"
					+ TextFormatting.LIGHT_PURPLE + " in: "
					+ TextFormatting.DARK_GREEN + "["
					+ TextFormatting.GREEN + "%s"
					+ TextFormatting.DARK_GREEN + "]", name, x, y, z,
					command.getEntityWorld().provider.getDimension());

			
			message = new TextComponentTranslation(TextHelper.localize(loc));
			for (int i = 0; i < player.worldObj.playerEntities.size(); i++)
				player.worldObj.playerEntities.get(i).addChatMessage(message);
		}
		return;
		
	}

}
