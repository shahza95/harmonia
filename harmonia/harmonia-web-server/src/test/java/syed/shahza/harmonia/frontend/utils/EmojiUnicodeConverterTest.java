package syed.shahza.harmonia.frontend.utils;

import static syed.shahza.harmonia.frontend.utils.EmojiUnicodeConverter.convertToUnicode;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;


public class EmojiUnicodeConverterTest {

	@Test
	public void shouldConvertHappyFaceCorrectly() {
		assertThat(convertToUnicode(":)"), is("&#x1F642;"));
	}
	
	@Test
	public void shouldConvertSadFaceCorrectly() {
		assertThat(convertToUnicode(":("), is("&#x1F641;"));
	}
	
	@Test
	public void shouldConvertConfusedFaceCorrectly() {
		assertThat(convertToUnicode(":S"), is("&#x1F615;"));
	}
}
