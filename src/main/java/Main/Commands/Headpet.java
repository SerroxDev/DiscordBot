package Main.Commands;

import Main.Loader.FileLoader;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;

import java.io.File;

public class Headpet {

    public static void headpet(GuildMessageChannel channel) {

        try{
            File gifFile = FileLoader.getFile("Pictures/pet.gif");
            channel.sendFiles(net.dv8tion.jda.api.utils.FileUpload.fromData(gifFile)).queue();

        } catch(IllegalArgumentException  e){
        }
    }
}
