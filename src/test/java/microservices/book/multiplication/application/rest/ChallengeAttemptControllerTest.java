package microservices.book.multiplication.application.rest;

import microservices.book.multiplication.application.dto.ChallengeAttemptRequestDto;
import microservices.book.multiplication.application.dto.ChallengeAttemptResponseDto;
import microservices.book.multiplication.domain.model.user.User;
import microservices.book.multiplication.application.ports.input.IChallengeService;
import microservices.book.multiplication.infrastructure.adapters.output.entity.ChallengeAttemptEntity;
import microservices.book.multiplication.infrastructure.adapters.output.entity.UserEntity;
import microservices.book.multiplication.infrastructure.adapters.output.mapper.ChallengeAttemptEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private JacksonTester<ChallengeAttemptRequestDto> jsonRequestAttempt;

    @Autowired
    private JacksonTester<ChallengeAttemptResponseDto> jsonResultAttempt;

    @Autowired
    private JacksonTester<List<ChallengeAttemptResponseDto>> jsonListResultAttempt;

    @Test
    void getStatsForUser() throws Exception {
        // given
        String userAlias = "john";
        UserEntity userEntity = new UserEntity(1L, userAlias);
        ChallengeAttemptEntity challengeAttemptEntity1 = new ChallengeAttemptEntity(1L, userEntity, 50, 60, 3010, false);
        ChallengeAttemptEntity challengeAttemptEntity2 = new ChallengeAttemptEntity(2L, userEntity, 50, 60, 3051, false);
        List<ChallengeAttemptResponseDto> latestAttemptsResult = ChallengeAttemptEntityMapper.MAPPER.map(List.of(challengeAttemptEntity1, challengeAttemptEntity2));

        given(challengeService.getStatsForUser(userAlias))
                .willReturn(latestAttemptsResult);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/attempts")
                        .param("alias", userEntity.getAlias()))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonListResultAttempt.write(latestAttemptsResult).getJson());
    }

    @Test
    void postValidResult() throws Exception {

        // given
        User user = new User(1L, "john");
        long attemptId = 5L;
        ChallengeAttemptRequestDto attemptRequest = new ChallengeAttemptRequestDto(50, 70, "john", 3500);
        ChallengeAttemptResponseDto expectedResponse = new ChallengeAttemptResponseDto(attemptId, user, 50, 70, 3500, true);

        given(challengeService
                .verifyAttempt(eq(attemptRequest)))
                .willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(
                        post("/attempts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestAttempt.write(attemptRequest).getJson()))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResultAttempt.write(expectedResponse).getJson());
    }
}