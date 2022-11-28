package dev.hutchcs.villagerrefresh;

import dev.hutchcs.villagerrefresh.network.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(VillagerRefresh.MODID)
public class VillagerRefresh {
    public static final String MODID = "villagerrefresh";

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }
}
