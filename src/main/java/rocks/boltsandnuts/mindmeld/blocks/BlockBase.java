package rocks.boltsandnuts.mindmeld.blocks;

/*
 * Base block class for getting standard things done with quickly.
 * Extend this for pretty much every block you make.
 */

import rocks.boltsandnuts.mindmeld.MindMeld;
import rocks.boltsandnuts.mindmeld.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

    // If you aren't setting multiple textures for your block. IE: Non-Metadata blocks.
    public BlockBase(String unlocName, Material material, String textureName, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.ID + "." + unlocName);
        setBlockTextureName(ModInformation.ID + ":" + textureName);
        setCreativeTab(MindMeld.tabBaseMod);
        setStepSound(soundType);
        setHardness(hardness);
    }

    // If you are setting multiple textures for your block. IE: Metadata blocks.
    public BlockBase(String unlocName, Material material, SoundType soundType, float hardness) {
        super(material);

        setBlockName(ModInformation.ID + "." + unlocName);
        setCreativeTab(MindMeld.tabBaseMod);
        setStepSound(soundType);
        setHardness(hardness);
    }
}
