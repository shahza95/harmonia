package syed.shahza.harmonia.backend.core.repository.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import syed.shahza.harmonia.backend.core.domain.Emotion;



@Entity(name = "MOOD")
@Table(name = "MOOD")
public class MoodEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MOOD_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMOJI", nullable = false)
    private String emoji;
    
    @Column(name = "EMOTION", nullable = false)
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @OneToOne
    @JoinColumn(name = "LECTURE_ID")
    private LectureEntity lecture;

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
    
    public void setEmotion(Emotion emotion) {
    	this.emotion = emotion;
    }
    
    public Emotion getEmotion() {
    	return emotion;
    }
    
    public void setLecture(LectureEntity lecture) {
    	this.lecture = lecture;
    }

    public LectureEntity getLecture() {
        return lecture;
    }
}