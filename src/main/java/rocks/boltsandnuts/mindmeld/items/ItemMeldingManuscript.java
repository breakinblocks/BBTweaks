package rocks.boltsandnuts.mindmeld.items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import rocks.boltsandnuts.mindmeld.util.NBTHandlers.ItemNBTHandler;
import thaumcraft.common.lib.research.ResearchManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import rocks.boltsandnuts.mindmeld.util.TextHelper;

/**
 * The overhauled sharing tome.
 */
@SuppressWarnings("unused")
public class ItemMeldingManuscript extends ItemBase {

    private static final String TAG_PLAYER = "player";
    private static final String NOT_ASSIGNED = "[none]";
    
    public ItemMeldingManuscript() {
        super("melding.manuscript", "meldingManuscript");
        setMaxStackSize(1);
        this.setColor=TextHelper.PURPLE;
        this.setInfo="Right click to Bind";
        //addInformation(null, null, null, false);
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
    public ItemStack onItemRightClick(ItemStack IStack, World VarWorld, EntityPlayer EntityP) {
        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            String name = NOT_ASSIGNED;
            setPlayerName(IStack, name);
            if (!VarWorld.isRemote)
            EntityP.addChatMessage(new ChatComponentTranslation("The Manuscript is now unbound."));
        }
        else {
        String name = getPlayerName(IStack);
        if (name.endsWith(NOT_ASSIGNED)) {
            setPlayerName(IStack, EntityP.getGameProfile().getName());
            //setPlayerResearch(IStack, EntityP.getGameProfile().getName());
            /*if (!VarWorld.isRemote)
                EntityP.addChatMessage(new ChatComponentTranslation("ttmisc.shareTome.write"));
        } else sync:{
            List<String> researchesDone = ResearchManager.getResearchForPlayer(name);

            if (researchesDone == null) {
                if (VarWorld.isRemote)
                    researchesDone = getPlayerResearch(IStack);
                else {
                    EntityP.addChatMessage(new ChatComponentTranslation("ttmisc.shareTome.sync"));
                    break sync;

                }
            }*/
            if (!VarWorld.isRemote)
            EntityP.addChatMessage(new ChatComponentTranslation("The Manuscript is now bound to you."));
            setInfo="Currently Bound to: " + getPlayerName(IStack);
            setToolTipData(IStack);
        }
        }
        return IStack;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer EntityP, @SuppressWarnings("rawtypes") List iList, boolean bool) {
        String name = getPlayerName(itemStack);
        iList.add(name.equals(NOT_ASSIGNED) ? String.format("Unbound") : String.format("Bound to" + name));
    }

    
    private String getPlayerName(ItemStack iStack) {
         return ItemNBTHandler.getString(iStack, TAG_PLAYER, NOT_ASSIGNED);
    }

    private void setPlayerName(ItemStack stack, String playerName) {
        ItemNBTHandler.setString(stack, TAG_PLAYER, playerName);
    }
}
