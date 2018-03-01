package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestComments.aValidComment;

import org.junit.Test;

public class CommentTest {
    @Test
    public void canGetAndSetMessage() {
        String randomMessageString = "another comment message";
        assertThat(aValidComment().message(randomMessageString).build().getMessage(), is(randomMessageString));
    }
}
