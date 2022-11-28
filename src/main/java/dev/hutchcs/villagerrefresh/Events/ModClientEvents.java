package dev.hutchcs.villagerrefresh.Events;

import dev.hutchcs.villagerrefresh.VillagerRefresh;
import dev.hutchcs.villagerrefresh.network.ModMessages;
import dev.hutchcs.villagerrefresh.network.packet.C2SPacket;
import dev.hutchcs.villagerrefresh.server.MessageRefreshTrades;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = VillagerRefresh.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    private static VillagerTrades.ItemListing testing;


    @SubscribeEvent
    public static void villagerRefreshTest(PlayerInteractEvent.EntityInteract event) {
        LivingEntity targetEntity = (LivingEntity) event.getTarget();
        Player playerEntity = event.getEntity();
        if (targetEntity instanceof Villager villager) {
            MerchantOffers currOffers = villager.getOffers();
            if (playerEntity.getMainHandItem().getItem() == Items.STICK.asItem() && villager.getVillagerXp() == 0) {
                System.out.println("Interacted with Stick");
                int villagerLevel = villager.getVillagerData().getLevel();
                if (villagerLevel == 1 && villager.getVillagerXp() == 0) {
                    villager.setOffers(null);
                    for (MerchantOffer merchantOffer : villager.getOffers()) {
                        System.out.println("Cost A: " + merchantOffer.getCostA());
                        System.out.println("Cost B: " + merchantOffer.getCostB());
                        System.out.println("Result: " + merchantOffer.getResult());
                    }
                } else {
//                    new MessageRefreshTrades().executeServerSide();
                    ModMessages.sendToServer(new C2SPacket());
                }
//                    MerchantOffers newOffers = new MerchantOffers();
//                    System.out.println("Villager Offers before: " + villager.getOffers());
//                    for (MerchantOffer merchantOffer : villager.getOffers()) {
//                        System.out.println("Cost A: " + merchantOffer.getCostA());
//                        System.out.println("Cost B: " + merchantOffer.getCostB());
//                        System.out.println("Result: " + merchantOffer.getResult());
//                    }
//                    int offersSize = villager.getOffers().size()-1;
//                    for (int i = 0; i < offersSize; i++) {
//                        newOffers.add(villager.getOffers().get(i));
//                    }
//                    System.out.println("Got through for loop");
//
//                    newOffers.add(null);
//                    villager.setOffers(null);
//                    villager.setOffers(newOffers);
//                    villager.updateTrades();
//                    System.out.println("Villager Offers after: " + villager.getOffers());
//                    for (MerchantOffer merchantOffer : villager.getOffers()) {
//                        System.out.println("Cost A: " + merchantOffer.getCostA());
//                        System.out.println("Cost B: " + merchantOffer.getCostB());
//                        System.out.println("Result: " + merchantOffer.getResult());
//                    }
//

//                    System.out.println("1st Offers: " + villager.getOffers());
//                    villager.setOffers(null);
//
//                    System.out.println("2nd Offers: " + villager.getOffers());
//                    ByteBuf buffer = Unpooled.buffer();
//                    villager.getOffers().writeToStream(new FriendlyByteBuf(buffer));
//                    for (int i = 0; i < buffer.capacity(); i ++) {
//                        byte b = buffer.getByte(i);
//                        System.out.print(b + " ");
//                    }
//                    System.out.println("3rd Offers: " + villager.getOffers());
//                    for (MerchantOffer merchantOffer : villager.getOffers()) {
//                        System.out.println("Cost A: " + merchantOffer.getCostA());
//                        System.out.println("Cost B: " + merchantOffer.getCostB());
//                        System.out.println("Result: " + merchantOffer.getResult());
//                    }
//                    VillagerData oldData = villager.getVillagerData();
//                    VillagerData newData = new VillagerData(oldData.getType(), oldData.getProfession(), oldData.getLevel());
//                    villager.setVillagerData(newData);
//                    villager.setOffers(null);
//                    System.out.println("Swapped Data");
//                    for (MerchantOffer merchantOffer : villager.getOffers()) {
//                        System.out.println("Cost A: " + merchantOffer.getCostA());
//                        System.out.println("Cost B: " + merchantOffer.getCostB());
//                        System.out.println("Result: " + merchantOffer.getResult());
//                    }


//                    ServerPlayer player = (ServerPlayer) playerEntity;
//                    MerchantMenu container = (MerchantMenu) player.containerMenu;
//                    Villager containerVillager = (Villager) container.trader;
//                    containerVillager.offers = null;
//                    playerEntity.sendMerchantOffers(container.containerId, containerVillager.getOffers(),
//                            containerVillager.getVillagerData().getLevel(), containerVillager.getVillagerXp(),
//                            containerVillager.showProgressBar(), containerVillager.canRestock());
//
//                }
            }
        }
    }


    public static void refreshTrades(ServerPlayer player) {
        System.out.println("COde ran");
        MerchantMenu container = (MerchantMenu) player.containerMenu;
        Villager villager = (Villager) container.trader;
        villager.offers = null;
        player.sendMerchantOffers(container.containerId, villager.getOffers(),
                villager.getVillagerData().getLevel(), villager.getVillagerXp(), villager.showProgressBar(),
                villager.canRestock());
    }


//    @SubscribeEvent
//    public static void test(VillagerTradesEvent event) {
//        if (event.getType() == VillagerProfession.LIBRARIAN) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//            List<VillagerTrades.ItemListing> idk = trades.get(1);
//            VillagerTrades.ItemListing idk2 = idk.get(1);
//            testing = idk2;
//            System.out.println("Printing item listing: " + idk2);
//        }
//    }
}
