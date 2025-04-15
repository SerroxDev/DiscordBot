package org.example.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;

public class InfoCommand implements Command{
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info about bot";
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder =new EmbedBuilder();
        embedBuilder.setTitle("Bot Information");
        embedBuilder.setDescription("Simple testbot");
        embedBuilder.setColor(new Color(135,0,180));
        embedBuilder.addField("Author", "Mau", false);
        embedBuilder.addField("Language", "Java", true);
        embedBuilder.addField("Library", "JDA(Java Discord API)", true);
        embedBuilder.setFooter("Blablabla");

        MessageEmbed embed = embedBuilder.build();
        event.replyEmbeds(embed).queue();
    }
}
