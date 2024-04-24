package vazkii.psi.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import vazkii.psi.common.core.handler.PsiOverloadDamage;
import vazkii.psi.common.lib.LibMisc;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PsiDamageGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.DAMAGE_TYPE, PsiOverloadDamage::bootstrap);

    public PsiDamageGenerator(DataGenerator gen, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(gen.getPackOutput(), lookupProvider, BUILDER, Set.of(LibMisc.MOD_ID));
    }
}
