package com.example.wantedpreonboarding;

import com.example.wantedpreonboarding.user.dto.UserDto;

import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// WebMvcTest는 컨트롤러 계층만 테스트하기 때문에 스프링 시큐리티 설정을 로드하지 않음
// 따라서 접근이 계속 거부 되었음
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserSaveTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("이메일 형식 실패 테스트")
    public void testEmailValidation() throws Exception {
        // Given
        String username = "testuser";
        String password = "asdD12345!@";
        String userEmail = "testusergmail.com";
        String userPhoneNumber = "010-1234-5678";

        UserDto userDto = UserDto.builder()
                .userName(username)
                .userPassword(password)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
            .build();

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        // When & then
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.userEmail").value("이메일 형식이 맞지 않습니다."));
    }

    @Test
    @DisplayName("비밀번호 형식 실패 테스트")
    public void testPasswordValidation() throws Exception{
        // Given
        String username = "testuser";
        String password = "asdD12345@";
        String userEmail = "testuser@gmail.com";
        String userPhoneNumber = "010-1234-5678";

        UserDto userDto = UserDto.builder()
                .userName(username)
                .userPassword(password)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        // When & then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.userPassword").value("대소문자, 숫자 5개 이상, 특수문자 포함 2개 이상 포함해야합니다."));
    }

    @Test
    @DisplayName("전화번호 형식 실패 테스트")
    public void testPhoneNumberValidation() throws Exception{
        // Given
        String username = "testuser";
        String password = "asdD12345@!";
        String userEmail = "testuser@gmail.com";
        String userPhoneNumber = "010-12345678";

        UserDto userDto = UserDto.builder()
                .userName(username)
                .userPassword(password)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
            .build();

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        // When & then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.userPhoneNumber").value("숫자와 하이픈으로 구성되어야합니다."));
    }

    @Test
    @DisplayName("회원가입 성공")
    public void testSuccessSaveUser() throws Exception{
        // Given
        String username = "testuser";
        String password = "asdD12345@!";
        String userEmail = "testuser@gmail.com";
        String userPhoneNumber = "010-1234-5678";

        UserDto userDto = UserDto.builder()
                .userName(username)
                .userPassword(password)
                .userEmail(userEmail)
                .userPhoneNumber(userPhoneNumber)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        // When & then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
