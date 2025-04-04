package Main;

import Main.Commands.Headpet;
import Main.Commands.Timestamp;
import Main.Loader.ResourceLoader;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;


public class MessageReceivedListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return; // Ignoriere Bots
        Message message = event.getMessage();
        String content = message.getContentRaw().toLowerCase();
        GuildMessageChannel channel = event.getChannel().asGuildMessageChannel();
        String prefix = "!";
        if (!content.startsWith(prefix)) return;
        String[] commandParts = content.substring(prefix.length()).split(" ", 2);
        String command = commandParts[0];


        switch (command) {
            case "miau":
                String MIAU = ResourceLoader.getMessage("MIAU");
                channel.sendMessage(MIAU).queue();
                break;
            case "help":
                String HELP = ResourceLoader.getMessage("HELP");
                channel.sendMessage(HELP).queue();
                break;
            case "commands":
                String COMMANDS = ResourceLoader.getMessage("COMMANDS");
                channel.sendMessage(COMMANDS).queue();
                break;
            case "timestamp":
                Timestamp.timestamp(commandParts, channel);
                break;
            case "headpet":
                Headpet.headpet(channel);
                break;
            default:
                break;
        }
    }
}
