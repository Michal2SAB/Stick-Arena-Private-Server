package ballistickemu.Lobby.handlers;
import ballistickemu.Types.StickClient;
import ballistickemu.Tools.DatabaseTools;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
/**
 *
 * @author Michal
 */
public class GiveTicketHandler {
    public static void HandlePacket(StickClient client)
    {
        if (1 > client.getTicket()) // dont let anyone spam the cred ticket
        {
            System.out.println("WARNING: " + client.getName() + " attempted to collect cred ticket despite not having it available for them");
        } 
        else 
        {
            int[] prizes = new int[] {20, 25, 30, 35, 40, 55, 60, 75, 100, 250, 500, 999, 1500, 5000};

            int prize = new Random().nextInt(prizes.length);

            switch (prizes[prize]) {
                case 20 -> client.getCredsTicket("0");
                case 25 -> client.getCredsTicket("1");
                case 30 -> client.getCredsTicket("2");
                case 35 -> client.getCredsTicket("3");
                case 40 -> client.getCredsTicket("4");
                case 55 -> client.getCredsTicket("5");
                case 60 -> client.getCredsTicket("6");
                case 75 -> client.getCredsTicket("7");
                case 100 -> client.getCredsTicket("8");
                case 250 -> client.getCredsTicket("9");
                case 500 -> client.getCredsTicket("10");
                case 999 -> client.getCredsTicket("11");
                case 1500 -> client.getCredsTicket("12");
                case 5000 -> client.getCredsTicket("13");
                default -> {
                }
            }

            try 
            {
                PreparedStatement ps = DatabaseTools.getDbConnection().prepareStatement("UPDATE `users` SET `cash` = `cash` + ? WHERE `username` = ?");

                ps.setInt(1, prizes[prize]);
                ps.setString(2, client.getName());
                ps.executeUpdate();

                PreparedStatement ps2 = DatabaseTools.getDbConnection().prepareStatement("UPDATE `users` SET `ticket` = ? WHERE `username` = ?");

                ps2.setInt(1, 0);
                ps2.setString(2, client.getName());
                ps2.executeUpdate();

            } catch (SQLException e)
            {
                client.getAnnounce("There was an error collecting creds ticket, try again later");
                System.out.println("There was an error accepting cred ticket for a user on database.");
            }
        }
    }
}
