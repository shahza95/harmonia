package syed.shahza.harmonia.frontend.controller;

public class EmojiUnicodeConverter {
	
	public static String convertToUnicode(String emoji) {
		switch (emoji) {
			case ":)": emoji = "&#x1F642;";
					   break;
			case ":D": emoji = "&#x1F600;";
					   break;
			case ":(": emoji = "&#x1F641;";
					   break;
			case ":S": emoji = "&#x1F615;";
					   break;
		}
		return emoji;
	}
}
