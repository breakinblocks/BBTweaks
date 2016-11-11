package com.breakinblocks.bbtweaks.rituals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.api.alchemy.energy.ReagentRegistry;
import WayofTime.alchemicalWizardry.api.rituals.IMasterRitualStone;
import WayofTime.alchemicalWizardry.api.rituals.RitualComponent;
import WayofTime.alchemicalWizardry.api.rituals.RitualEffect;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellHelper;
import WayofTime.alchemicalWizardry.common.tileEntity.TEAltar;

public class RitualEffectCulling extends RitualEffect {
	DamageSource culled = new DamageSource("bbtweaks.absolute")
	.setDamageAllowedInCreativeMode().setDamageBypassesArmor()
	.setDamageIsAbsolute();
	
	public int reagentDrain = 2;
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

		player.worldObj.addWeatherEffect(new EntityLightningBolt(
				player.worldObj, xCoord + itemRand.nextInt(64) - 32, yCoord
				+ itemRand.nextInt(8) - 8, zCoord
				+ itemRand.nextInt(64) - 32));

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void performEffect(IMasterRitualStone ritualStone) {
		String owner = ritualStone.getOwner();
		World world = ritualStone.getWorld();
		int x = ritualStone.getXCoord();
		int y = ritualStone.getYCoord();
		Random random = new Random();
		int z = ritualStone.getZCoord();
		double ex, ey, ez;
		TEAltar tileAltar = null;
		boolean testFlag = false;

		int currentEssence = SoulNetworkHandler.getCurrentEssence(owner);
		if (world.getWorldTime() % RitualEffectCulling.timeDelay != 0) {
			return;
		}
		for (int i = -5; i <= 5; i++) {
			for (int j = -5; j <= 5; j++) {
				for (int k = -10; k <= 10; k++) {
					if (world.getTileEntity(x + i, y + k, z + j) instanceof TEAltar) {
						tileAltar = (TEAltar) world.getTileEntity(x + i, y + k,
								z + j);
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
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) x,
				(double) y, (double) z, (double) (x + 1), (double) (y + 1),
				(double) (z + 1)).expand(d0, vertRange, d0);
		List<EntityLivingBase> list = world.getEntitiesWithinAABB(
				EntityLivingBase.class, axisalignedbb);
		int entityCount = 0;
		if (currentEssence < this.getCostPerRefresh() * list.size()) {
			SoulNetworkHandler.causeNauseaToPlayer(owner);
		} else {
			for (EntityLivingBase livingEntity : list) {
				if (livingEntity instanceof IBossDisplayData
						|| AlchemicalWizardry.wellBlacklist
						.contains(livingEntity.getClass())) {
					continue;
				}
				if (livingEntity instanceof EntityPlayer
						&& livingEntity.getHealth() > 4)
					continue;

				ex = livingEntity.posX;
				ey = livingEntity.posY;
				ez = livingEntity.posZ;
				
				PotionEffect effect = livingEntity
						.getActivePotionEffect(Potion.damageBoost); // Cursed earth boosted

				if (this.canDrainReagent(ritualStone,
						ReagentRegistry.magicalesReagent, reagentDrain, true) || effect == null) { // needs to be able to consume up to 5 blocks per try
							int p = 0;
					for (p = 0; p < 6; p++)
						SpellHelper.sendParticleToAllAround(world, ex, ey, ez,
								30, world.provider.dimensionId, "lava", ex
								+ smallGauss(0.1D), ey + p / 10
								+ smallGauss(0.12D), ez
								+ smallGauss(0.1D), 0.5D, 0.5D, 0.5D);
					if (random.nextInt(100) < 20)
						world.playSoundEffect((double) x + 0.5D,
								(double) ey + 0.5D, (double) ez + 0.5D,
								"mob.ghast.scream", 0.2F,
								world.rand.nextFloat() * 0.1F + 0.9F);
				}

				if (SoulNetworkHandler.getPlayerForUsername(owner) != null) {
					/*
					 * if (livingEntity.attackEntityFrom(DamageSource
					 * .causePlayerDamage(SoulNetworkHandler
					 * .getPlayerForUsername(owner)), livingEntity
					 * .getMaxHealth() * 3)) <- I may implement this later
					 */
					
					if (livingEntity.attackEntityFrom(culled,
							livingEntity.getMaxHealth() * 2)) {
						entityCount++;
						if (effect == null)
							tileAltar.sacrificialDaggerCall(
									RitualEffectCulling.amount, true);
					}
				} else {
					if (livingEntity.attackEntityFrom(culled,
							livingEntity.getMaxHealth() * 2)) {
						entityCount++;
						if (effect == null)
							tileAltar.sacrificialDaggerCall(
									RitualEffectCulling.amount, true);
					}
				}
			}
			
			SoulNetworkHandler.syphonFromNetwork(owner, getCostPerRefresh()
					* entityCount);

		}

	}

	@Override
	public int getCostPerRefresh() {
		return 75;
	}

	public double smallGauss(double d) {
		Random myRand = new Random();
		return (myRand.nextFloat() - 0.5D) * d;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<RitualComponent> getRitualComponentList() {
		@SuppressWarnings("unchecked")
		ArrayList<RitualComponent> cullingRitual = new ArrayList();
		
		cullingRitual.add(new RitualComponent(1, 0, 1, RitualComponent.FIRE));
        cullingRitual.add(new RitualComponent(-1, 0, 1, RitualComponent.FIRE));
        cullingRitual.add(new RitualComponent(1, 0, -1, RitualComponent.FIRE));
        cullingRitual.add(new RitualComponent(-1, 0, -1, RitualComponent.FIRE));
        
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
