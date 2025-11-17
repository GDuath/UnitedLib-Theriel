package org.unitedlands.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public static void send(
            Collection<? extends Audience> targets,
            List<String> lines,
            Map<String, String> replacements,
            String prefix) {

        if (targets == null || targets.isEmpty()) {
            return;
        }
        if (lines == null || lines.isEmpty()) {
            return;
        }

        Component component = buildComponent(lines, replacements, prefix);

        var receiverAudience = Audience.audience(targets);
        receiverAudience.sendMessage(component);

    }

    private static Component buildComponent(List<String> lines, Map<String, String> replacements, String prefix) {

        List<String> processed = new ArrayList<>(lines.size());
        for (String line : lines) {
            String modified = applyReplacements(line, replacements);
            if (prefix != null && !prefix.isEmpty()) {
                modified = prefix + modified;
            }
            processed.add(modified);
        }

        String joined = String.join("\n", processed);
        return MiniMessage.miniMessage().deserialize(joined);
    }

    private static String applyReplacements(String input, Map<String, String> replacements) {
        if (replacements == null || replacements.isEmpty()) {
            return input;
        }

        String output = input;
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            output = output.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return output;
    }

    // -------------------------------------------------------------------------------------------
    // Overloads: single sender, single String
    // -------------------------------------------------------------------------------------------

    public static void sendMessage(Audience target, String message) {
        sendMessage(target, message, Collections.emptyMap(), null);
    }

    public static void sendMessage(Audience target, String message, Map<String, String> replacements) {
        sendMessage(target, message, replacements, null);
    }

    public static void sendMessage(Audience target, String message, Map<String, String> replacements, String prefix) {
        Objects.requireNonNull(message, "message");
        send(Collections.singletonList(target), Collections.singletonList(message), replacements, prefix);
    }

    // -------------------------------------------------------------------------------------------
    // Overloads: multiple targets, single String
    // -------------------------------------------------------------------------------------------

    public static void sendMessage(Collection<? extends Audience> targets, String message) {
        sendMessage(targets, message, Collections.emptyMap(), null);
    }

    public static void sendMessage(Collection<? extends Audience> targets, String message, Map<String, String> replacements) {
        sendMessage(targets, message, replacements, null);
    }

    public static void sendMessage(Collection<? extends Audience> targets, String message, Map<String, String> replacements,
            String prefix) {
        Objects.requireNonNull(message, "message");
        send(targets, Collections.singletonList(message), replacements, prefix);
    }

    // -------------------------------------------------------------------------------------------
    // Overloads: single sender, multiple lines
    // -------------------------------------------------------------------------------------------

    public static void sendMessage(Audience target, List<String> lines) {
        sendMessage(target, lines, Collections.emptyMap(), null);
    }

    public static void sendMessage(Audience target, List<String> lines, Map<String, String> replacements) {
        sendMessage(target, lines, replacements, null);
    }

    public static void sendMessage(Audience target, List<String> lines, Map<String, String> replacements, String prefix) {
        send(Collections.singletonList(target), lines, replacements, prefix);
    }

    // -------------------------------------------------------------------------------------------
    // Overloads: multiple targets, multiple lines
    // -------------------------------------------------------------------------------------------

    public static void sendMessage(Collection<? extends Audience> targets, List<String> lines) {
        sendMessage(targets, lines, Collections.emptyMap(), null);
    }

    public static void sendMessage(Collection<? extends Audience> targets, List<String> lines,
            Map<String, String> replacements) {
        sendMessage(targets, lines, replacements, null);
    }

    public static void sendMessage(Collection<? extends Audience> targets, List<String> lines,
            Map<String, String> replacements, String prefix) {
        send(targets, lines, replacements, prefix);
    }

}
