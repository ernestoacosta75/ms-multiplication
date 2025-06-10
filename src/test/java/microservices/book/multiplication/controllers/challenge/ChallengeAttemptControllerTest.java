package microservices.book.multiplication.controllers.challenge;

import microservices.book.multiplication.domain.ChallengeAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.dtos.challenge.ChallengeAttemptDto;
import microservices.book.multiplication.service.interfaces.challenge.IChallengeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)
class ChallengeAttemptControllerTest {

    @MockitoBean
    private IChallengeService challengeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeAttemptDto> jsonRequestAttempt;

    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;

    @Test
    void postValidResult() throws Exception {
        // given
        User user = new User(1L, "john");
        long attemptId = 5L;
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 70, "john", 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user.getId(), 50, 70, 3500, true);

        given(challengeService
                .verifyAttempt(eq(attemptDto)))
                .willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(attemptDto).getJson()))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResultAttempt.write(expectedResponse).getJson());
    }
}