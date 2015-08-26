package rocks.boltsandnuts.bbtweaks.command;

import java.util.Calendar;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import rocks.boltsandnuts.bbtweaks.items.ItemRegistry;

public class CommandBB extends CommandBase {

	private static final String NBTTagInt = null;


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

	
	public static void giveBreakBit(ICommandSender command){
			
		long curTime=0, Llast=0, timeUntil=0;
		int time=0, last;
		String out;
		EntityPlayer e = command.getEntityWorld().getPlayerEntityByName(command.getCommandSenderName());
		NBTTagCompound tag = e.getEntityData();
		Calendar c=Calendar.getInstance();
		
		NBTBase modeTag = tag.getTag("lastBB");
		
		if (modeTag != null) {
			Llast =  ((NBTTagLong)modeTag).func_150291_c();
			}
		
		curTime = System.currentTimeMillis();
		c.setTimeInMillis(curTime);
		time=c.get(Calendar.HOUR);
		c.setTimeInMillis(Llast);
		last=c.get(Calendar.HOUR);
		//out = "TimeHours: " + time + " lasthours: " + last;
		
		if ((time-last) < 24 && last != 0)
		{
			timeUntil = 24 - (time-last);
			out = "Try again in " + timeUntil + " Hours.";// + " result:" + (time-last) + " Last: " + Llast + " Time: " + curTime;
			e.addChatMessage(new ChatComponentTranslation("You aren't eligible for another Breakbit yet."));
			e.addChatMessage(new ChatComponentTranslation(out));
			return;
		}
		
		ItemStack BB = new ItemStack(ItemRegistry.breakbit_invar);

		if (!e.inventory.addItemStackToInventory(BB)){
			e.addChatMessage(new ChatComponentTranslation("Not enough inventory Space."));
		}
		else{
			//out = "Try again in " + timeUntil + " Hours" + " result:" + (time/last) + " Last: " + last + " Time: " + time;
			//e.addChatMessage(new ChatComponentTranslation(out));
			e.addChatMessage(new ChatComponentTranslation("Granted your Daily Invar BreakBit!"));
			tag.setLong("lastBB", curTime);

		}
		

			
		return;
	}
	
}
