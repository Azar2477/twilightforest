package twilightforest.world.components.structures;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import twilightforest.world.registration.TFFeature;
import twilightforest.block.TFBlocks;

import java.util.Random;

public class HydraLairComponent extends HollowHillComponent {

	public HydraLairComponent(ServerLevel level, CompoundTag nbt) {
		super(TFFeature.TFHydra, nbt);
	}

	public HydraLairComponent(TFFeature feature, Random rand, int i, int x, int y, int z) {
		super(TFFeature.TFHydra, feature, i, 2, x, y + 2, z);
	}

	@Override
	public void addChildren(StructurePiece structurecomponent, StructurePieceAccessor accessor, Random random) {
		// NO-OP
	}

	@Override
	public boolean postProcess(WorldGenLevel world, StructureFeatureManager manager, ChunkGenerator generator, Random rand, BoundingBox sbb, ChunkPos chunkPosIn, BlockPos blockPos) {
		int stalacts = 64;
		int stalags = 8;

		// fill in features
		// ore or glowing stalactites! (smaller, less plentiful)
		for (int i = 0; i < stalacts; i++) {
			BlockPos.MutableBlockPos dest = this.randomCeilingCoordinates(rand, this.radius);
			this.generateOreStalactite(world, dest.move(0, 1, 0), sbb);
		}
		// stone stalactites!
		for (int i = 0; i < stalacts; i++) {
			BlockPos.MutableBlockPos dest = this.randomCeilingCoordinates(rand, this.radius);
			this.generateBlockSpike(world, STONE_STALACTITE, dest.getX(), dest.getY(), dest.getZ(), sbb);
		}
		// stone stalagmites!
		for (int i = 0; i < stalags; i++) {
			BlockPos.MutableBlockPos dest = this.randomFloorCoordinates(rand, this.radius);
			this.generateBlockSpike(world, STONE_STALAGMITE, dest.getX(), dest.getY(), dest.getZ(), sbb);
		}

		// boss spawner seems important
		placeBlock(world, TFBlocks.boss_spawner_hydra.get().defaultBlockState(), 27, 3, 27, sbb);

		return true;
	}
}
