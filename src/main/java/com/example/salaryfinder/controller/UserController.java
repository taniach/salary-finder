package com.example.salaryfinder.controller;

import com.example.salaryfinder.domain.CustomSortOrder;
import com.example.salaryfinder.domain.User;
import com.example.salaryfinder.service.UserService;
import com.opencsv.bean.CsvToBeanBuilder;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(name = "min", defaultValue = "0.0") double min,
            @RequestParam(name = "max", defaultValue = "4000.0") double max,
            @RequestParam(name = "offset", defaultValue = "0") long offset,
            @RequestParam(name = "limit") Optional<Long> limit,
            @RequestParam(name = "sort", required = false) CustomSortOrder sort) {
        try {
            return ResponseEntity.ok().body(Map.of("results",
                    userService.getUsers(
                            min,
                            max,
                            offset,
                            limit.orElseGet(() -> userService.getUserCount()),
                            sort)));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "file") MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            userService.uploadData(
                    new CsvToBeanBuilder<User>(reader)
                            .withType(User.class)
                            .build()
                            .parse());
            return ResponseEntity.ok().body(Map.of("success", 1));
        } catch (IOException | RuntimeException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", 0);
            jsonObject.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(jsonObject);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", 0);
            jsonObject.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(jsonObject);
        }
    }
}
