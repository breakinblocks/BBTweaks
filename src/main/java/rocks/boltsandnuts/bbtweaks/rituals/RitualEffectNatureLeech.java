package rocks.boltsandnuts.bbtweaks.rituals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import WayofTime.alchemicalWizardry.api.alchemy.energy.ReagentRegistry;
import WayofTime.alchemicalWizardry.api.rituals.IMasterRitualStone;
import WayofTime.alchemicalWizardry.api.rituals.RitualComponent;
import WayofTime.alchemicalWizardry.api.rituals.RitualEffect;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellHelper;
import WayofTime.alchemicalWizardry.common.tileEntity.TEAltar;

public class RitualEffectNatureLeech extends RitualEffect {
	public int reagentDrain = 2;
	public static final int timeDelay = 30;

	@Override
	public int getCostPerRefresh() {
		return 50;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RitualComponent> getRitualComponentList() {
		@SuppressWarnings("rawtypes")
		ArrayList<RitualComponent> ritualBlocks = new ArrayList();
		ritualBlocks.add(new RitualComponent(-3, 0, 0, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(-2, 0, -2, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(-2, 0, 2, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(-2, 1, -2, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(-2, 1, 0, RitualComponent.AIR));
		ritualBlocks.add(new RitualComponent(-2, 1, 2, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(-1, 0, -1, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(-1, 0, 1, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(0, 0, -3, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(0, 0, 3, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(0, 1, -2, RitualComponent.AIR));
		ritualBlocks.add(new RitualComponent(0, 1, 2, RitualComponent.AIR));
		ritualBlocks.add(new RitualComponent(1, 0, -1, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(1, 0, 1, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(2, 0, -2, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(2, 0, 2, RitualComponent.EARTH));
		ritualBlocks.add(new RitualComponent(2, 1, -2, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(2, 1, 0, RitualComponent.AIR));
		ritualBlocks.add(new RitualComponent(2, 1, 2, RitualComponent.WATER));
		ritualBlocks.add(new RitualComponent(3, 0, 0, RitualComponent.EARTH));
		return ritualBlocks;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplay(World world, int x, int y, int z, Random random) {
		float f1 = (float) x + 0.5f;
		float f2 = (float) y + 1.1f;
		float f3 = (float) z + 0.5f;
		float f4 = random.nextFloat() * 0.6F - 0.3F;
		float f5 = random.nextFloat() * -0.6F - -0.3F;
		world.spawnParticle("smoke", (double) f1 + f4, (double) f2, (double) f3
				+ f5, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("lava", (double) f1 + f4, (double) f2, (double) f3
				+ f5, 0.5D, 0.9D, 0.3D);

	}

	@Override
	public void performEffect(IMasterRitualStone ritualStone) {
		String owner = ritualStone.getOwner();
		Random random = new Random();
		World world = ritualStone.getWorld();
		int x = ritualStone.getXCoord();
		int y = ritualStone.getYCoord();
		int z = ritualStone.getZCoord();
		int radius = 3;
		int currentEssence = SoulNetworkHandler.getCurrentEssence(owner);
		TEAltar tileAltar = new TEAltar();

		if (world.getWorldTime() % RitualEffectNatureLeech.timeDelay != 0) { // Don't want to call
			// this too often
			return;
		}

		if (currentEssence < this.getCostPerRefresh()) {
			EntityPlayer entityOwner = SpellHelper.getPlayerForUsername(owner);
			if (entityOwner == null) {
				return;
			}
			SoulNetworkHandler.causeNauseaToPlayer(owner);
		} else {
			if (this.canDrainReagent(ritualStone,
					ReagentRegistry.terraeReagent, reagentDrain * 5, false)) { // needs to be able to consume up to 5 blocks per try

				radius = 5;
			}
			int eaten = 0;
			int max = 100;
			 SoulNetworkHandler.syphonFromNetwork(owner, getCostPerRefresh());
			for (int eat = 0; eat < 5; eat++) {
				if (eat > max)
					break;//little sanity checking
				int[] pos = getNextBlock(world, x, z, radius, ritualStone);

				if (pos != null) {
					if (random.nextInt(100) < 20) {
						if (!world.isRemote)
							this.randomDisplay(world, x, y, z, random);
						world.setBlockToAir(pos[0], pos[1], pos[2]);
						eaten++;
						if (radius == 5)
							this.canDrainReagent(ritualStone,
									ReagentRegistry.terraeReagent,
									reagentDrain, true);

						boolean testFlag = false;
						//Lets find an altar!
						for (int i = -10; i <= 10; i++) {
							for (int j = -10; j <= 10; j++) {
								for (int k = -10; k <= 10; k++) {
									if (world.getTileEntity(x + i, y + k, z + j) instanceof TEAltar) {
										tileAltar = (TEAltar) world.getTileEntity(x + i, y + k, z + j);
										testFlag = true;
									}
								}
							}
						}
						if (!testFlag) {
							return; //No altar in range, abandon ship!
						}
						else
						{
							if (SoulNetworkHandler.getPlayerForUsername(owner) != null && tileAltar != null){
									tileAltar.sacrificialDaggerCall(eaten*15, true);
							}
						} 
					

				} else // This block wasn't eaten
				{
					eat--;
				}

			}
		}

	}

}

public int[] getNextBlock(World world, int ritualX, int ritualZ,
		int radius, IMasterRitualStone ritualStone) {
	int startChunkX = ritualX >> 4;
								int startChunkZ = ritualZ >> 4;
						double stoneY = 64;

						if (ritualStone != null)
							stoneY = ritualStone.getYCoord()-10;//works up to 10 levels below the MRS

						IChunkProvider provider = world.getChunkProvider();
						for (int chunkX = startChunkX - radius; chunkX <= startChunkX + radius; chunkX++) {
							for (int chunkZ = startChunkZ - radius; chunkZ <= startChunkZ
									+ radius; chunkZ++) {
								provider.loadChunk(chunkX, chunkZ);
								for (int x = 0; x < 16; x++) {
									for (int z = 0; z < 16; z++) {
										for (int y = (int) stoneY; y < (int) stoneY + 32; y++) { // works up to 32 above the MRS
											int wx = chunkX * 16 + x;
											int wz = chunkZ * 16 + z;
											Block thisBlock = world.getBlock(wx, y, wz);

											if (thisBlock instanceof BlockCrops
													|| thisBlock instanceof BlockLog
													|| thisBlock instanceof BlockLeaves
													|| thisBlock instanceof BlockFlower
													|| thisBlock instanceof BlockTallGrass
													|| thisBlock instanceof BlockDoublePlant) // found
												// a
												// plant

												return new int[] { wx, y, wz };
										}
									}
								}
							}
						}
						return null;
}

}
