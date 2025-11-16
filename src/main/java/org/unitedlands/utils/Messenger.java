package org.unitedlands.utils;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.unitedlands.UnitedLib;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Messenger {

    @SuppressWarnings("unused")
    private static final UnitedLib plugin;

    static {
        plugin = UnitedLib.getPlugin(UnitedLib.class);
    }

    public static void broadCastMessage(String message, String prefix) {

        if (message == null || message.isEmpty())
            return;

        Component messageComponent = MiniMessage.miniMessage().deserialize(message);
        if (prefix != null && !prefix.isEmpty()) {
            var prefixComponent = MiniMessage.miniMessage().deserialize(prefix);
            messageComponent = prefixComponent.append(messageComponent);
        }

        Audience all = Bukkit.getServer();
        all.sendMessage(messageComponent);
    }

    public static void broadcastMessages(List<String> messages, Map<String, String> replacements, String prefix) {

        if (messages == null || messages.isEmpty())
            return;

        for (String message : messages) {
            if (replacements != null) {
                for (var entry : replacements.entrySet()) {
                    message = message.replace("{" + entry.getKey() + "}",
                            entry.getValue() != null ? entry.getValue() : "");
                }
            }
            broadCastMessage(message, prefix);
        }
    }

    public static void sendMessage(CommandSender target, String message, String prefix) {

        if (message == null || message.isEmpty())
            return;

        Component messageComponent = MiniMessage.miniMessage().deserialize(message);
        if (prefix != null && !prefix.isEmpty()) {
            var prefixComponent = MiniMessage.miniMessage().deserialize(prefix);
            messageComponent = prefixComponent.append(messageComponent);
        }

        Audience receiver = target;
        receiver.sendMessage(messageComponent);
    }

    public static void sendMessage(List<CommandSender> targets, String message, String prefix) {

        if (message == null || message.isEmpty())
            return;

        Component messageComponent = MiniMessage.miniMessage().deserialize(message);
        if (prefix != null && !prefix.isEmpty()) {
            var prefixComponent = MiniMessage.miniMessage().deserialize(prefix);
            messageComponent = prefixComponent.append(messageComponent);
        }

        Audience receivers = Audience.audience(targets.toArray(new Audience[0]));
        receivers.sendMessage(messageComponent);
    }

    public static void sendMessages(CommandSender target, List<String> messages,
            Map<String, String> replacements, String prefix) {

        if (messages == null || messages.isEmpty())
            return;

        for (String message : messages) {
            if (replacements != null) {
                for (var entry : replacements.entrySet()) {
                    message = message.replace("{" + entry.getKey() + "}",
                            entry.getValue() != null ? entry.getValue() : "");
                }
            }
            sendMessage(target, message, prefix);
        }
    }

}
