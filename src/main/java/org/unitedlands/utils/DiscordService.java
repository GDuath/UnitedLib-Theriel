package org.unitedlands.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class DiscordService {

    public static void sendDiscordEmbed(String webhookUrl, String embed, String pingRoleId, Map<String, String> replacements) {

        try {
            if (webhookUrl == null)
                return;

            URL url = URI.create(webhookUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            if (pingRoleId != null && pingRoleId.length() > 0) {
                replacements.put("ping-role", "<@&" + pingRoleId + ">");
            } else {
                replacements.put("ping-role", "");
            }

            if (replacements != null) {
                for (var entry : replacements.entrySet()) {
                    embed = embed.replace("{" + entry.getKey() + "}",
                            entry.getValue() != null ? entry.getValue() : "");
                }
            }

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = embed.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != 204) {
                System.out.println("Failed to send Discord embed. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
