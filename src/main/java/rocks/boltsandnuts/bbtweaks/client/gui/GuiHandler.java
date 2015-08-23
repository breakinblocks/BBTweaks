package rocks.boltsandnuts.bbtweaks.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import rocks.boltsandnuts.bbtweaks.BBTweaks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/*      if (ID == 0) {
            MindMeld.logger.info("Open Gui");
            return new ItemGuiBaseBook();
        }*/
        return null;
    }
}
