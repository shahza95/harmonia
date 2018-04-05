package syed.shahza.harmonia.frontend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static syed.shahza.harmonia.backend.dto.TestLectureDto.aValidLectureDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import syed.shahza.harmonia.backend.dto.CommentDto;
import syed.shahza.harmonia.backend.dto.CommentDtoList;
import syed.shahza.harmonia.backend.dto.FeedbackDto;
import syed.shahza.harmonia.backend.dto.LectureDto;
import syed.shahza.harmonia.backend.dto.MoodDto;
import syed.shahza.harmonia.backend.dto.QuestionDto;
import syed.shahza.harmonia.backend.dto.QuestionDtoList;
import syed.shahza.harmonia.backend.dto.TestCommentDto;
import syed.shahza.harmonia.backend.dto.TestCommentDtoList;
import syed.shahza.harmonia.backend.dto.TestFeedbackDto;
import syed.shahza.harmonia.backend.dto.TestLectureDto;
import syed.shahza.harmonia.backend.dto.TestMoodDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDto;
import syed.shahza.harmonia.backend.dto.TestQuestionDtoList;
import syed.shahza.harmonia.restapi.action.AddCommentAction;
import syed.shahza.harmonia.restapi.action.AddFeedbackAction;
import syed.shahza.harmonia.restapi.action.AddQuestionAction;
import syed.shahza.harmonia.restapi.action.GetAllCommentsAction;
import syed.shahza.harmonia.restapi.action.GetAllQuestionsAction;
import syed.shahza.harmonia.restapi.action.GetLectureAction;
import syed.shahza.harmonia.restapi.action.RemoveMoodAction;
import syed.shahza.harmonia.restapi.action.SendMoodAction;

@RunWith(MockitoJUnitRunner.class)
public class ActiveLectureControllerStudentTest {
    private ActiveLectureControllerStudent lectureController;
    private LectureDto lectureDto;
    private CommentDto commentDto;
    private MoodDto moodDto;
    private FeedbackDto feedbackDto;
    private QuestionDto questionDto;
    private String moodString;
    private String title;
    
    @Mock
    private GetLectureAction mockGetLectureAction;
    
    @Mock
    private AddCommentAction mockAddCommentAction;
    
    @Mock
    private GetAllCommentsAction mockGetAllCommentsAction;
    
    @Mock
    private SendMoodAction mockSendMoodAction;
    
    @Mock
    private RemoveMoodAction mockRemoveMoodAction;
    
    @Mock
    private AddFeedbackAction mockAddFeedbackAction;
    
    @Mock
    private AddQuestionAction mockAddQuestionAction;
    
    
    @Mock
    private GetAllQuestionsAction mockGetAllQuestionsAction;
    
    @Mock
    private RedirectAttributes mockRedirectAttributes;
    
    @Captor
    private ArgumentCaptor<MoodDto> moodDtoCaptor;
    
    @Before
    public void before() {
    	this.lectureDto = aValidLectureDto().build();
    	this.commentDto = TestCommentDto.aValidCommentDto().build();
    	this.moodDto = TestMoodDto.aValidMoodDto().build();
    	this.feedbackDto = TestFeedbackDto.aValidFeedbackDto().build();
    	this.questionDto = TestQuestionDto.aValidQuestionDto().build();
    	this.moodString = moodDto.getEmotionDto().toString() + " " + moodDto.getEmoji();
    	this.title = "title";
        this.lectureController = new ActiveLectureControllerStudent(this.mockGetLectureAction, this.mockGetAllCommentsAction, this.mockAddCommentAction, this.mockSendMoodAction, this.mockRemoveMoodAction, this.mockAddFeedbackAction, this.mockAddQuestionAction, this.mockGetAllQuestionsAction);
        when(this.mockGetLectureAction.get(lectureDto.getTitle())).thenReturn(lectureDto);
    }
    
