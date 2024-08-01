package com.example.wantedpreonboarding;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.example.wantedpreonboarding.user.repository.UserRepository;
import com.example.wantedpreonboarding.user.service.UserService;
import com.example.wantedpreonboarding.user.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WantedPreOnBoardingApplicationTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    void createUserTest(){
        UserDto userDto = UserDto.builder()
                .userName("John Doe")
                .userPassword("JDoe")
                .userEmail("john.doe@example.com")
                .userPhoneNumber("010-1234-5678")
            .build();

        UserEntity userEntity = UserEntity.builder()
                .userName("John Doe")
                .userPassword("JDoe")
                .userEmail("john.doe@example.com")
                .userPhoneNumber("010-1234-5678")
            .build();

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // 서비스 메서드 호출
        UserEntity createdUser = userService.userSave(userDto);

        // Assertions: createdUser 객체가 null이 아닌지 확인
        assertThat(createdUser).isNotNull();

        // Assertions: createdUser 객체의 userName이 "John Doe"와 같은지 확인
        assertThat(createdUser.getUserName()).isEqualTo("John Doe");

        // Assertions: createdUser 객체의 userEmail이 "john.doe@example.com"와 같은지 확인
        assertThat(createdUser.getUserEmail()).isEqualTo("john.doe@example.com");

        // Verify: userRepository의 save 메서드가 호출되었는지 확인
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}
