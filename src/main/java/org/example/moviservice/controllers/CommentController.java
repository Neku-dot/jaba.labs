package org.example.moviservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.moviservice.dto.CommentDto;
import org.example.moviservice.services.CommentService;
import org.example.moviservice.services.RequestCounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;
    private final RequestCounterService requestCounterService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        log.info("Trying GET all comments");
        log.info("{}", requestCounterService.incrementAndGet());
        List<CommentDto> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        log.info("Trying GET comment by id");
        log.info("{}", requestCounterService.incrementAndGet());
        CommentDto commentDto = commentService.getCommentById(id);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        log.info("Trying POST comment");
        log.info("{}", requestCounterService.incrementAndGet());
        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        log.info("Trying PUT movie");
        log.info("{}", requestCounterService.incrementAndGet());
        CommentDto updatedComment = commentService.updateComment(id, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        log.info("Trying DEL movie");
        log.info("{}", requestCounterService.incrementAndGet());
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}