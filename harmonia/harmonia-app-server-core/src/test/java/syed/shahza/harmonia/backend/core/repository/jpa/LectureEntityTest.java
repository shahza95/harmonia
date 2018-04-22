package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;


public class LectureEntityTest {
    private LectureEntity lectureEntity;
    private String randomString;

    @Before
    public void before() {
        lectureEntity = TestLectureEntity.aLectureEntity();
        randomString = RandomStringUtils.random(10);
    }

    @Test
    public void getAndSetTitle() {
        lectureEntity.setTitle(randomString);

        assertThat(lectureEntity.getTitle(), is(randomString));
    }

    @Test
    public void getAndSetPassword() {
        lectureEntity.setPassword(randomString);

        assertThat(lectureEntity.getPassword(), is(randomString));
    }
    
    @Test
    public void getAndSetDate() {
    	LocalDate date = new LocalDate();
    	lectureEntity.setDate(date);
    	
    	assertThat(lectureEntity.getDate(), is(date));
    }
    
    @Test
    public void getAndSetStartTime() {
    	LocalTime startTime = new LocalTime();
    	lectureEntity.setStartTime(startTime);
    	
    	assertThat(lectureEntity.getStartTime(), is(startTime));
    }
    
    @Test
    public void getAndSetEndTime() {
    	LocalTime endTime = new LocalTime();
    	lectureEntity.setEndTime(endTime);
    	
    	assertThat(lectureEntity.getEndTime(), is(endTime));
    }
    
    @Test
    public void getAndSetEnded() {
    	lectureEntity.setEnded(true);
    	assertThat(lectureEntity.getEnded(), is(true));
    }
    
    @Test
    public void getAndSetCommentsEnabled() {
    	lectureEntity.setCommentsEnabled(false);
    	assertThat(lectureEntity.getCommentsEnabled(), is(false));
    }
    
    @Test
    public void getAndSetMoodEnabled() {
    	lectureEntity.setMoodEnabled(false);
    	assertThat(lectureEntity.getMoodEnabled(), is(false));
    }
    
    @Test
    public void getAndSetFeedbackEnabled() {
    	lectureEntity.setFeedbackEnabled(false);
    	assertThat(lectureEntity.getFeedbackEnabled(), is(false));
    }
    
    @Test
    public void getAndSetQuestionsEnabled() {
    	lectureEntity.setQuestionsEnabled(false);
    	assertThat(lectureEntity.getQuestionsEnabled(), is(false));
    }
}
