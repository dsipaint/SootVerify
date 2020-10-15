import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main
{
	static JDA jda;
	static final String PREFIX = "^";
	
	public static void main(String[] args)
	{
		try
		{
			jda = JDABuilder.createDefault("")
					.build();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			jda.awaitReady();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		jda.addEventListener(new CommandListener());
		jda.addEventListener(new StopListener());
	}
}
