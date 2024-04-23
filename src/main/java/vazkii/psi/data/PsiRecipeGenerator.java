/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.data;

import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import vazkii.psi.common.Psi;
import vazkii.psi.common.block.base.ModBlocks;
import vazkii.psi.common.crafting.recipe.*;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibItemNames;
import vazkii.psi.common.lib.ModTags;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class PsiRecipeGenerator extends RecipeProvider implements IConditionBuilder {

	public PsiRecipeGenerator(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		specialRecipe(AssemblyScavengeRecipe.SERIALIZER, consumer);
		specialRecipe(BulletToDriveRecipe.SERIALIZER, consumer);
		specialRecipe(ColorizerChangeRecipe.SERIALIZER, consumer);
		specialRecipe(DriveDuplicateRecipe.SERIALIZER, consumer);
		specialRecipe(SensorAttachRecipe.SERIALIZER, consumer);
		specialRecipe(SensorRemoveRecipe.SERIALIZER, consumer);

		CriterionTriggerInstance hasIron = has(Tags.Items.INGOTS_IRON);
		CriterionTriggerInstance hasPsimetal = has(ModTags.INGOT_PSIMETAL);
		CriterionTriggerInstance hasEbonyPsimetal = has(ModTags.INGOT_EBONY_PSIMETAL);
		CriterionTriggerInstance hasIvoryPsimetal = has(ModTags.INGOT_IVORY_PSIMETAL);
		CriterionTriggerInstance hasPsidust = has(ModTags.PSIDUST);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.cadAssembler)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('P', Items.PISTON)
				.pattern("IPI")
				.pattern("I I")
				.pattern(" I ")
				.unlockedBy("has_iron", hasIron)
				.save(consumer, Psi.location("assembler"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.programmer)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.pattern("IDI")
				.pattern("I I")
				.pattern(" I ")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("programmer"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ebonyPsimetal)
				.define('S', ModTags.EBONY_SUBSTANCE)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern("SSS")
				.pattern("SIS")
				.pattern("SSS")
				.unlockedBy("has_ebony_substance", has(ModItems.ebonySubstance))
				.save(consumer, Psi.location("ebony_psimetal"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ivoryPsimetal)
				.define('S', ModTags.IVORY_SUBSTANCE)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern("SSS")
				.pattern("SIS")
				.pattern("SSS")
				.unlockedBy("has_ivory_substance", has(ModItems.ivorySubstance))
				.save(consumer, Psi.location("ivory_psimetal"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadAssemblyIron)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("III")
				.pattern("I  ")
				.unlockedBy("has_iron", hasIron)
				.save(consumer, Psi.location("cad_assembly_iron"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadAssemblyGold)
				.define('I', Tags.Items.INGOTS_GOLD)
				.pattern("III")
				.pattern("I  ")
				.unlockedBy("has_gold", has(Tags.Items.INGOTS_GOLD))
				.save(consumer, Psi.location("cad_assembly_gold"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadAssemblyPsimetal)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern("III")
				.pattern("I  ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_assembly_psimetal"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadAssemblyEbony)
				.define('I', ModTags.INGOT_EBONY_PSIMETAL)
				.pattern("III")
				.pattern("I  ")
				.unlockedBy("has_ebony_psimetal", hasEbonyPsimetal)
				.save(consumer, Psi.location("cad_assembly_ebony"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadAssemblyIvory)
				.define('I', ModTags.INGOT_IVORY_PSIMETAL)
				.pattern("III")
				.pattern("I  ")
				.unlockedBy("has_ivory_psimetal", hasIvoryPsimetal)
				.save(consumer, Psi.location("cad_assembly_ivory"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadCoreBasic)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.pattern(" I ")
				.pattern("IDI")
				.pattern(" I ")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("cad_core_basic"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadCoreOverclocked)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_REDSTONE)
				.pattern(" I ")
				.pattern("IDI")
				.pattern(" I ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_core_overclocked"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadCoreConductive)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_GLOWSTONE)
				.pattern(" I ")
				.pattern("IDI")
				.pattern(" I ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_core_conductive"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadCoreHyperClocked)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_REDSTONE)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern(" G ")
				.pattern("IDI")
				.pattern(" G ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_core_hyperclocked"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadCoreRadiative)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_GLOWSTONE)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern(" G ")
				.pattern("IDI")
				.pattern(" G ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_core_radiative"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadSocketBasic)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.pattern("DI ")
				.pattern("I  ")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("cad_socket_basic"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadSocketSignaling)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_REDSTONE)
				.pattern("DI ")
				.pattern("I  ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_socket_signaling"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadSocketLarge)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_GLOWSTONE)
				.pattern("DI ")
				.pattern("I  ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_socket_large"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadSocketTransmissive)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_REDSTONE)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern("DI ")
				.pattern("IG ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_socket_transmissive"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadSocketHuge)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('D', Tags.Items.DUSTS_GLOWSTONE)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern("DI ")
				.pattern("IG ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_socket_huge"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadBatteryBasic)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('G', Tags.Items.INGOTS_GOLD)
				.pattern("I")
				.pattern("D")
				.pattern("G")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("cad_battery_basic"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadBatteryExtended)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.INGOT_PSIMETAL)
				.define('G', Tags.Items.INGOTS_GOLD)
				.pattern("I")
				.pattern("D")
				.pattern("G")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_battery_extended"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadBatteryUltradense)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.GEM_PSIGEM)
				.define('G', Tags.Items.INGOTS_GOLD)
				.pattern("I")
				.pattern("D")
				.pattern("G")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("cad_battery_ultradense"));

		for(DyeColor color : DyeColor.values()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ForgeRegistries.ITEMS.getValue(Psi.location(LibItemNames.CAD_COLORIZER + color.getSerializedName())))
					.group("psi:colorizer")
					.define('D', ModTags.PSIDUST)
					.define('I', Tags.Items.INGOTS_IRON)
					.define('G', Tags.Items.GLASS)
					.define('C', color.getTag())
					.pattern(" D ")
					.pattern("GCG")
					.pattern(" I ")
					.unlockedBy("has_psidust", hasPsidust)
					.save(consumer, Psi.location(LibItemNames.CAD_COLORIZER + color.getSerializedName()));
		}

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadColorizerRainbow)
				.group("psi:colorizer")
				.define('D', ModTags.PSIDUST)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('G', Tags.Items.GLASS)
				.define('C', Tags.Items.GEMS_PRISMARINE)
				.pattern(" D ")
				.pattern("GCG")
				.pattern(" I ")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("cad_colorizer_rainbow"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadColorizerPsi)
				.group("psi:colorizer")
				.define('D', ModTags.PSIDUST)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('G', Tags.Items.GLASS)
				.define('C', ModTags.PSIDUST)
				.pattern(" D ")
				.pattern("GCG")
				.pattern(" I ")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("cad_colorizer_psi"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.spellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.pattern("ID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_basic"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.projectileSpellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('A', ItemTags.ARROWS)
				.pattern("AID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_projectile"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.projectileSpellBullet)
				.requires(ModItems.spellBullet)
				.requires(ItemTags.ARROWS)
				.unlockedBy("has_psidust", has(ModItems.psidust))
				.save(WrapperResult.ofType(BulletUpgradeRecipe.SERIALIZER, consumer), Psi.location("spell_bullet_projectile_upgrade"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.loopSpellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('A', Tags.Items.STRING)
				.pattern("AID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_loopcast"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.loopSpellBullet)
				.requires(ModItems.spellBullet)
				.requires(Tags.Items.STRING)
				.unlockedBy("has_psidust", has(ModItems.psidust))
				.save(WrapperResult.ofType(BulletUpgradeRecipe.SERIALIZER, consumer), Psi.location("spell_bullet_loopcast_upgrade"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.circleSpellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('A', Ingredient.fromValues(Stream.of(
						new Ingredient.TagValue(Tags.Items.SLIMEBALLS),
						new Ingredient.ItemValue(new ItemStack(Items.SNOWBALL)))))
				.pattern("AID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_circle"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.circleSpellBullet)
				.requires(ModItems.spellBullet)
				.requires(Ingredient.fromValues(Stream.of(
						new Ingredient.TagValue(Tags.Items.SLIMEBALLS),
						new Ingredient.ItemValue(new ItemStack(Items.SNOWBALL)))))
				.unlockedBy("has_psidust", has(ModItems.psidust))
				.save(WrapperResult.ofType(BulletUpgradeRecipe.SERIALIZER, consumer), Psi.location("spell_bullet_circle_upgrade"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.grenadeSpellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('A', Tags.Items.GUNPOWDER)
				.pattern("AID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_grenade"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.grenadeSpellBullet)
				.requires(ModItems.spellBullet)
				.requires(Tags.Items.GUNPOWDER)
				.unlockedBy("has_psidust", has(ModItems.psidust))
				.save(WrapperResult.ofType(BulletUpgradeRecipe.SERIALIZER, consumer), Psi.location("spell_bullet_grenade_upgrade"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.chargeSpellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('A', Tags.Items.DUSTS_REDSTONE)
				.pattern("AID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_charge"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.chargeSpellBullet)
				.requires(ModItems.spellBullet)
				.requires(Tags.Items.DUSTS_REDSTONE)
				.unlockedBy("has_psidust", has(ModItems.psidust))
				.save(WrapperResult.ofType(BulletUpgradeRecipe.SERIALIZER, consumer), Psi.location("spell_bullet_charge_upgrade"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.mineSpellBullet)
				.define('I', Tags.Items.INGOTS_IRON)
				.define('D', ModTags.PSIDUST)
				.define('A', ItemTags.BUTTONS)
				.pattern("AID")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("spell_bullet_mine"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.mineSpellBullet)
				.requires(ModItems.spellBullet)
				.requires(ItemTags.BUTTONS)
				.unlockedBy("has_psidust", has(ModItems.psidust))
				.save(WrapperResult.ofType(BulletUpgradeRecipe.SERIALIZER, consumer), Psi.location("spell_bullet_mine_upgrade"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.spellDrive)
				.define('I', ModTags.INGOT_PSIMETAL)
				.define('R', Tags.Items.DUSTS_REDSTONE)
				.pattern("I")
				.pattern("R")
				.pattern("I")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("spell_drive"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalShovel)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("GP")
				.pattern(" I")
				.pattern(" I")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_shovel"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalPickaxe)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("PGP")
				.pattern(" I ")
				.pattern(" I ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_pickaxe"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalAxe)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("GP")
				.pattern("PI")
				.pattern(" I")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_axe"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalSword)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("P")
				.pattern("G")
				.pattern("I")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_sword"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalExosuitHelmet)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern("GPG")
				.pattern("P P")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_exosuit_helmet"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalExosuitChestplate)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern("P P")
				.pattern("GPG")
				.pattern("PPP")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_exosuit_chestplate"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalExosuitLeggings)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern("GPG")
				.pattern("P P")
				.pattern("P P")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_exosuit_leggings"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalExosuitBoots)
				.define('P', ModTags.INGOT_PSIMETAL)
				.define('G', ModTags.GEM_PSIGEM)
				.pattern("G G")
				.pattern("P P")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_exosuit_boots"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.detonator)
				.define('P', ModTags.PSIDUST)
				.define('B', ItemTags.BUTTONS)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern(" B ")
				.pattern("IPI")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("detonator"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.exosuitController)
				.define('R', Tags.Items.DUSTS_REDSTONE)
				.define('G', Tags.Items.GLASS)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern("R")
				.pattern("G")
				.pattern("I")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("exosuit_controller"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.vectorRuler)
				.define('D', ModTags.PSIDUST)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("D")
				.pattern("I")
				.pattern("I")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("vector_ruler"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.exosuitSensorLight)
				.define('M', Tags.Items.DUSTS_GLOWSTONE)
				.define('R', Tags.Items.INGOTS_IRON)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" I ")
				.pattern("IMR")
				.pattern(" R ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("exosuit_sensor_light"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.exosuitSensorWater)
				.define('M', Tags.Items.GEMS_PRISMARINE)
				.define('R', Tags.Items.INGOTS_IRON)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" I ")
				.pattern("IMR")
				.pattern(" R ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("exosuit_sensor_water"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.exosuitSensorHeat)
				.define('M', Items.FIRE_CHARGE)
				.define('R', Tags.Items.INGOTS_IRON)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" I ")
				.pattern("IMR")
				.pattern(" R ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("exosuit_sensor_heat"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.exosuitSensorStress)
				.define('M', Items.GLISTERING_MELON_SLICE)
				.define('R', Tags.Items.INGOTS_IRON)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" I ")
				.pattern("IMR")
				.pattern(" R ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("exosuit_sensor_stress"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.exosuitSensorTrigger)
				.define('M', Items.GUNPOWDER)
				.define('R', Tags.Items.INGOTS_IRON)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" I ")
				.pattern("IMR")
				.pattern(" R ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("exosuit_sensor_trigger"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadColorizerEmpty)
				.define('D', ModTags.PSIDUST)
				.define('G', Tags.Items.GLASS)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern(" D ")
				.pattern("G G")
				.pattern(" I ")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("cad_colorizer_empty"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psidustBlock.asItem())
				.define('I', ModItems.psidust)
				.pattern("III")
				.pattern("III")
				.pattern("III")
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("psidust_block"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psimetalBlock.asItem())
				.define('I', ModItems.psimetal)
				.pattern("III")
				.pattern("III")
				.pattern("III")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_block"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psigemBlock.asItem())
				.define('I', ModItems.psigem)
				.pattern("III")
				.pattern("III")
				.pattern("III")
				.unlockedBy("has_psigem", has(ModItems.psigem))
				.save(consumer, Psi.location("psigem_block"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psimetalEbony.asItem())
				.define('I', ModItems.ebonyPsimetal)
				.pattern("III")
				.pattern("III")
				.pattern("III")
				.unlockedBy("has_ebony_psimetal", hasEbonyPsimetal)
				.save(consumer, Psi.location("ebony_block"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psimetalIvory.asItem())
				.define('I', ModItems.ivoryPsimetal)
				.pattern("III")
				.pattern("III")
				.pattern("III")
				.unlockedBy("has_ivory_psimetal", hasIvoryPsimetal)
				.save(consumer, Psi.location("ivory_block"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.psidust, 9)
				.requires(ModBlocks.psidustBlock.asItem())
				.unlockedBy("has_psidust", hasPsidust)
				.save(consumer, Psi.location("psidust_shapeless"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.psimetal, 9)
				.requires(ModBlocks.psimetalBlock.asItem())
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_shapeless"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.psigem, 9)
				.requires(ModBlocks.psigemBlock.asItem())
				.unlockedBy("has_psigem", has(ModItems.psigem))
				.save(consumer, Psi.location("psigem_shapeless"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ebonyPsimetal, 9)
				.requires(ModBlocks.psimetalEbony.asItem())
				.unlockedBy("has_ebony_psimetal", hasEbonyPsimetal)
				.save(consumer, Psi.location("ebony_ingot_shapeless"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ivoryPsimetal, 9)
				.requires(ModBlocks.psimetalIvory.asItem())
				.unlockedBy("has_ivory_psimetal", hasIvoryPsimetal)
				.save(consumer, Psi.location("ivory_ingot_shapeless"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psimetalPlateBlack.asItem())
				.define('C', ItemTags.COALS)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" C ")
				.pattern("CIC")
				.pattern(" C ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_plate_black"));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.psimetalPlateWhite.asItem())
				.define('C', Tags.Items.GEMS_QUARTZ)
				.define('I', ModTags.INGOT_PSIMETAL)
				.pattern(" C ")
				.pattern("CIC")
				.pattern(" C ")
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_plate_white"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.psimetalPlateBlackLight.asItem())
				.requires(Tags.Items.DUSTS_GLOWSTONE)
				.requires(ModBlocks.psimetalPlateBlack.asItem())
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_plate_black_light"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.psimetalPlateWhiteLight.asItem())
				.requires(Tags.Items.DUSTS_GLOWSTONE)
				.requires(ModBlocks.psimetalPlateWhite.asItem())
				.unlockedBy("has_psimetal", hasPsimetal)
				.save(consumer, Psi.location("psimetal_plate_white_light"));
	}

	private static void specialRecipe(SimpleCraftingRecipeSerializer<?> serializer, Consumer<FinishedRecipe> consumer) {
		SpecialRecipeBuilder.special(serializer).save(consumer, Psi.location("dynamic/" + ForgeRegistries.RECIPE_SERIALIZERS.getKey(serializer).getPath()).toString());
	}
}
