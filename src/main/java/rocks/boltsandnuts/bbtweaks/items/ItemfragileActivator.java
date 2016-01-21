package rocks.boltsandnuts.bbtweaks.items;

import java.util.List;

import WayofTime.alchemicalWizardry.common.items.ActivationCrystal;
import WayofTime.alchemicalWizardry.common.items.EnergyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rocks.boltsandnuts.bbtweaks.BBTweaks;
import rocks.boltsandnuts.bbtweaks.ModInformation;

public class ItemfragileActivator extends ActivationCrystal {

	@SideOnly(Side.CLIENT)
	private IIcon icon;
	private long timeToLive = 2400;

	public ItemfragileActivator() {
		super();
		setMaxStackSize(16);
		setUnlocalizedName("item." + ModInformation.ID + "." + "fragileActivator");
		setTextureName(ModInformation.ID + ":" + "fragileActivator");
		this.hasSubtypes = false;
		setCreativeTab(BBTweaks.tabBaseMod);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon(ModInformation.ID + ":" + "fragileActivator");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(StatCollector.translateToLocal("tooltip.activationcrystal.fragile"));

		if (!(par1ItemStack.getTagCompound() == null)) {
			par3List.add(StatCollector.translateToLocal("tooltip.owner.currentowner") + " "
					+ par1ItemStack.getTagCompound().getString("ownerName"));
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		long ttl;
		NBTTagCompound data = par1ItemStack.getTagCompound();

		EnergyItems.checkAndSetItemOwner(par1ItemStack, par3EntityPlayer);


		if (data == null) {
			par1ItemStack.setTagCompound(new NBTTagCompound());
			data = par1ItemStack.getTagCompound();
			data.setLong("timeToLive", this.timeToLive);
			ttl = data.getLong("timeToLive");
			if (ttl < 1)
				par1ItemStack.getTagCompound().setLong("timeToLive", this.timeToLive);
			par3EntityPlayer.addChatMessage(new ChatComponentTranslation(
					"The crystal has been activated, its energy is quickly diminishing and will soon shatter."));

		}
		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		NBTTagCompound data = par1ItemStack.getTagCompound();
		EntityPlayer player = (EntityPlayer) par3Entity;

		if (!(par3Entity instanceof EntityPlayer)) {
			return;
		}

		if (data != null) {
			long ttl = 0;
			ttl = data.getLong("timeToLive");
			if (ttl > 0 && (par1ItemStack.getTagCompound().getString("ownerName") != null)) {
				ttl--;
				par1ItemStack.getTagCompound().setLong("timeToLive", ttl);
			}
			if (ttl < 1) {
				player.addChatMessage(new ChatComponentTranslation("The activation crystal shatters."));
				this.removeItem(player, par1ItemStack);
			

			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return ("item." + ModInformation.ID + "." + "fragileActivator");
	}

	@Override
	public int getCrystalLevel(ItemStack itemStack) {
		return itemStack.getItemDamage() > 1 ? Integer.MAX_VALUE : itemStack.getItemDamage() + 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item id, CreativeTabs creativeTab, List list) {
		list.add(new ItemStack(id, 1, 0));
	}

	public void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem()
						&& ItemStack.areItemStackTagsEqual(j, removeitem)) {
					inv.setInventorySlotContents(i, null);
				}
			}
		}
	}

}
