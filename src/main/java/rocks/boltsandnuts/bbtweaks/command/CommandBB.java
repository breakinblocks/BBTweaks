package rocks.boltsandnuts.bbtweaks.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import rocks.boltsandnuts.bbtweaks.items.ItemRegistry;

public class CommandBB extends CommandBase {

	public static int cooldown = 1000 * 60 * 60 * 24;

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

	public static void giveBreakBit(ICommandSender command) {
		EntityPlayer player = command.getEntityWorld().getPlayerEntityByName(
				command.getCommandSenderName());
		NBTTagCompound data = player.getEntityData();
		long timeNoSee = data.getLong("lastBB");
		long time = System.currentTimeMillis();
		if (time - timeNoSee < cooldown) {
			String out;
			out = "Try again in " + gimme(cooldown - (time - timeNoSee));
			player.addChatMessage(new ChatComponentTranslation(
					"You aren't eligible for another Breakbit yet."));
			player.addChatMessage(new ChatComponentTranslation(out));
			return;
		}

		ItemStack BB = new ItemStack(ItemRegistry.breakbit_invar);

		if (!player.inventory.addItemStackToInventory(BB)) {
			player.addChatMessage(new ChatComponentTranslation(
					"Not enough inventory Space."));
		} else {
			player.addChatMessage(new ChatComponentTranslation(
					"Granted your Daily Invar BreakBit!"));
			data.setLong("lastBB", time);
		}
		return;
	}

	private static String gimme(long time) {
		long hours = time / 1000 / 60 / 60;
		long minutes = time / 1000 / 60 % 60;
		long seconds = time / 1000 % 60;
		return String.format("%d hours, %d minutes, %d seconds.", hours,
				minutes, seconds);
	}
}
