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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.psi.common.block.base.ModBlocks;
import vazkii.psi.common.item.ItemCAD;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibMisc;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PsiCreativeTab {
	@SubscribeEvent
	public static void buildContents(@Nonnull CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation(LibMisc.MOD_ID, "creative_tab"), builder -> {
			builder.title(Component.translatable("Psi"));
			builder.hideTitle();
			builder.icon(() -> new ItemStack(ModItems.cadAssemblyIron));
			builder.displayItems((features, output, creativeTab) -> {
				NonNullList<ItemStack> items = NonNullList.create();
				ModItems.fillItems(items);
				ModBlocks.fillItems(items);
				ItemCAD.fillItemCategory(items);
				output.acceptAll(items);
				output.accept(new ItemStack(ModItems.cadAssemblyIron));
			});
			// builder.withBackgroundLocation(new ResourceLocation(LibMisc.MOD_ID, LibResources.GUI_CREATIVE));
			builder.build();
		});
	}
}
