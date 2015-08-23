package rocks.boltsandnuts.bbtweaks.command;

import amerifrance.guideapi.api.GuideRegistry;
import rocks.boltsandnuts.bbtweaks.BBTweaksGuide;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;

public class CommnandNab extends CommandBase {

	@Override
	public String getCommandName() {
		return "nab";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {

		return "/nab";
	}

	@Override
	public void processCommand(ICommandSender command, String[] p_71515_2_) {
		CommnandNab.giveBook(command);
		return;
	}

	
	public static void giveBook(ICommandSender command){
		ItemStack book = GuideRegistry.getItemStackForBook(BBTweaksGuide.myBook);
		if (!command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).inventory.addItemStackToInventory(book))
			command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).entityDropItem(book, 0);
	
		command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName()).addChatMessage(new ChatComponentTranslation("Generated new copy of NabTweaks, enjoy!"));
		
		return;
	}
	
}
