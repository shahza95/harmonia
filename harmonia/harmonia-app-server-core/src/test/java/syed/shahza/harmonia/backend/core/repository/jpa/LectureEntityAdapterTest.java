package syed.shahza.harmonia.backend.core.repository.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import syed.shahza.harmonia.backend.core.domain.Lecture;
import syed.shahza.harmonia.backend.core.domain.TestLecture;


public class LectureEntityAdapterTest {
    private LectureEntityAdapter adapter;
    private Lecture.Builder lectureBuilder;
    private String randomString;

    @Before
    public void Before() {
        this.adapter = new LectureEntityAdapter();
        this.lectureBuilder = TestLecture.aValidLecture();
        this.randomString = RandomStringUtils.random(10);
    }

    @Test
    public void adaptsTitleFromDomainToEntityCorrectlyAndBackAgain() {
        LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.title(randomString).build());

        assertThat(adapter.toDomain(lectureEntity).getTitle(), is(randomString));
    }

    @Test
    public void adaptsPasswordFromDomainToEntityCorrectlyAndBackAgain() {
        LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.password(randomString).build());

        assertThat(adapter.toDomain(lectureEntity).getPassword(), is(randomString));
    }
    
    @Test
    public void adaptsDateFromDomainToEntityCorrectlyAndBackAgain() {
    	LocalDate date = new LocalDate();
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.date(date).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getDate(), is(date));
    }
    
    @Test
    public void adaptsStartTimeFromDomainToEntityCorrectlyAndBackAgain() {
    	LocalTime time = new LocalTime();
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.startTime(time).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getStartTime(), is(time));
    }
    
    @Test
    public void adaptsEndTimeFromDomainToEntityCorrectlyAndBackAgain() {
    	LocalTime time = new LocalTime().plusHours(1);
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.endTime(time).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getEndTime(), is(time));
    }
    
    @Test
    public void adaptsEndedFromDomainToEntityCorrectlyAndBackAgain() {
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.ended(true).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getEnded(), is(true));
    }
    
    @Test
    public void adaptsCommentsEnabledFromDomainToEntityCorrectlyAndBackAgain() {
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.commentsEnabled(false).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getCommentsEnabled(), is(false));
    }
    
    @Test
    public void adaptsMoodEnabledFromDomainToEntityCorrectlyAndBackAgain() {
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.moodEnabled(false).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getMoodEnabled(), is(false));
    }
    
    @Test
    public void adaptsFeedbackEnabledFromDomainToEntityCorrectlyAndBackAgain() {
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.feedbackEnabled(false).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getFeedbackEnabled(), is(false));
    }
    
    @Test
    public void adaptsQuestionsEnabledFromDomainToEntityCorrectlyAndBackAgain() {
    	LectureEntity lectureEntity = adapter.toEntity(lectureBuilder.questionsEnabled(false).build());
    	
    	assertThat(adapter.toDomain(lectureEntity).getQuestionsEnabled(), is(false));
    }
}
