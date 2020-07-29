package microservices.book.socialmultiplication.controller;

import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
final class MultiplicationResultAttemptController {
    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt){
        boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
        MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
            multiplicationResultAttempt.getUser(),
            multiplicationResultAttempt.getMultiplication(),
            multiplicationResultAttempt.getResultAttempt(),
            isCorrect
        );

        return ResponseEntity.ok(attemptCopy);
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistics (@RequestParam("alias") String alias){
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }

    static final class ResultResponse {
        private final boolean correct;

        public ResultResponse(boolean correct) {
            this.correct = correct;
        }

        public ResultResponse() {
            this(false);
        }

        public boolean isCorrect() {
            return correct;
        }
    }
}
