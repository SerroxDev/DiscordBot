package Main;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws LoginException {
        String token = TokenLoader.getToken();

        if (token == null || token.isEmpty()) {
            System.err.println("Fehler: Token konnte nicht geladen werden");
            return;
        }
        try {
        JDABuilder.createDefault(token, EnumSet.of(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT
                ))
                .setActivity(Activity.watching("Birds"))
                .addEventListeners(new MessageReceivedListener())
                .build();
        } catch (Exception e) {
            System.err.println("Fehler beim Starten: " + e.getMessage());
        }
    }
}