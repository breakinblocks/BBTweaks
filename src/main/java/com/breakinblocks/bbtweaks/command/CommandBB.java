package com.breakinblocks.bbtweaks.command;

import com.breakinblocks.bbtweaks.common.registry.ModItems;
import com.breakinblocks.bbtweaks.util.TextHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandBB extends CommandBase {

	public static int cooldown = 1000 * 60 * 60 * 24;
	public static int maxBreakbits = 32;
	public static String TAG_LAST_BB = "lastBB";

	public static void giveBreakBit(ICommandSender sender) {
		if (!(sender instanceof EntityPlayer)) {
			sender.sendMessage(new TextComponentTranslation("command.bbtweaks.not_a_player"));
			return;
		}

		EntityPlayer player = (EntityPlayer) sender;
		NBTTagCompound data = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		long timeNoSee = data.getLong(TAG_LAST_BB);
		long time = System.currentTimeMillis();
		int amount = (int) ((time - timeNoSee) / cooldown);

		if (amount <= 0) {
			long range = cooldown - (time - timeNoSee);
			String out;
			out = String.format(TextHelper.localize("command.bbtweaks.bb.try_again"),
					TextHelper.formatTimeFriendly(range));
			player.sendMessage(new TextComponentTranslation(TextHelper.localize("command.bbtweaks.bb.not_eligible")));
			player.sendMessage(new TextComponentTranslation(out));
			return;
		}

		if (amount > maxBreakbits) {
			amount = 1; // They've gone inactive or this is their first one
		} else {
			// Move forward scaled by the amount given
			time = timeNoSee + amount * cooldown;
		}

		ItemStack BB = new ItemStack(ModItems.breakbitinvar, amount, 0);

		if (!player.inventory.addItemStackToInventory(BB)) {
			player.sendMessage(
					new TextComponentTranslation(TextHelper.localize("command.bbtweaks.not_enough_inventory_space")));
		} else {
			player.sendMessage(new TextComponentTranslation(TextHelper.localize("command.bbtweaks.bb.granted")));
			data.setLong(TAG_LAST_BB, time);
		}
		return;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		CommandBB.giveBreakBit(sender);

	}

	@Override
	public String getName() {
		return "bb";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/bb";
	}
}
