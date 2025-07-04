package leets.leenk.global.common.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class StatusCheckController {

    @GetMapping("/health-check")
    public ResponseEntity<String> checkHealthStatus() {

        return ResponseEntity.ok("OK");
    }
}
