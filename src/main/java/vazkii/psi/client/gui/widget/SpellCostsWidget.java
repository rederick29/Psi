/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.EnumCADStat;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.client.gui.GuiProgrammer;
import vazkii.psi.common.Psi;
import vazkii.psi.common.item.ItemCAD;

public class SpellCostsWidget extends AbstractWidget {

	private final GuiProgrammer parent;

	public SpellCostsWidget(int x, int y, int width, int height, String message, GuiProgrammer programmer) {
		super(x, y, width, height, Component.nullToEmpty(message));
		this.parent = programmer;
	}

	@Override
	protected boolean isValidClickButton(int p_isValidClickButton_1_) {
		return false;
	}

	@Override
	public void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float pTicks) {
		parent.compileResult.left().ifPresent(compiledSpell -> {
			int i = 0;
			int statX = parent.left + parent.xSize + 3;

			SpellMetadata meta = compiledSpell.metadata;
			ItemStack cad = PsiAPI.getPlayerCAD(parent.getMinecraft().player);
			for(EnumSpellStat stat : meta.getStatSet()) {
				int val = meta.getStat(stat);
				int statY = parent.top + (parent.takingScreenshot ? 40 : 20) + i * 20;
				EnumCADStat cadStat = stat.getTarget();
				int cadVal = 0;

				if(cadStat == null) {
					cadVal = -1;
				} else if(!cad.isEmpty()) {
					ICAD cadItem = (ICAD) cad.getItem();
					cadVal = cadItem.getStatValue(cad, cadStat);
				}
				String s = "" + val;
				if(stat == EnumSpellStat.COST) {
					s += " (" + Math.max(0, ItemCAD.getRealCost(cad, ItemStack.EMPTY, val)) + ")";
				} else {
					s += "/" + (cadVal == -1 ? "\u221E" : cadVal);
				}

				RenderSystem.setShaderColor(1f, 1f, 1f, 1F);
				RenderSystem.setShaderTexture(0, GuiProgrammer.texture);
				guiGraphics.blit(GuiProgrammer.texture, statX, statY, (stat.ordinal() + 1) * 12, parent.ySize + 16, 12, 12);
				guiGraphics.drawString(parent.getMinecraft().font, s, statX + 16, statY + 2, cadStat != null && cadVal < val && cadVal != -1 ? 0xFF6666 : 0xFFFFFF, true);

				if(mouseX > statX && mouseY > statY && mouseX < statX + 12 && mouseY < statY + 12 && !parent.panelWidget.panelEnabled) {
					parent.tooltip.add(Component.translatable(stat.getName()).withStyle(Psi.magical ? ChatFormatting.LIGHT_PURPLE : ChatFormatting.AQUA));
					parent.tooltip.add(Component.translatable(stat.getDesc()).withStyle(ChatFormatting.GRAY));
				}
				i++;

			}
		});
	}

	@Override
	protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

	}
}
