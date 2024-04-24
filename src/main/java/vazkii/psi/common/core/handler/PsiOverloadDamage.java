package vazkii.psi.common.core.handler;

import net.minecraft.core.registries.Registries;
// why is this called Bootstap ?!?!?
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.resources.ResourceLocation;
import vazkii.psi.common.lib.LibMisc;

public class PsiOverloadDamage {
    public static final ResourceKey<DamageType> psiOverload = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(LibMisc.MOD_ID, "psi-overload"));

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(psiOverload, new DamageType("psi-overload", 0.1f));
    }
}