    @Test
    public void getActiveLectureInvokesGetLectureAction() {
    	this.lectureController.getActiveLecturePage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
        
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLecture() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getViewName(), is("student/activeLecture"));
    }
    
    @Test
    public void getActiveLectureSendsLectureDtoAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureSendsCommentDtoListAsModel() {
    	CommentDtoList commentDtoList = TestCommentDtoList.aFilledCommentDtoList(3);
    	when(this.mockGetAllCommentsAction.getAll(lectureDto.getTitle())).thenReturn(commentDtoList);
    	
    	assertThat(this.lectureController.getActiveLecturePage(lectureDto.getTitle()).getModel().get("commentDtoList"), is(commentDtoList));
    } 
    
    @Test
    public void addCommentInvokesGetLectureAction() {
    	this.lectureController.addComment(title, commentDto);
    	
    	verify(this.mockGetLectureAction).get(title);
    }
    
    @Test
    public void addCommentInvokesAddCommentAction() {
       	when(this.mockGetLectureAction.get(title)).thenReturn(this.lectureDto);
    	this.lectureController.addComment(title, commentDto);
    	  
    	verify(this.mockAddCommentAction).addComment(commentDto);
    }
    
    @Test
    public void addCommentRedirectsToActiveLecturePageSoItself() {
       	when(this.mockGetLectureAction.get(title)).thenReturn(this.lectureDto);
    	when(this.mockAddCommentAction.addComment(commentDto)).thenReturn(commentDto);
    	
    	assertThat(this.lectureController.addComment(title, commentDto).getViewName(), is("redirect:/student/lecture/active/" + title + "/comments"));
    }
    
    @Test
    public void getActiveLectureMoodPageInvokesGetLectureAction() {
    	this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle());
    	verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
    
    @Test
    public void getActiveLectureMoodPageSendsLectureAsModel() {
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getModelMap().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureMoodPageReturnsCorrectView() {
    	assertThat(this.lectureController.getActiveLectureMoodPage(lectureDto.getTitle()).getViewName(), is("student/activeLectureMood"));
    }
    
    @Test
    public void sendMoodInvokesGetLectureAction() {
    	this.lectureController.sendMood(lectureDto.getTitle(), this.moodString, "", this.mockRedirectAttributes);
    	verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
    
    @Test
    public void sendMoodSendsInvokesSendMoodActionWithCorrectlyConstructedMoodDto() {
    	this.lectureController.sendMood(lectureDto.getTitle(), this.moodString, "", this.mockRedirectAttributes);
    	String[] moodParts = this.moodString.split(" ");
    	
    	this.moodDtoCaptor = ArgumentCaptor.forClass(MoodDto.class);
    	verify(this.mockSendMoodAction).sendMood(this.moodDtoCaptor.capture());
    	
    	assertThat(this.moodDtoCaptor.getValue().getEmotionDto().toString(), is(moodParts[0]));
    	assertThat(this.moodDtoCaptor.getValue().getEmoji(), is(moodParts[1]));
    }
    
    @Test
    public void sendMoodRedirectsToCorrectView() {
    	assertThat(this.lectureController.sendMood(lectureDto.getTitle(), this.moodString, "", this.mockRedirectAttributes).getViewName(), is("redirect:/student/lecture/active/" + lectureDto.getTitle() + "/mood"));
    }
    
    @Test
    public void sendMoodAddsCorrectRedirectAttributeOfCurrentEmoji() {
    	//current emoji gets updated by new selection
    	this.lectureController.sendMood(lectureDto.getTitle(), this.moodString, "", this.mockRedirectAttributes);
    	verify(this.mockRedirectAttributes).addFlashAttribute("currentEmoji", this.moodString.split(" ")[1]);
    }
    
    @Test
    public void sendMoodInvokesRemoveMoodActionIfCurrentEmojiExists() {
    	String emoji = ":)";
    	this.lectureController.sendMood(lectureDto.getTitle(), this.moodString, emoji, this.mockRedirectAttributes);
    	verify(this.mockRemoveMoodAction).removeMoodByEmoji(this.title, emoji);
    }
    
    @Test
    public void sendMoodDoesNotInvokeRemoveMoodActionIfNoCurrentEmojiExists() {
    	String emoji = "";
    	this.lectureController.sendMood(lectureDto.getTitle(), this.moodString, emoji, this.mockRedirectAttributes);
    	verify(this.mockRemoveMoodAction, times(0)).removeMoodByEmoji(this.title, emoji);
    }
    
    @Test
    public void getActiveLectureFeedbackPageReturnsCorrectView() {
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getViewName(), is("student/activeLectureFeedback"));
    }
    
    @Test
    public void getActiveLectureFeedbackPageSendsLectureAsModel() {
    	assertThat(this.lectureController.getActiveLectureFeedbackPage(lectureDto.getTitle()).getModelMap().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void addFeedbackInvokesGetLectureAction() {
    	this.lectureController.addFeedback(title, feedbackDto);
    	
    	verify(this.mockGetLectureAction).get(title);
    }
    
    @Test
    public void addFeedbackInvokesAddFeedbackAction() {
    	this.lectureController.addFeedback(title, this.feedbackDto);
    	
    	verify(this.mockAddFeedbackAction).addFeedback(this.feedbackDto);
    }
    
    @Test
    public void addFeedbackRedirectsToJoinLecturePage() {
       	when(this.mockGetLectureAction.get(title)).thenReturn(this.lectureDto);
    	when(this.mockAddFeedbackAction.addFeedback(this.feedbackDto)).thenReturn(this.feedbackDto);
    	
    	assertThat(this.lectureController.addFeedback(title, this.feedbackDto).getViewName(), is("redirect:/student/lecture/join"));
    }
    
    @Test
    public void addQuestionInvokesGetLectureAction() {
    	this.lectureController.addQuestion(title, questionDto);
    	
    	verify(this.mockGetLectureAction).get(title);
    }
    
    @Test
    public void addQuestionInvokesAddQuestionAction() {
    	this.lectureController.addQuestion(title, this.questionDto);
    	
    	verify(this.mockAddQuestionAction).addQuestion(this.questionDto);
    }
    
    @Test
    public void addQuestionRedirectsToActiveLectureQuestionsPageSoItself() {
    	when(this.mockGetLectureAction.get(title)).thenReturn(this.lectureDto);
    	when(this.mockAddQuestionAction.addQuestion(this.questionDto)).thenReturn(this.questionDto);
    	
    	assertThat(this.lectureController.addQuestion(title, this.questionDto).getViewName(), is("redirect:/student/lecture/active/" + title + "/questions"));
    }
    
    @Test
    public void getActiveLectureQuestionInvokesGetLectureAction() {
    	this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle());
    	
    	Mockito.verify(this.mockGetLectureAction).get(lectureDto.getTitle());
    }
        
    @Test
    public void controllerServesUpCorrectThymeleafPageOnGetForActiveLectureQuestions() {
    	LectureDto lectureDto = TestLectureDto.aValidLectureDto().build();
    	assertThat(this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle()).getViewName(), is("student/activeLectureQuestions"));
    }
    
    @Test
    public void getActiveLectureQuestionsSendsLectureDtoAsModel() {
    	QuestionDtoList questionDtoList = TestQuestionDtoList.aFilledQuestionDtoList(3);
    	when(this.mockGetAllQuestionsAction.getAll(lectureDto.getTitle())).thenReturn(questionDtoList);
    
    	assertThat(this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle()).getModel().get("lectureDto"), is(lectureDto));
    }
    
    @Test
    public void getActiveLectureQuestionsSendsQuestionDtoListAsModel() {
    	QuestionDtoList questionDtoList = TestQuestionDtoList.aFilledQuestionDtoList(3);
    	when(this.mockGetAllQuestionsAction.getAll(lectureDto.getTitle())).thenReturn(questionDtoList);
    	
    	assertThat(this.lectureController.getActiveLectureQuestionsPage(lectureDto.getTitle()).getModel().get("questionDtoList"), is(questionDtoList));
    } 
}
