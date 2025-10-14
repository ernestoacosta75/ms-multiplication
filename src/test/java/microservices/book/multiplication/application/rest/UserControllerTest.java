package microservices.book.multiplication.application.rest;

import microservices.book.multiplication.application.dto.UserDto;
import microservices.book.multiplication.application.ports.input.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockitoBean
    private IUserService userService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<List<UserDto>> jsonRequestUser;

    @Test
    void getUserIdsTest() throws Exception {
        // given
        UserDto user1 = new UserDto(1L, "jane");
        UserDto user2 = new UserDto(2L, "john");
        given(userService.findAllById(List.of(1L, 2L))).willReturn(List.of(user1, user2));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/users/1,2"))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonRequestUser.write(List.of(user1, user2)).getJson()
        );
    }
}