package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.config.Config;
import org.example.listeners.CommandListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class DiscordBot {
    private static final Logger logger = LoggerFactory.getLogger(DiscordBot.class);
    private static JDA jda;

    public static void main(String[] args) {
        String token = Config.getToken();

        if (token == null || token.isEmpty()) {
            logger.error("Token not found in config.propertiies");
            return;
        }
        try {
            jda = JDABuilder.createDefault(token)
                    .enableIntents(EnumSet.allOf(GatewayIntent.class))
                    .addEventListeners(new CommandListener())
                    .build();

            jda.awaitReady();
            logger.info("bot is online");

            registerSlashCommands();
        } catch (Exception e) {
            logger.error("error starting bot", e);
        }
    }

    private static void registerSlashCommands() {
        if (jda == null) {
            logger.error("jda instance not initialized");
            return;
        }
        logger.info("registering Slash Commands");
        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Checks ping"),
                        Commands.slash("info", "info about bot"),
                        Commands.slash("echo", "echo msg")
                                .addOption(OptionType.STRING, "text", "echo text", true),
                        Commands.slash("timestamp", "creates a Timestamp")
                                .addOption(OptionType.STRING, "date", "dd.MM.yyyy", true)
                                .addOption(OptionType.STRING, "time", "HH:mm", true)
                                .addOption(OptionType.STRING, "style", "F for full Time or R for the relative time", true)
                ).queue(success -> logger.info("Slash commands registert"),
                        failure -> logger.error("failed to register Slash commands", failure));
    }
}
