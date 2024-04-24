/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.gui.button;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import vazkii.psi.client.gui.GuiProgrammer;

public class GuiButtonPage extends Button {

	public final boolean right;
	final GuiProgrammer gui;

	public GuiButtonPage(int x, int y, boolean right, GuiProgrammer gui) {
		super(x, y, 18, 10, Component.empty(), button -> {}, DEFAULT_NARRATION);
		this.gui = gui;
		this.right = right;
	}

	public GuiButtonPage(int x, int y, boolean right, GuiProgrammer gui, Button.OnPress pressable) {
		super(x, y, 18, 10, Component.empty(), pressable, DEFAULT_NARRATION);
		this.gui = gui;
		this.right = right;
	}

	@Override
	public void renderWidget(GuiGraphics guiGraphics, int x, int y, float pTicks) {
		if(active) {
			boolean hover = x >= getX() && y >= getY() && x < getX() + width && y < getY() + height;

			RenderSystem.setShaderTexture(0, GuiProgrammer.texture);
			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
			guiGraphics.blit(GuiProgrammer.texture, getX(), getY(), hover ? 216 : 198, right ? 145 : 155, width, height);

			if(hover) {
				gui.tooltip.add(Component.translatable(right ? "psimisc.next_page" : "psimisc.prev_page"));
			}
		}
	}

	public boolean isRight() {
		return right;
	}
}
