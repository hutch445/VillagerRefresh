package dev.hutchcs.villagerrefresh.server;

import dev.hutchcs.villagerrefresh.Events.ModClientEvents;
import net.minecraftforge.network.NetworkEvent;

public class MessageRefreshTrades {

    public void executeServerSide(NetworkEvent.Context context) {
        ModClientEvents.refreshTrades(context.getSender());
    }
}
