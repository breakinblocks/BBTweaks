package com.breakinblocks.bbtweaks.blocks;

import com.breakinblocks.bbtweaks.BBTweaks;
import com.breakinblocks.bbtweaks.ModInformation;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.client.model.ModelLoader;

public class BlockBase extends Block implements IModeledBlock {
	public Item itemBlock = null;
	public boolean isOpaqueCube = true, isFullCube = true;
	public BlockRenderLayer layer = BlockRenderLayer.SOLID;

	// If you aren't setting multiple textures for your block. IE: Non-Metadata
	// blocks.
	public BlockBase(String unlocName, Material material, String textureName, SoundType soundType, float hardness) {
		super(material);

		setUnlocalizedName(ModInformation.ID + "." + unlocName);
		setCreativeTab(BBTweaks.tabBaseMod);
		setSoundType(soundType);
		setHardness(hardness);
	}

	// If you are setting multiple textures for your block. IE: Metadata blocks.
	public BlockBase(String unlocName, Material material, SoundType soundType, float hardness) {
		super(material);

		setUnlocalizedName(ModInformation.ID + "." + unlocName);
		setCreativeTab(BBTweaks.tabBaseMod);
		setSoundType(soundType);
		setHardness(hardness);
	}

	@Override
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(getRegistryName().toString(), "inventory"));

	}
}
