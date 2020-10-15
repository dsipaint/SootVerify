import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		if(!e.getGuild().getId().equals("565623426501443584"))
			return;
		
		if(StopListener.isStaff(e.getMember()))
		{
			String msg = e.getMessage().getContentRaw();
			String[] args = msg.split(" ");
			
			if(args[0].equalsIgnoreCase(Main.PREFIX + "verify"))
			{
				if(args.length == 1)
					return;
				
				//user id
				if(args[1].matches("\\d{18}"))
				{
					e.getGuild().retrieveMemberById(args[1]).queue(member ->
					{
						e.getGuild().removeRoleFromMember(member, e.getGuild().getRoleById("732290854118621344")).queue();
						e.getChannel().sendMessage("Verified user " + member.getEffectiveName()).queue();
					},
					
					member ->
					{
						e.getChannel().sendMessage("Error: Could not verify user").queue();
					});
					
				} //user ping
				else if(args[1].matches("<@!\\d{18}>"))
				{
					String id = args[1].substring(3, 21);
					e.getGuild().retrieveMemberById(id).queue(member ->
					{
						e.getGuild().removeRoleFromMember(member, e.getGuild().getRoleById("732290854118621344")).queue();
						e.getChannel().sendMessage("Verified user " + member.getEffectiveName()).queue();
					},
					
					member ->
					{
						e.getChannel().sendMessage("Error: Could not verify user").queue();
					});
				}
				
				return;
			}
		}
	}
}
