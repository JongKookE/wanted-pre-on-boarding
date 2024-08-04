package com.example.wantedpreonboarding.user.controller;

import com.example.wantedpreonboarding.user.dto.UserDto;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import com.example.wantedpreonboarding.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "User", description = "초기 테스트")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
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
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
        userService.userSave(userDto);
        // TODO 적절한 Response 코드 작성하기
        return ResponseEntity.ok().build();
    }

    // MethodArgumentNotValidException 에러 발생시 에러가 발생한 필드와 에러 메시지 출력
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
