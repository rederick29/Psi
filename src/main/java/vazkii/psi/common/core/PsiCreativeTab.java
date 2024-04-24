/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.common.core;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import vazkii.psi.common.block.base.ModBlocks;
import vazkii.psi.common.item.ItemCAD;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibMisc;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PsiCreativeTab {
	public static ResourceKey<CreativeModeTab> PSI_CREATIVE_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(LibMisc.MOD_ID, "creative_tab"));

	@SubscribeEvent
	public static void buildContents(RegisterEvent event) {
		event.register(Registries.CREATIVE_MODE_TAB, helper -> {
			CreativeModeTab tab = CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.Psi"))
					.hideTitle()
					.icon(() -> new ItemStack(ModItems.cadAssemblyIron))
					.displayItems((params, output) -> {
						NonNullList<ItemStack> items = NonNullList.create();
						ModItems.fillItems(items);
						ModBlocks.fillItems(items);
						ItemCAD.fillItemCategory(items);
						output.acceptAll(items);
					})
					.build();
			helper.register(PSI_CREATIVE_TAB, tab);
		});
	}
}
