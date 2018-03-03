package syed.shahza.harmonia.backend.core.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static syed.shahza.harmonia.backend.core.domain.TestComment.aValidComment;

import org.junit.Test;

public class CommentTest {
    @Test
    public void canGetAndSetMessage() {
        String randomMessageString = "another comment message";
        assertThat(aValidComment().message(randomMessageString).build().getMessage(), is(randomMessageString));
    }
    
    @Test
    public void canGetAndSetLecture() {
    	Lecture lecture = TestLecture.aValidLecture().build();
    	assertThat(aValidComment().lecture(lecture).build().getLecture(), is(lecture));
    }
}
