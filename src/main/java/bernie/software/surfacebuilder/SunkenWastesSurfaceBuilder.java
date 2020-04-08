package bernie.software.surfacebuilder;

import bernie.software.registry.DeepWatersBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class SunkenWastesSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
	private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
	private static final BlockState MOSSY_OCEAN_FLOOR = DeepWatersBlocks.MOSSY_OCEAN_FLOOR.get().getDefaultState();
	private static final BlockState OCEAN_FLOOR = DeepWatersBlocks.SUNKEN_GRAVEL.get().getDefaultState();
	private static final BlockState GRAVEL = DeepWatersBlocks.SUNKEN_GRAVEL.get().getDefaultState();
	protected long seed;
	protected OctavesNoiseGenerator randomNoiseGenerator;

	public SunkenWastesSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51308_1_) {
		super(p_i51308_1_);
	}

	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		int i = seaLevel + 1;
		int j = x & 15;
		int k = z & 15;
		double d0 = 0.03125D;
		boolean flag = this.randomNoiseGenerator.func_205563_a((double)x * 0.03125D, (double)z * 0.03125D, 0.0D) + random.nextDouble() * 0.2D > 0.0D;
		boolean flag1 = this.randomNoiseGenerator.func_205563_a((double)x * 0.03125D, 109.0D, (double)z * 0.03125D) + random.nextDouble() * 0.2D > 0.0D;
		int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int i1 = -1;
		BlockState blockstate = OCEAN_FLOOR;
		BlockState blockstate1 = OCEAN_FLOOR;
		boolean aboveBlock = false;

		for(int j1 = 256; j1 >= 0; --j1) {
			blockpos$mutableblockpos.setPos(j, j1, k);
			BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
			if (blockstate2.getBlock() != null && !blockstate2.isAir()) {
				if (blockstate2.getBlock() == defaultBlock.getBlock()) {
					if (i1 == -1) {
						if (l <= 0) {
							blockstate = CAVE_AIR;
							if(!aboveBlock)
							{
								blockstate1 = GRAVEL;
								aboveBlock = true;
							}
							else {
								blockstate1 = OCEAN_FLOOR;
								aboveBlock = false;

							}
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							blockstate = OCEAN_FLOOR;
							if(!aboveBlock) {
								blockstate1 = GRAVEL;
								aboveBlock = true;

							}
							else {
								blockstate1 = OCEAN_FLOOR;
								aboveBlock = false;

							}
							if (flag1) {
								blockstate = GRAVEL;
								if(!aboveBlock) {
									blockstate1 = MOSSY_OCEAN_FLOOR;
									aboveBlock = true;

								}
								else {
									blockstate1 = OCEAN_FLOOR;
									aboveBlock = false;
								}
							}

							if (flag) {
								blockstate = GRAVEL;
								blockstate1 = GRAVEL;
							}
						}

						if (j1 < i && (blockstate == null || blockstate.isAir())) {
							blockstate = defaultFluid;
						}

						i1 = l;
						if (j1 >= i - 1) {
							chunkIn.setBlockState(blockpos$mutableblockpos, blockstate, false);
						} else {
							chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
						}
					} else if (i1 > 0) {
						--i1;
						chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
					}
				}
			} else {
				i1 = -1;
			}
		}

	}

	public void setSeed(long seed) {
		if (this.seed != seed || this.randomNoiseGenerator == null) {
			this.randomNoiseGenerator = new OctavesNoiseGenerator(new SharedSeedRandom(seed), 4);
		}

		this.seed = seed;
	}
}