package bernie.software.block;

import bernie.software.registry.DeepWatersBlocks;
import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;

import java.util.List;
import java.util.Random;

public class DeepWatersGrassBlock extends SpreadableSnowyDirtBlock {

    public DeepWatersGrassBlock(Properties properties) {
        super(properties);
    }

    public static boolean func_220257_b(BlockState p_220257_0_, IWorldReader p_220257_1_, BlockPos p_220257_2_) {
        BlockPos blockpos = p_220257_2_.up();
        BlockState blockstate = p_220257_1_.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else {
            int i = LightEngine.func_215613_a(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP, blockstate.getOpacity(p_220257_1_, blockpos));
            return i < p_220257_1_.getMaxLightLevel();
        }
    }

    public static boolean func_220256_c(BlockState p_220256_0_, IWorldReader p_220256_1_, BlockPos p_220256_2_) {
        return func_220257_b(p_220256_0_, p_220256_1_, p_220256_2_);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!func_220257_b(state, worldIn, pos)) {
                worldIn.setBlockState(pos, DeepWatersBlocks.OCEAN_FLOOR.get().getDefaultState());
            } else {
                if (worldIn.getLight(pos.up()) >= 0) {
                    BlockState blockstate = this.getDefaultState();

                    for(int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == DeepWatersBlocks.OCEAN_FLOOR.get() && func_220256_c(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate);
                        }
                    }
                }

            }
        }
    }

}
