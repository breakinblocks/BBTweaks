package rocks.boltsandnuts.mindmeld.items;

/*
 * Class to register your blocks in.
 * The order that you list them here is the order they are registered.
 * Keep that in mind if you like nicely organized creative tabs.
 */

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemRegistry {

    //items
    public static Item meldingManuscript;

    public static void registerItems() {
        meldingManuscript = new ItemMeldingManuscript();
        GameRegistry.registerItem(meldingManuscript, "ItemMeldingManuscript");
    }
}
