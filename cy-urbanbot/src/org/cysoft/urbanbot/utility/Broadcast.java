package org.cysoft.urbanbot.utility;

import java.nio.charset.Charset;
import java.util.List;

import org.cysoft.bss.core.model.Person;
import org.cysoft.urbanbot.api.bss.CyBssCoreAPI;
import org.cysoft.urbanbot.api.telegram.TelegramAPI;
import org.cysoft.urbanbot.common.CyUrbanbotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broadcast {
	
	private static final Logger logger = LoggerFactory.getLogger(Broadcast.class);
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info(">>> Start Broadcast...");
		final String itemEmoj=new String(new byte[]{(byte)0xE2, (byte)0x9C, (byte)0x94}, Charset.forName("UTF-8"));
		
		CyBssCoreAPI bssCoreAPI=CyBssCoreAPI.getInstance();
		bssCoreAPI.setAppName("Urbanbot");		
		//bssCoreAPI.setCoreUrl("http://localhost:8080/cy-bss-core");
		bssCoreAPI.setCoreUrl("http://carovignobot.org/cy-bss-core");
			
		try {
			//bssCoreAPI.logOn("cybss", "cybss");
			bssCoreAPI.logOn("cybss", "a100969$");
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			System.exit(1);
		}
		
		TelegramAPI telegramAPI=TelegramAPI.getInstance();
		//telegramAPI.setBotUrl("https://api.telegram.org/bot130643009:AAGW77QBGQV4A7G494zA_w2DfzCR0zqULCM/");
		telegramAPI.setBotUrl("https://api.telegram.org/bot169494023:AAETCiMmLaNzqbKQXDzL5l0_YouF3TGF-Jg/");
		
		String message="Siete in tantissimi a provare ‪#‎carovignobot‬ ! Grazie.\n\n";
		message+="Non esitate ad inviarci i vostri suggerimenti e contributi per migliorare il bot: es. guide audio (mp3), descrizioni più accurate ed in lingua inglese, idee per nuovi servizi, ....\n\n";
		message+="https://telegram.me/carovignobot\n\n";
		message+="Buon divertimento !";
		
		/*
		String message="<strong>Update News:</strong>\n\n";
		message+="Caro Utente, riportiamo di seguito le funzionalità rilasciate di recente in @carovignobot:\n\n";
		message+=itemEmoj+" la possibilità di effettuare <strong>ricerche</strong> all'interno delle sezioni segnalazioni e storie;\n\n";
		message+=itemEmoj+" l'integrazione delle segnalazioni inserite dai cittadini in <strong>Decoro Urbano</strong>;\n\n";
		message+=itemEmoj+" la <strong>paginazione</strong> dei dati: segnalazioni, storie, siti turistici;\n\n";
		message+=itemEmoj+" la possibilità di condividere i dati estratti dal bot mediante <strong>inline queries</strong> nelle chat (https://core.telegram.org/bots/inline).\n";
		message+="Di seguito alcuni esempi:\n";
		message+="1. scrivendo <strong>@carovignobot</strong>, saranno visualizzati tutti i siti turistici in una 'pick list', dalla quale l'utente potrà scegliere quale inviare;\n";
		message+="2. <strong>@carovignobot torre</strong>, siti turistici che contengono nel nome o nella descrizione la parola 'torre';\n";
		message+="3. <strong>@carovignobot segn</strong>, tutte le segnalazioni;\n";
		message+="4. <strong>@carovignobot segn parco</strong>, tutte le segnalazioni che contengono la parola 'parco';\n";
		message+="5. <strong>@carovignobot storie</strong>, tutte le storie;\n";
		message+="6. <strong>@carovignobot storie carnevale</strong>, tutte le storie che contengono la parola 'carnevale'.\n";
		message+="\nBuon divertimento !";
		*/
		
		String chatFilter="114716051";
		//String chatFilter="*";
		
		
		List<Person> persons=null;
		try {
			persons=bssCoreAPI.findPersonsAll();
		} catch (CyUrbanbotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			System.exit(1);
		}
		
		for(Person person:persons){
			logger.info(person.toString());
			try {
				if (person.getCode().startsWith("tlgid:")){
					String chatId=person.getCode().substring(6);
					if (chatId.equals(chatFilter) || chatFilter.equals("*"))
						telegramAPI.sendMessage(message, Long.parseLong(chatId),0,TelegramAPI.MESSAGE_PARSEMODE_HTML);
				}
			} catch (CyUrbanbotException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
			}
		}
		
		logger.info("<<< End Broadcast...");
	}

}
