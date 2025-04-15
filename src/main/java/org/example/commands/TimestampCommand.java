package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimestampCommand implements Command {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public String getName() {
        return "timestamp";
    }

    @Override
    public String getDescription() {
        return "Creates a Timestamp";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event) {
        OptionMapping date = event.getOption("date");
        OptionMapping time = event.getOption("time");
        OptionMapping style = event.getOption("style");
        try {
            String dateTime = date.getAsString() + " " + time.getAsString();
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateFormat);
            Instant instant = localDateTime.atZone(ZoneId.of("Europe/Berlin")).toInstant();
            long seconds = instant.getEpochSecond();
            String timestamp = "<t:" + seconds + ":" + style.getAsString() + ">";

            event.reply(timestamp).setEphemeral(false).queue();
        } catch (DateTimeParseException e){
            event.reply("no valid Timestamp entered ").queue();
        }
    }
}
