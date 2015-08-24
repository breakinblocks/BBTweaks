package rocks.boltsandnuts.bbtweaks.rituals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.api.rituals.IMasterRitualStone;
import WayofTime.alchemicalWizardry.api.rituals.RitualComponent;
import WayofTime.alchemicalWizardry.api.rituals.RitualEffect;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.entity.projectile.LightningBoltProjectile;
import WayofTime.alchemicalWizardry.common.tileEntity.TEAltar;

public class RitualEffectCulling extends RitualEffect {
	DamageSource culled = new DamageSource("bbtweaks.absolute")
	.setDamageAllowedInCreativeMode().setDamageBypassesArmor()
	.setDamageIsAbsolute();

	public static final int timeDelay = 20;
	public static final int amount = 200;

	@Override
	public boolean startRitual(IMasterRitualStone ritualStone,
			EntityPlayer player) {
		Random itemRand = new Random();
		double xCoord, yCoord, zCoord;

			
		xCoord = ritualStone.getXCoord();
		yCoord = ritualStone.getYCoord();
		zCoord = ritualStone.getZCoord();
		
		player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, xCoord + itemRand.nextInt(64) - 32, yCoord + itemRand.nextInt(8) - 8, zCoord + itemRand.nextInt(64) - 32));


		
		return true;
	}

	
	@SideOnly(Side.CLIENT)
	public void genParticle(World world, int x, int y, int z, Random random) {
		float f1 = (float) x + 0.5f;
		float f2 = (float) y + 1.1f;
		float f3 = (float) z + 0.5f;

		
        EntityFX fx = Minecraft.getMinecraft().renderGlobal.doSpawnParticle("lava", f1, f2, f3, 0.0D, 0.0D, 0.0D);
        EntityFX fx2 = Minecraft.getMinecraft().renderGlobal.doSpawnParticle("lava", f1, f2+.5, f3, 0.0D, 0.0D, 0.0D);
        EntityFX fx3 = Minecraft.getMinecraft().renderGlobal.doSpawnParticle("lava", f1, f2+2, f3, 0.0D, 0.0D, 0.0D);
        if(fx != null) {
          fx.setRBGColorF(0.2f, 0.8f, 0.4f);
          fx.motionY *= 0.5f;         
        }
        if(fx2 != null) {
            fx2.setRBGColorF(0.2f, 0.8f, 0.4f);
            fx2.motionY *= 0.5f;         
          }
        if(fx3 != null) {
            fx3.setRBGColorF(0.2f, 0.8f, 0.4f);
            fx3.motionY *= 0.5f;         
          }
        if (random.nextInt(100)<20)
        world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "mob.ghast.scream", 0.2F, world.rand.nextFloat() * 0.1F + 0.9F);
	}
	
	@Override
	public void performEffect(IMasterRitualStone ritualStone) {
		String owner = ritualStone.getOwner();
		World world = ritualStone.getWorld();
		int x = ritualStone.getXCoord();
		int y = ritualStone.getYCoord();
		Random random = new Random();
		int z = ritualStone.getZCoord();
		TEAltar tileAltar = null;
		boolean testFlag = false;
		int currentEssence = SoulNetworkHandler.getCurrentEssence(owner);
		if (world.getWorldTime() % this.timeDelay != 0) {
			return;
		}
		for (int i = -5; i <= 5; i++) {
			for (int j = -5; j <= 5; j++) {
				for (int k = -10; k <= 10; k++) {
					if (world.getTileEntity(x + i, y + k, z + j) instanceof TEAltar) {
						tileAltar = (TEAltar) world.getTileEntity(x + i, y + k, z + j);
						testFlag = true;
					}
				}
			}
		}
		if (!testFlag) {
			return;
		}
		int d0 = 10;
		int vertRange = 10;
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1)).expand(d0, vertRange, d0);
		List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
		int entityCount = 0;
		if (currentEssence < this.getCostPerRefresh() * list.size()) {
			SoulNetworkHandler.causeNauseaToPlayer(owner);
		} else {
			for (EntityLivingBase livingEntity : list) {
				if (livingEntity instanceof IBossDisplayData || AlchemicalWizardry.wellBlacklist.contains(livingEntity.getClass())) {
					continue;
				}
				if (livingEntity instanceof EntityPlayer && livingEntity.getHealth() > 4)
						continue;
				
				
				this.genParticle(world, (int)livingEntity.posX, (int)livingEntity.posY, (int)livingEntity.posZ, random);
		        
		
				
				if (SoulNetworkHandler.getPlayerForUsername(owner) != null ) {
					if (livingEntity.attackEntityFrom(culled.causePlayerDamage(SoulNetworkHandler.getPlayerForUsername(owner)), livingEntity.getMaxHealth() * 3)) {
						if (livingEntity.getActivePotionEffects() == null)
							entityCount++;
						tileAltar.sacrificialDaggerCall(this.amount, true);
					}
				} else {
					if (livingEntity.attackEntityFrom(culled, livingEntity.getMaxHealth() * 2)) {
						entityCount++;
						if (livingEntity.getActivePotionEffects() == null)
						tileAltar.sacrificialDaggerCall(this.amount, true);
					}
				}
			}
			SoulNetworkHandler.syphonFromNetwork(owner, getCostPerRefresh());
			
		}
		
	}

	@Override
	public int getCostPerRefresh() {
		return 75;
	}

	@Override
	public List<RitualComponent> getRitualComponentList() {
		ArrayList<RitualComponent> cullingRitual = new ArrayList();
		cullingRitual.add(new RitualComponent(1, 0, 1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-1, 0, 1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(1, 0, -1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-1, 0, -1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(2, -1, 2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(2, -1, -2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-2, -1, 2, RitualComponent.DUSK));
		cullingRitual
		.add(new RitualComponent(-2, -1, -2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(0, -1, 2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(2, -1, 0, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(0, -1, -2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-2, -1, 0, RitualComponent.DUSK));
		cullingRitual
		.add(new RitualComponent(-3, -1, -3, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(3, -1, -3, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-3, -1, 3, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(3, -1, 3, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(2, -1, 4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(4, -1, 2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-2, -1, 4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(4, -1, -2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(2, -1, -4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-4, -1, 2, RitualComponent.DUSK));
		cullingRitual
		.add(new RitualComponent(-2, -1, -4, RitualComponent.DUSK));
		cullingRitual
		.add(new RitualComponent(-4, -1, -2, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(1, 0, 4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(4, 0, 1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(1, 0, -4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-4, 0, 1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-1, 0, 4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(4, 0, -1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-1, 0, -4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-4, 0, -1, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(4, 1, 0, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(0, 1, 4, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(-4, 1, 0, RitualComponent.DUSK));
		cullingRitual.add(new RitualComponent(0, 1, -4, RitualComponent.DUSK));
		return cullingRitual;
	}
}
