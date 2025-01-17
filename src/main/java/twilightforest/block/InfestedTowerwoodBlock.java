package twilightforest.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.server.level.ServerLevel;
import twilightforest.entity.TowerwoodBorerEntity;
import twilightforest.entity.TFEntities;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class InfestedTowerwoodBlock extends FlammableBlock {

	public InfestedTowerwoodBlock(int flammability, int spreadSpeed, Properties props) {
		super(flammability, spreadSpeed, props);
	}

	@Override
	@Deprecated
	public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		super.spawnAfterBreak(state, world, pos, stack);
		if (!world.isClientSide && world.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			TowerwoodBorerEntity termite = new TowerwoodBorerEntity(TFEntities.tower_termite, world);
			termite.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
			world.addFreshEntity(termite);
			termite.spawnAnim();
		}

	}
}
