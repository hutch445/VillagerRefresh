package dev.hutchcs.villagerrefresh.Events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.event.ContainerEvent;

@Mod.EventBusSubscriber(modid = "villagerrefresh", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void villagerRefreshTest(PlayerInteractEvent.EntityInteract event) {
        LivingEntity targetEntity = (LivingEntity) event.getTarget();
        Player playerEntity = event.getEntity();
        if (targetEntity instanceof Villager villager) {
            MerchantOffers currOffers = villager.getOffers();
            if (playerEntity.getMainHandItem().getItem() == Items.STICK.asItem() && villager.getVillagerXp() == 0) {
                System.out.println("Interacted with Stick");
//                if (villager.getVillagerData().getLevel() == 1 && villager.getVillagerXp() == 0) {
//                    villager.setOffers(null);
//                }
                VillagerData villagerdata = villager.getVillagerData();
                Int2ObjectMap<VillagerTrades.ItemListing[]> int2objectmap = VillagerTrades.TRADES.get(villagerdata.getProfession());
                if (int2objectmap != null && !int2objectmap.isEmpty()) {
                    VillagerTrades.ItemListing[] avillagertrades$itemlisting = int2objectmap.get(villagerdata.getLevel());
                    if (avillagertrades$itemlisting != null) {
                        System.out.println("tried to update");
                        MerchantOffers merchantoffers = new get(int2objectmap);
                        villager.setOffers(merchantoffers);
                    }
                }
            }
        }
    }

    private static class get extends MerchantOffers {
        public get(Int2ObjectMap<VillagerTrades.ItemListing[]> int2objectmap) {
        }
    }

//    @SubscribeEvent
//    public static void onlyRefreshNew(ContainerEvent event) {
//        Container targetContainer = (Container) event.getContainer();
//        if (targetContainer instanceof MerchantContainer merchantContainer){
//            System.out.println("Living Event happened with Villager");
//        }
//    }
}
