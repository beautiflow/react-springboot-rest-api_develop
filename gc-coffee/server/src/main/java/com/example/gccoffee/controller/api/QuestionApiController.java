package com.example.gccoffee.controller.api;

import com.example.gccoffee.dto.AnswerDto.AnswerSaveRequestDto;
import com.example.gccoffee.dto.AnswerDto.ResponseDto;
import com.example.gccoffee.dto.QuestionDto.QuestionSaveRequestDto;
import com.example.gccoffee.dto.QuestionDto.QuestionUpdateRequestDto;
import com.example.gccoffee.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("/api/v1/question")
    public Long save(@RequestBody QuestionSaveRequestDto requestDto) {
        return questionService.save(requestDto);
    }

    @PutMapping("/api/v1/question/{id}")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto requestDto) {
        System.out.println(requestDto.getContent());
        System.out.println(requestDto.getTitle());
        return questionService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/question/{id}")
    public Long delete(@PathVariable Long id) {
        questionService.delete(id);
        return id;
    }

    @PostMapping("/api/question/{questionId}/answer")
    public ResponseDto<Integer> answerSave(@RequestBody AnswerSaveRequestDto answerSaveRequestDto){
        questionService.commentSave(answerSaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/question/{questionId}/answer/{answerId}")
    public ResponseDto<Integer> answerDelete(@PathVariable Long answerId){
        questionService.commentDelete(answerId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
