package bernie.software.datagen.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Supplier;

public class DeepWatersRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    public DeepWatersRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public ShapedRecipeBuilder makeNuggetToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
        return ShapedRecipeBuilder.shapedRecipe(ingotOut.get(), 1)
                .patternLine("NNN")
                .patternLine("NNN")
                .patternLine("NNN")
                .key('N', nuggetIn.get())
                .addCriterion("has_" + nuggetIn.get().getRegistryName().getPath(), hasItem(nuggetIn.get()));
    }

    public ShapelessRecipeBuilder makeIngotToNugget(Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(nuggetOut.get(), 9)
                .addIngredient(ingotIn.get())
                .addCriterion("has_" + ingotIn.get().getRegistryName().getPath(), hasItem(ingotIn.get()));
    }

    public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Block> blockOut, Supplier<? extends Item> ingotIn) {
        return ShapedRecipeBuilder.shapedRecipe(blockOut.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingotIn.get())
                .addCriterion("has_" + ingotIn.get().getRegistryName().getPath(), hasItem(ingotIn.get()));
    }

    public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapelessRecipe(ingotOut.get())
                .addIngredient(blockIn.get(), 9)
                .addCriterion("has_" + blockIn.get().getRegistryName().getPath(), hasItem(blockIn.get()));
    }

    public ShapedRecipeBuilder makeSword(Supplier<? extends Item> swordOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(swordOut.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', materialIn.get())
                .key('/', Items.STICK)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makePickaxe(Supplier<? extends Item> pickaxeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(pickaxeOut.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', materialIn.get())
                .key('/', Items.STICK)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeAxe(Supplier<? extends Item> axeOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(axeOut.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', materialIn.get())
                .key('/', Items.STICK)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

    public ShapedRecipeBuilder makeShovel(Supplier<? extends Item> shovelOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shapedRecipe(shovelOut.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', materialIn.get())
                .key('/', Items.STICK)
                .addCriterion("has_" + materialIn.get().getRegistryName().getPath(), hasItem(materialIn.get()));
    }

}
