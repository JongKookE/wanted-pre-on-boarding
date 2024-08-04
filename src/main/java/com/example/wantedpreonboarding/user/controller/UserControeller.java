package com.example.wantedpreonboarding.user.controller;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.example.wantedpreonboarding.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "초기 테스트")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserControeller {
    private final UserService userService;

    @DeleteMapping
    public ResponseEntity<UserEntity> delete(@RequestParam long userId) {
        userService.softDeleteByUserId(userId);
        // TODO 적절한 Response 코드 작성하기
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name={username}")
    public ResponseEntity<UserDto> findByName(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUserName(username));
    }

    @GetMapping("/email={userEmail}")
    public ResponseEntity<UserDto> findByUserEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.findByUserEmail(userEmail));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findUserAll());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<UserDto>> findDeletedAll() {
        return ResponseEntity.ok(userService.findDeletedUser());
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        userService.userSave(userDto);
        // TODO 적절한 Response 코드 작성하기
        return ResponseEntity.ok().build();
    }

}
