/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.gui.widget;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import vazkii.psi.api.spell.SpellGrid;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.client.gui.GuiProgrammer;

import java.util.ArrayList;
import java.util.List;

public class SideConfigWidget extends AbstractWidget {

	public final List<Button> configButtons = new ArrayList<>();
	public boolean configEnabled = false;
	public final GuiProgrammer parent;

	public SideConfigWidget(int x, int y, int width, int height, GuiProgrammer programmer) {
		super(x, y, width, height, Component.empty());
		this.parent = programmer;
	}

	@Override
	protected boolean isValidClickButton(int p_isValidClickButton_1_) {
		return false;
	}

	@Override
	public void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float pTicks) {
		SpellPiece piece = null;
		if(SpellGrid.exists(GuiProgrammer.selectedX, GuiProgrammer.selectedY)) {
			piece = parent.spell.grid.gridData[GuiProgrammer.selectedX][GuiProgrammer.selectedY];
		}
		if(configEnabled && !parent.takingScreenshot) {
			guiGraphics.blit(GuiProgrammer.texture, parent.left - 81, parent.top + 55, parent.xSize, 30, 81, 115);
			String configStr = I18n.get("psimisc.config");
			guiGraphics.drawString(parent.getMinecraft().font, configStr, parent.left - parent.getMinecraft().font.width(configStr) - 2, parent.top + 45, 0xFFFFFF, true);

			int i = 0;
			if(piece != null) {
				int param = -1;
				for(int j = 0; j < 4; j++) {
					if(InputConstants.isKeyDown(parent.getMinecraft().getWindow().getWindow(), GLFW.GLFW_KEY_1 + j)) {
						param = j;
					}
				}

				for(String s : piece.params.keySet()) {
					int x = parent.left - 75;
					int y = parent.top + 70 + i * 26;

					RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
					RenderSystem.setShaderTexture(0, GuiProgrammer.texture);
					guiGraphics.blit(GuiProgrammer.texture, x + 50, y - 8, parent.xSize, 145, 24, 24);

					String localized = I18n.get(s);
					if(i == param) {
						localized = ChatFormatting.UNDERLINE + localized;
					}

					guiGraphics.drawString(parent.getMinecraft().font, localized, x, y, 0xFFFFFF, true);

					i++;
				}
			}
		}
	}

	@Override
	protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

	}
}
