package minibots;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Remindme {
	TimerTask timerTask;
	Timer timer = new Timer();


	public void setTask(MessageReceivedEvent e){
		timerTask = new TimerTask(){
		@Override
		public void run(){
				e.getChannel().sendMessage(
					e.getMessage().getAuthor().getAsMention() +
					" reminder " +
					e.getMessage().getJumpUrl()
				).queue();
				timer.cancel();
			}
		};
	}


	public Remindme(MessageReceivedEvent e){
		setTask(e);
		String message = e.getMessage().getContentRaw();
		String time = message.substring(11, message.length());
		long secs = parser(time);
		timer.schedule(timerTask, secs*1000);
		e.getChannel().sendMessage("Timer at "+DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now(ZoneId.of("Asia/Tbilisi")).plus(secs, ChronoUnit.SECONDS))).queue();
	}

	public long parser(String date){
		date = date.toLowerCase();
		long secs = 0;
		if (date.equals("tomorrow")){
			date = "in 24 hours";
		}

		if (date.contains("in")){
			secs = Integer.parseInt(date.replaceAll("[^\\d ]", "").trim());

			if (date.contains("hour")){
				secs = secs*60*60;
			} else if (date.contains("min")){
				secs = secs*60;
			} else if (date.contains("day")){
				secs = secs*24*60*60;
			} else if (date.contains("week")){
				secs = secs*7*24*60*60;
			} else if (date.contains("month")){
				secs = secs*31*7*24*60*60;
			} else if (date.contains("year")){
				secs = secs*365*31*7*24*60*60;
			} else if (date.contains("decade")){
				secs = secs*365*31*7*24*60*60;
			} else if (date.contains("millennium")){
				secs = secs*1000*365*31*7*24*60*60;
			}
			//yes I know about existense of switch statement
		} else if (date.contains("at")){
			LocalTime localTime;
			LocalTime now = LocalTime.now();
			date = date.substring(3, date.length());
			if (date.contains("pm")){
				date = date.substring(0, date.length()-2);
				localTime= LocalTime.parse(date).plus(12, ChronoUnit.HOURS);
			} else if (date.contains("am")){
				date = date.substring(0, date.length()-2);
				localTime= LocalTime.parse(date);
			} else {
				localTime= LocalTime.parse(date);
			}
			if (localTime.isBefore(now)){
				secs = ChronoUnit.SECONDS.between(now, localTime.plus(24, ChronoUnit.HOURS));
			} else {
				secs = ChronoUnit.SECONDS.between(now, localTime);
			}
		}

		return secs;
	}
}