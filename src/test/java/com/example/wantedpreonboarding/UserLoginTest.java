package com.example.wantedpreonboarding;

import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.example.wantedpreonboarding.user.repository.UserRepository;
import com.example.wantedpreonboarding.user.service.UserServiceImpl;
import com.example.wantedpreonboarding.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserLoginTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        // Given
        String username = "testuser";
        String password = "testpassword";
        UserEntity userEntity = UserEntity.builder()
                .userName(username)
                .userPassword(password)
                .build();

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(userEntity));

        // When
        UserDetails userDetails = userServiceImpl.loadUserByUsername(username);

        // Then
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        verify(userRepository, times(1)).findByUserName(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Given
        String username = "nonexistentuser";

        when(userRepository.findByUserName(username)).thenReturn(Optional.empty());

        // When / Then
        // UsernameNotFoundException가 발생해야 테스트 통과
        assertThrows(UsernameNotFoundException.class, () -> {
            userServiceImpl.loadUserByUsername(username);
        });

        verify(userRepository, times(1)).findByUserName(username);
    }

    @Test
    @DisplayName("jwt가 정상적으로 생성되었는지 확인")
    void testGenerateJwt(){
        // given
        long userId = 99L;
        String username = "testuser";
        String userEmail = "test@example.com";
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .userName(username)
                .userEmail(userEmail)
                .build();

        // when
        String token = jwtUtil.generateToken(userEntity);

        // then
        assertNotNull(token);
        assertEquals(userId, jwtUtil.getUserField(token, JwtConstant.USERID));
        assertEquals(username, jwtUtil.getUserField(token, JwtConstant.USERNAME));
        assertEquals(userEmail, jwtUtil.getUserField(token, JwtConstant.USEREMAIL));
    }
}