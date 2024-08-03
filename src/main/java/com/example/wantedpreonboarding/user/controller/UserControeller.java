package com.example.wantedpreonboarding.user.controller;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.example.wantedpreonboarding.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/users?name={username}")
    public ResponseEntity<UserDto> findByName(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUserName(username));
    }

    @GetMapping("/users?email={userEmail}")
    public ResponseEntity<UserDto> findByUserEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.findByUserEmail(userEmail));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findUserAll());
    }

    @GetMapping("/users/deleted")
    public ResponseEntity<List<UserDto>> findDeletedAll() {
        return ResponseEntity.ok(userService.findDeletedUser());
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        userService.userSave(userDto);
        // TODO 적절한 Response 코드 작성하기
        return ResponseEntity.ok().build();
    }

}
