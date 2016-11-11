package com.breakinblocks.bbtweaks.command;

import com.breakinblocks.bbtweaks.items.ItemRegistry;
import com.breakinblocks.bbtweaks.util.TextHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;

public class CommandBB extends CommandBase {

	public static int cooldown = 1000 * 60 * 60 * 24;
	public static int maxBreakbits = 32;
	public static String TAG_LAST_BB = "lastBB";

	@Override
	public String getCommandName() {
		return "bb";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {

		return "/bb";
	}

	@Override
	public void processCommand(ICommandSender command, String[] p_71515_2_) {
		CommandBB.giveBreakBit(command);
		return;
	}

	public static void giveBreakBit(ICommandSender sender) {
		if (!(sender instanceof EntityPlayer)) {
			sender.addChatMessage(new ChatComponentTranslation(
					TextHelper.localize("command.bbtweaks.not_a_player")));
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
			out = String.format(
					TextHelper.localize("command.bbtweaks.bb.try_again"),
					TextHelper.formatTimeFriendly(range));
			player.addChatMessage(new ChatComponentTranslation(
					TextHelper.localize("command.bbtweaks.bb.not_eligible")));
			player.addChatMessage(new ChatComponentTranslation(out));
			return;
		}

		if (amount > maxBreakbits) {
			amount = 1; // They've gone inactive or this is their first one
		} else {
			// Move forward scaled by the amount given
			time = timeNoSee + amount * cooldown;
		}
		
		ItemStack BB = new ItemStack(ItemRegistry.breakbit_invar, amount, 0);

		if (!player.inventory.addItemStackToInventory(BB)) {
			player.addChatMessage(new ChatComponentTranslation(
					TextHelper.localize("command.bbtweaks.not_enough_inventory_space")));
		} else {
			player.addChatMessage(new ChatComponentTranslation(
					TextHelper.localize("command.bbtweaks.bb.granted")));
			data.setLong(TAG_LAST_BB, time);
		}
		return;
	}
}
