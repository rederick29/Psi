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
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.NotNull;
import vazkii.psi.client.gui.GuiProgrammer;

public class GuiButtonIO extends Button {

	public final boolean out;
	final GuiProgrammer gui;

	public GuiButtonIO(int x, int y, boolean out, GuiProgrammer gui) {
		super(x, y, 12, 12, Component.empty(), button -> {}, DEFAULT_NARRATION);
		this.out = out;
		this.gui = gui;
	}

	public GuiButtonIO(int x, int y, boolean out, GuiProgrammer gui, OnPress pressable) {
		super(x, y, 12, 12, Component.empty(), pressable, DEFAULT_NARRATION);
		this.out = out;
		this.gui = gui;
	}

	@Override
	public void renderWidget(@NotNull PoseStack ms, int x, int y, float pticks) {
		if(active && !gui.takingScreenshot) {
			boolean hover = x >= getX() && y >= getY() && x < getX() + width && y < getY() + height;

			RenderSystem.setShaderTexture(0, GuiProgrammer.texture);
			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
			blit(ms, getX(), getY(), hover ? 186 : 174, out ? 169 : 181, width, height);

			if(hover) {
				String key = out ? "psimisc.export_to_clipboard" : "psimisc.import_from_clipboard";
				ChatFormatting color = out ? ChatFormatting.RED : ChatFormatting.BLUE;
				Component tip = Component.translatable(key).withStyle(color);
				gui.tooltip.add(tip);
				gui.tooltip.add(Component.translatable("psimisc.must_hold_shift").withStyle(ChatFormatting.GRAY));
			}
		}
	}

}
