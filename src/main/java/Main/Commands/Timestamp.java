package Main.Commands;

import Main.Loader.ResourceLoader;
import Main.Util.TimestampUtil;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;


public class Timestamp {
    public static void timestamp (String[] commandParts, GuildMessageChannel channel){
        if (commandParts.length < 2) {
            String DATEMISSING = ResourceLoader.getError("DATEMISSING");
            channel.sendMessage(DATEMISSING).queue();
            return;
        }
        String datetime = commandParts[1];
        String timestamp = TimestampUtil.getTimestamp(datetime, 'F');
        channel.sendMessage(timestamp).queue();
    }
}
