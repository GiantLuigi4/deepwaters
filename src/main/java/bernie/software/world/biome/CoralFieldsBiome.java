package bernie.software.world.biome;

import bernie.software.registry.DeepWatersBiomeFeatures;
import bernie.software.registry.DeepWatersBiomes;
import bernie.software.registry.DeepWatersEntities;
import bernie.software.registry.DeepWatersWorldCarvers;
import bernie.software.surfacebuilder.CoralFieldsSurfaceBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CoralFieldsBiome extends WaterBiomeBase
{
	public CoralFieldsBiome()
	{
		super((new Biome.Builder()).surfaceBuilder(new CoralFieldsSurfaceBuilder(SurfaceBuilderConfig::deserialize), new SurfaceBuilderConfig(Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState())).precipitation(Biome.RainType.NONE).category(Category.OCEAN).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).waterColor(42892).waterFogColor(42892));

		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SEAGRASS, new SeaGrassConfig(48, 0.3D), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SingleRandomFeature(new Feature[]{Feature.CORAL_TREE, Feature.CORAL_CLAW, Feature.CORAL_MUSHROOM}, new IFeatureConfig[]{IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG, IFeatureConfig.NO_FEATURE_CONFIG}), Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(20, 400.0D, 1.0D, Heightmap.Type.OCEAN_FLOOR_WG)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SEA_PICKLE, new CountConfig(20), Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(16)));
		DefaultBiomeFeatures.addKelp(this);
		DeepWatersBiomeFeatures.addDeepWatersOres(this);
		DeepWatersBiomeFeatures.addSedimentDisks(this);
		DeepWatersBiomeFeatures.addStoneVariants(this);
	}

	public void addWorldCarvers()
	{
		WorldCarver<ProbabilityConfig> carver = DeepWatersWorldCarvers.CORAL_CAVE_CARVER.get();
		this.addCarver(GenerationStage.Carving.AIR, createCarver(carver, new ProbabilityConfig(15F)));
	}

	public void addSpawns()
	{
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.DONUT_FISH.get(), 3, 75, 100));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.DONUT_FISH.get(), 30, 5, 20));

		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.BLUFFERFISH.get(), 30, 4, 10));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.KILLER_WIGGLER.get(), 1, 1, 1));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(EntityType.SALMON, 30, 5, 10));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(EntityType.TROPICAL_FISH, 25, 6, 12));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.BABY_KRACKEN.get(), 2, 1, 2));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.STING_RAY.get(), 4, 1, 4));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.MUCK_GULPER.get(), 30, 1, 10));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.DONUT_FISH.get(), 30, 5, 20));
		addWaterPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.COLORFUL_FISH.get(), 30, 6, 20));

		addWaterLandPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.CORAL_CRAWLER.get(), 100, 1, 4));
		addWaterLandPassiveCreatureSpawn(new Biome.SpawnListEntry(DeepWatersEntities.CLAM.get(), 12, 1, 2));

	}
}
