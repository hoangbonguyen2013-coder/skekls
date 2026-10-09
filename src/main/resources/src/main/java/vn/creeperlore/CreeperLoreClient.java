package vn.creeperlore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class CreeperLoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, flag, lines) -> {
            if (!stack.is(Items.GREEN_STAINED_GLASS) && !stack.is(Items.LIME_STAINED_GLASS)) {
                return;
            }

            List<Component> out = new ArrayList<>();
            out.add(Component.literal("Lồng triệu hồi Creeper").withStyle(ChatFormatting.LIGHT_PURPLE));
            out.add(Component.empty());
            out.add(Component.literal("Interact with Spawn Egg:").withStyle(ChatFormatting.GRAY));
            out.add(Component.literal("  Sets Mob Type").withStyle(ChatFormatting.BLUE));

            for (int i = 1; i < lines.size(); i++) {
                Component line = lines.get(i);
                String plain = ChatFormatting.stripFormatting(line.getString());
                if (plain != null && plain.contains("Bán được")) {
                    continue;
                }
                out.add(line);
            }

            lines.clear();
            lines.addAll(out);
        });
    }
}
