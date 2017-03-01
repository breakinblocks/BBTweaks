package com.breakinblocks.bbtweaks.command;

import com.breakinblocks.bbtweaks.util.TextHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class CommandSayLocation extends CommandBase {

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
			loc = String.format(
					TextFormatting.LIGHT_PURPLE + "%s is @" + TextFormatting.GREEN + " (" + TextFormatting.DARK_GREEN
							+ "%.1f" + TextFormatting.GREEN + ")" + TextFormatting.GREEN + " ("
							+ TextFormatting.DARK_GREEN + "%.1f" + TextFormatting.GREEN + ")" + TextFormatting.GREEN
							+ " (" + TextFormatting.DARK_GREEN + "%.1f" + TextFormatting.GREEN + ")"
							+ TextFormatting.LIGHT_PURPLE + " in: " + TextFormatting.DARK_GREEN + "["
							+ TextFormatting.GREEN + "%s" + TextFormatting.DARK_GREEN + "]",
					name, x, y, z, command.getEntityWorld().provider.getDimension());

			message = new TextComponentTranslation(TextHelper.localize(loc));
			for (int i = 0; i < player.world.playerEntities.size(); i++)
				player.world.playerEntities.get(i).sendMessage(message);
		}
		return;

	}

	@Override
	public String getName() {
		return "saylocation";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/saylocation";
	}

}
