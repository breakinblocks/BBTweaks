package com.breakinblocks.bbtweaks.items;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.breakinblocks.bbtweaks.util.ItemNBTHandler;
import com.breakinblocks.bbtweaks.util.KeyboardHelper;
import com.breakinblocks.bbtweaks.util.MinecraftHelper;
import com.breakinblocks.bbtweaks.util.TextHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.lib.research.ResearchManager;
import net.java.games.input.Keyboard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

/**
 * The overhauled sharing tome.
 */
@SuppressWarnings("unused")
public class ItemMeldingManuscript extends ItemBase {

	private static final String TAG_PLAYER = "player";
	private static final String NOT_ASSIGNED = "[none]";
	KeyboardHelper kb =  new KeyboardHelper();
	public ItemMeldingManuscript() {
		super("manuscript", "meldingManuscript");
		setMaxStackSize(1);
		this.bFull3D = true;
		this.setColor = TextHelper.PURPLE;
		this.setInfo = "Right click to Bind";
		// addInformation(null, null, null, false);
	}

	private List<String> getPlayerResearch(ItemStack itemStack) {
		List<String> retVals = new ArrayList<String>();
		NBTTagCompound cmp = itemStack.getTagCompound();
		if (!cmp.hasKey("research"))
			return retVals;
		NBTTagList list = cmp.getTagList("research", Constants.NBT.TAG_STRING);
		for (int i = 0; i < list.tagCount(); i++) {
			retVals.add(list.getStringTagAt(i));
		}
		return retVals;
	}

	
	@Override
	public ItemStack onItemRightClick(ItemStack IStack, World VarWorld,
			EntityPlayer EntityP) {
		ResearchManager tcproxy = new thaumcraft.common.lib.research.ResearchManager();
			String name = getPlayerName(IStack);
			if (name.endsWith(NOT_ASSIGNED)) {
				setPlayerName(IStack, EntityP.getGameProfile().getName());
				setPlayerResearch(IStack, EntityP.getGameProfile().getName());

				if (!VarWorld.isRemote) {
					EntityP.addChatMessage(new ChatComponentTranslation(
							"Research Written."));
					setInfo = "Currently Bound to: " + getPlayerName(IStack);
					if (MinecraftHelper.isClientSide())
					setToolTipData(IStack);
				}
			} else {
				sync: {
					List<String> researchesDone = ResearchManager
							.getResearchForPlayer(name);

					if (researchesDone == null) {
						if (VarWorld.isRemote)
							researchesDone = getPlayerResearch(IStack);
						else {
							EntityP.addChatMessage(new ChatComponentTranslation(
									"Research Aquired."));
							break sync;

						}
					}
					for (String key : researchesDone)
						tcproxy.completeResearch(EntityP, key);
				    if (!VarWorld.isRemote)
				        EntityP.addChatMessage(new ChatComponentTranslation("Research Aquired."));
				    
				    this.removeItem(EntityP, IStack);

				}

			}
		
		return IStack;
	}
	
	public void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for(int i=0; i < inv.getSizeInventory(); i++)
		{
			if(inv.getStackInSlot(i) != null)
			{
				ItemStack j = inv.getStackInSlot(i);
				if(j.getItem() != null && j.getItem() == removeitem.getItem() && ItemStack.areItemStackTagsEqual(j, removeitem))
				{
					inv.setInventorySlotContents(i, null);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		String name = getPlayerName(par1ItemStack);
		if (name.endsWith(NOT_ASSIGNED)) {
			return Color.HSBtoRGB(255, 0.25F, 1F);
		} else {
			return Color.HSBtoRGB(
					(System.currentTimeMillis() / 4) % 360 / 360F, 0.25F, 1F);

		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, EntityPlayer EntityP,
			@SuppressWarnings("rawtypes") List iList, boolean bool) {
		String name = getPlayerName(itemStack);
		iList.add(name.equals(NOT_ASSIGNED) ? String.format("Unbound") : String
				.format("Bound to" + name));
	}

	private String getPlayerName(ItemStack iStack) {
		return ItemNBTHandler.getString(iStack, TAG_PLAYER, NOT_ASSIGNED);
	}

	private void setPlayerName(ItemStack stack, String playerName) {
		ItemNBTHandler.setString(stack, TAG_PLAYER, playerName);
	}

	private void setPlayerResearch(ItemStack stack, String playername) {
		List<String> researchesDone = ResearchManager
				.getResearchForPlayer(playername);
		NBTTagCompound cmp = ItemNBTHandler.GetNBTData(stack);
		NBTTagList list = new NBTTagList();
		for (String tag : researchesDone) {
			list.appendTag(new NBTTagString(tag));
		}
		cmp.setTag("research", list);

	}

}
