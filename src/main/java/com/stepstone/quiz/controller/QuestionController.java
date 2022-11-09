package com.stepstone.quiz.controller;

import com.stepstone.quiz.api.request.CheckRequest;
import com.stepstone.quiz.api.response.CheckResponse;
import com.stepstone.quiz.api.response.ErrorResponse;
import com.stepstone.quiz.api.response.QuestionsResponse;
import com.stepstone.quiz.api.response.Response;
import com.stepstone.quiz.controller.converter.ErrorConverter;
import com.stepstone.quiz.controller.converter.QuestionConverter;
import com.stepstone.quiz.data.Question;
import com.stepstone.quiz.service.QuestionService;
import com.stepstone.quiz.service.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/v1")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final ErrorConverter errorConverter;

    public QuestionController(@Autowired QuestionService questionService,
                              @Autowired QuestionConverter questionConverter,
                              @Autowired ErrorConverter errorConverter) {
        this.questionService = questionService;
        this.questionConverter = questionConverter;
        this.errorConverter = errorConverter;
    }

    @GetMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Returns available questions. Allows to filter questions based on the specified parameters.",
            summary = "Get questions for a quiz",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "questions retrieved successfully",
                            content = @Content(schema = @Schema(implementation = QuestionsResponse.class))
                    )
            }
    )
    public ResponseEntity<Response> getQuestions(@RequestParam Optional<String> type) {
        var result = type.isPresent()
                ? questionService.getByType(type.get())
                : questionService.getAll();
        return handleResult(result, r -> {
            var questions = ((Result.Ok<List<Question>>) result).getData().stream()
                    .map(questionConverter::convert)
                    .collect(Collectors.toList());
            return new QuestionsResponse(questions);
        });
    }

    @PostMapping(value = "/questions/{id}/check",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Checks a given answer to the specified question.",
            summary = "Check answer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "questions checked successfully",
                            content = @Content(schema = @Schema(implementation = QuestionsResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "incorrect request",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "question not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public ResponseEntity<Response> check(@PathVariable String id,
                                          @Valid @RequestBody CheckRequest checkRequest) {
        var result = questionService.check(id, checkRequest.getAnswer());
        return handleResult(result, r ->
                new CheckResponse(((Result.Ok<Boolean>) result).getData()));
    }

    private <T> ResponseEntity<Response> handleResult(
            Result<T> result, Function<Result<T>, Response> onSuccess) {

        if (result instanceof Result.Error) {
            var errorResult = (Result.Error<Boolean>) result;
            var errorResponse = new ErrorResponse(errorResult.getMessage());
            var status = errorConverter.convert(errorResult.getType());
            return new ResponseEntity<>(errorResponse, status);
        }
        return new ResponseEntity<>(onSuccess.apply(result), HttpStatus.OK);
    }
}
