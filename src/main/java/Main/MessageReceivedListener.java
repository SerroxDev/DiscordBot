package Main;

import Main.Commands.TimestampUtil;
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
        String datetime = null;
        String timestamp = null;

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
                datetime = commandParts[1];
                timestamp = TimestampUtil.getTimestamp(datetime, 'F');
                channel.sendMessage(timestamp).queue();
                break;
                //TODO Tmestamp msg gets printed twice
            case "relativetimestamp":
                datetime = commandParts[1];
                timestamp = TimestampUtil.getTimestamp(datetime, 'F');
                String rTimestamp = TimestampUtil.getTimestamp(datetime, 'R');
                channel.sendMessage(timestamp + rTimestamp).queue();
                break;
            default:
                break;
        }
    }
}
