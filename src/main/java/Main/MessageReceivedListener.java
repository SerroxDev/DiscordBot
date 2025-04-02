package Main;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;



public class MessageReceivedListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return; // Ignoriere Bots
        Message message = event.getMessage();
        String content = message.getContentRaw();
        GuildMessageChannel channel = event.getChannel().asGuildMessageChannel();
        String prefix = "!";
        if (!content.startsWith(prefix)) return;
        String command = content.substring(prefix.length());

        switch (command) {
            case "Miau":
                channel.sendMessage("Miau").queue();
                break;
            default:
                break;
        }
    }
}
