package org.example.listeners;

import org.example.commands.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.DiscordBot;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandListener extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(DiscordBot.class);
    public static final Map<String, Command> commands = new HashMap<>();

    public CommandListener() {
        commands.put("ping", new PingCommand());
        commands.put("info", new InfoCommand());
        commands.put("echo", new EchoCommand());
        commands.put("timestamp", new TimestampCommand());
        logger.info("registered: {} command", commands.size());
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        logger.info("JDA ready",
                event.getJDA().getSelfUser().getName(),
                event.getJDA().getSelfUser().getDiscriminator());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        Command command = commands.get(commandName);

        if (command != null) {
            logger.debug("executing slashcommand: {} from User: {}", commandName, event.getUser().getName());
            command.executeSlash(event);
        } else {
            logger.warn("slahcommand unknown: {} from User: {}", commandName, event.getUser().getName());
            event.reply("command unknown").setEphemeral(true).queue();
        }
    }
}
