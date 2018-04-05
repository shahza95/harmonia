package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.FeedbackDtoList;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDtoList;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestFeedbackDtoList;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestMoodDtoList;
import syed.shahza.harmonia.backend.dto.TestQuestionDtoList;
import syed.shahza.harmonia.restapi.action.EndLectureAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllFeedbackAction;
import syed.shahza.harmonia.restapi.action.GetAllMoodsAction;
import syed.shahza.harmonia.restapi.action.GetAllQuestionsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.ToggleFeaturesAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureControllerLecturerTest {
    private ActiveLectureControllerLecturer lectureController;
    private LectureDto lectureDto;
    private MoodDtoList moodDtoList;
    private FeedbackDtoList feedbackDtoList;
    private int numberOfSameMood;
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private GetAllMoodsAction mockGetAllMoodsAction;
    
    @Mock
    private ToggleFeaturesAction mockToggleFeaturesAction;
    
    @Mock
    private EndLectureAction mockEndLectureAction;
    
    @Mock
    private GetAllFeedbackAction mockGetAllFeedbackAction;
    
    @Mock
    private GetAllQuestionsAction mockGetAllQuestionsAction;
    
    @Before
    public void before() {
    	this.lectureDto = aValidLectureDto().build();
        this.lectureController = new ActiveLectureControllerLecturer(this.mockGetLectureAction, this.mockGetAllCommentsAction, this.mockGetAllMoodsAction, this.mockToggleFeaturesAction, this.mockEndLectureAction, this.mockGetAllFeedbackAction, this.mockGetAllQuestionsAction);
        when(this.mockGetLectureAction.get(lectureDto.getTitle())).thenReturn(lectureDto);
    	this.numberOfSameMood = 3;
    	this.moodDtoList = TestMoodDtoList.aFilledMoodDtoList(numberOfSameMood);
    	this.feedbackDtoList = TestFeedbackDtoList.aFilledFeedbackDtoList(3);
    	when(this.mockGetAllMoodsAction.getAll(lectureDto.getTitle())).thenReturn(moodDtoList);
    	when(this.mockGetAllFeedbackAction.getAll(lectureDto.getTitle())).thenReturn(feedbackDtoList);
    }
    
    @Test
    public void getActiveLectureInvokesGetLectureAction() {
    	this.lectureController.getActiveLecturePage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
        
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getViewName(), is("lecturer/activeLecture"));
    }
    
    @Test
    public void getActiveLectureSendsLectureDtoAsModel() {
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureSendsCommentDtoListAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    	
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getModel().get("commentDtoList"), is(commentDtoList));
    }
    
    @Test
    public void getActiveLectureMoodInvokesGetLectureAction() {
    	this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLectureMood() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getViewName(), is("lecturer/activeLectureMood"));
    }
    
    @Test
    public void getActiveLectureMoodSendsLectureDtoAsModel() {
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureMoodSendsCorrectMoodMapAsModel() {
    	Map<String, Integer> moodMap = new HashMap<String, Integer>();
    	moodMap.put("HAPPY", numberOfSameMood);
    	moodMap.put("SAD", 0);
    	moodMap.put("CONFUSED", 0);
    	
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getModel().get("moodMap"), is(moodMap));
    }

    @Test
    public void toggleCommentingReturnsActiveLecturePage() {
     	assertThat(this.lectureController.toggleCommenting(lectureDto.getTitle(), "").getViewName(), is("lecturer/activeLecture"));
    }
    
    @Test
    public void toggleCommentingInvokesDisableCommentingIfToggleIsDisable() {
    	this.lectureController.toggleCommenting(lectureDto.getTitle(), "Disable");
    	verify(this.mockToggleFeaturesAction).disableCommenting(lectureDto);
    }
    
    @Test
    public void toggleCommentingInvokesEnableCommentingIfToggleIsEnable() {
    	this.lectureController.toggleCommenting(lectureDto.getTitle(), "Enable");
    	verify(this.mockToggleFeaturesAction).enableCommenting(lectureDto);
    }
    
    @Test
    public void toggleMoodReturnsActiveLectureMoodPage() {
    	assertThat(this.lectureController.toggleMood(lectureDto.getTitle(), "").getViewName(), is("lecturer/activeLectureMood"));
    }
    
    @Test
    public void toggleMoodInvokesDisableMoodIfToggleIsDisable() {
    	this.lectureController.toggleMood(lectureDto.getTitle(), "Disable");
    	verify(this.mockToggleFeaturesAction).disableMood(lectureDto);
    }
    
    @Test
    public void toggleMoodInvokesEnableMoodIfToggleIsEnable() {
    	this.lectureController.toggleMood(lectureDto.getTitle(), "Enable");
    	verify(this.mockToggleFeaturesAction).enableMood(lectureDto);
    }
    
    @Test
    public void endLectureInvokesEndLectureAction() {
    	this.lectureController.endLecture(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockEndLectureAction).endLecture(lectureDto);
    }
        
    @Test
    public void endLectureRedirectsToFeedbackPage() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.endLecture(lectureDto.getTitle()).getViewName(), is("redirect:/lecturer/lecture/active/" + this.lectureDto.getTitle() + "/feedback"));
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLectureFeedback() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getViewName(), is("lecturer/activeLectureFeedback"));
    }
    
    @Test
    public void getActiveLectureFeedbackSendsLectureDtoAsModel() {
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureFeedbackInvokesGetAllFeedbackAction() {
    	this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetAllFeedbackAction).getAll(lectureDto.getTitle());
    }
       
    @Test
    public void getActiveLectureFeedbackSendsCorrectAverageRatingAsModel() {
    	FeedbackDtoList feedbackDtoList = TestFeedbackDtoList.aFilledFeedbackDtoList(3);
    	double averageRatingCalculated = feedbackDtoList.getFeedbackDtoList().stream().mapToDouble(FeedbackDto::getRating).average().getAsDouble();
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getModel().get("averageRating"), is(averageRatingCalculated));
    }
    
    @Test
    public void getActiveLectureFeedbackSendsZeroAverageRatingAsModelIfNoFeedback() {
    	when(this.mockGetAllFeedbackAction.getAll(lectureDto.getTitle())).thenReturn(TestFeedbackDtoList.anEmptyFeedbackDtoList());
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getModel().get("averageRating"), is(0.0));
    }
    
    @Test
    public void getActiveLectureFeedbackSendsFeedbackDtoListAsModel() {
    	FeedbackDtoList feedbackDtoList = TestFeedbackDtoList.aFilledFeedbackDtoList(3);
    	when(this.mockGetAllFeedbackAction.getAll(lectureDto.getTitle())).thenReturn(feedbackDtoList);
    	
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getModel().get("feedbackDtoList"), is(feedbackDtoList));
    }
    
    @Test
    public void toggleFeedbackReturnsActiveLectureFeedbackPage() {
    	assertThat(this.lectureController.toggleFeedback(lectureDto.getTitle(), "").getViewName(), is("lecturer/activeLectureFeedback"));
    }
    
    @Test
    public void toggleFeedbackInvokesDisableFeedbackIfToggleIsDisable() {
    	this.lectureController.toggleFeedback(lectureDto.getTitle(), "Disable");
    	verify(this.mockToggleFeaturesAction).disableFeedback(lectureDto);
    }
    
    @Test
    public void toggleFeedbackInvokesEnableFeedbackIfToggleIsEnable() {
    	this.lectureController.toggleFeedback(lectureDto.getTitle(), "Enable");
    	verify(this.mockToggleFeaturesAction).enableFeedback(lectureDto);
    }
    
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLectureQuestions() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle()).getViewName(), is("lecturer/activeLectureQuestions"));
    }
    
    @Test
    public void getActiveLectureQuestionsSendsLectureDtoAsModel() {
    	assertThat(this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureQuestionsInvokesGetAllQuestionsAction() {
    	this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetAllQuestionsAction).getAll(lectureDto.getTitle());
    }
    
    @Test
    public void getActiveLectureQuestionsSendsQuestionDtoListAsModel() {
    	QuestionDtoList questionDtoList = TestQuestionDtoList.aFilledQuestionDtoList(3);
    	when(this.mockGetAllQuestionsAction.getAll(lectureDto.getTitle())).thenReturn(questionDtoList);
    	
    	assertThat(this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle()).getModel().get("questionDtoList"), is(questionDtoList));
    }
}
