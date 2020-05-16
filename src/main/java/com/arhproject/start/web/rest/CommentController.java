package com.arhproject.start.web.rest;

import com.arhproject.start.jpa.domain.Comment;
import com.arhproject.start.service.CommentService;
import com.arhproject.start.web.dto.comment.CommentModel;
import com.arhproject.start.web.dto.comment.CreateCommentDto;
import com.arhproject.start.web.dto.failure.FailureBodyModel;
import com.arhproject.start.web.mapper.CommentMapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 401, response = FailureBodyModel.class, message = "UNAUTHORIZED"),
            @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND"),
            @ApiResponse(code = 422, response = FailureBodyModel.class, message = "UNPROCESSABLE ENTITY")
    })
    public ResponseEntity<CommentModel> create(@RequestBody @Valid CreateCommentDto commentDto) {
        Comment comment = commentService.create(commentDto.getPublicationId(), commentDto.getText());
        CommentModel commentModel = commentMapper.toModel(comment);
        return ResponseEntity.ok(commentModel);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND")
    })
    public ResponseEntity<CommentModel> get(@PathVariable Long id) {
        Comment comment = commentService.get(id);
        CommentModel commentModel = commentMapper.toModel(comment);
        return ResponseEntity.ok(commentModel);
    }

    @GetMapping
    public ResponseEntity<List<CommentModel>> getAll() {
        List<CommentModel> commentModelList = commentService.getAll().stream()
                .map(commentMapper::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentModelList);
    }
/*
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
        @ApiResponse(code = 401, response = FailureBodyModel.class, message = "UNAUTHORIZED"),
        @ApiResponse(code = 403, response = FailureBodyModel.class, message = "FORBIDDEN"),
        @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND"),
        @ApiResponse(code = 422, response = FailureBodyModel.class, message = "UNPROCESSABLE ENTITY")
    })
    public ResponseEntity<CommentModel> update(@PathVariable Long id,
                                               @RequestBody @Valid UpdateCommentDto commentDto) {
        Comment comment = commentService.update(id, commentDto.getText());
        CommentModel commentModel = commentMapper.toModel(comment);
        return ResponseEntity.ok(commentModel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
        @ApiResponse(code = 401, response = FailureBodyModel.class, message = "UNAUTHORIZED"),
        @ApiResponse(code = 403, response = FailureBodyModel.class, message = "FORBIDDEN"),
        @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND"),
        @ApiResponse(code = 422, response = FailureBodyModel.class, message = "UNPROCESSABLE ENTITY")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }
 */
}
