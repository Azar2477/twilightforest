package twilightforest.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.BlockPos;
import twilightforest.entity.boss.KnightPhantomEntity;

import java.util.EnumSet;

import net.minecraft.world.entity.ai.goal.Goal.Flag;

public class PhantomAttackStartGoal extends Goal {

	private final KnightPhantomEntity boss;

	public PhantomAttackStartGoal(KnightPhantomEntity entity) {
		boss = entity;
		setFlags(EnumSet.of(Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		return boss.getTarget() != null && boss.getCurrentFormation() == KnightPhantomEntity.Formation.ATTACK_PLAYER_START;
	}

	@Override
	public void tick() {
		LivingEntity target = boss.getTarget();
		if (target != null) {
			BlockPos targetPos = new BlockPos(target.xOld, target.yOld, target.zOld);

			if (boss.isWithinRestriction(targetPos)) {
				boss.setChargePos(targetPos);
			} else {
				boss.setChargePos(boss.getRestrictCenter());
			}
		}
	}
}