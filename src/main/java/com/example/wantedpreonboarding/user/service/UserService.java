package com.example.wantedpreonboarding.user.service;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.example.wantedpreonboarding.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        UserDto userDto = UserDto.builder()
                .userName(userEntity.getUserName())
                .userPassword(userEntity.getUserPassword())
                .build();

        List<GrantedAuthority> authorities = new ArrayList<>();
        return userDto == null ? null : new User(userDto.getUserEmail(), userDto.getUserPassword(), authorities);
    }
}
