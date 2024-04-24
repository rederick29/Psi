/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.jei.tricks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;

import mezz.jei.api.gui.drawable.IDrawableStatic;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;

import vazkii.psi.api.spell.SpellPiece;

public class DrawablePiece implements IDrawableStatic {

	public final SpellPiece piece;

	public DrawablePiece(SpellPiece piece) {
		this.piece = piece;
	}

	@Override
	public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset, int maskTop, int maskBottom, int maskLeft, int maskRight) {
		PoseStack ms = guiGraphics.pose();
		ms.pushPose();
		ms.translate(xOffset, yOffset, 0);

		MultiBufferSource.BufferSource buffers = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
		piece.drawBackground(ms, buffers, 0xF000F0);

		buffers.endBatch();

		ms.popPose();
	}

	@Override
	public int getWidth() {
		return 16;
	}

	@Override
	public int getHeight() {
		return 16;
	}

	@Override
	public void draw(GuiGraphics guiGraphics, int xOff, int yOff) {
		draw(guiGraphics, xOff, yOff, 0, 0, 0, 0);
	}
}
