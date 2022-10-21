package com.example.gccoffee.service;

import com.example.gccoffee.dto.AnswerDto.AnswerSaveRequestDto;
import com.example.gccoffee.dto.QuestionDto.QuestionListResponseDto;
import com.example.gccoffee.dto.QuestionDto.QuestionResponseDto;
import com.example.gccoffee.dto.QuestionDto.QuestionSaveRequestDto;
import com.example.gccoffee.dto.QuestionDto.QuestionUpdateRequestDto;
import com.example.gccoffee.model.Answer;
import com.example.gccoffee.model.Question;
import com.example.gccoffee.repository.AnswerRepository;
import com.example.gccoffee.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto requestDto) {
        System.out.println(requestDto);
        return questionRepository.save(requestDto.toEntity()).getId();
    }

    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 질문이 없습니다. id=" + id));
        return new QuestionResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<QuestionListResponseDto> findAllDesc(){
        return questionRepository.findAllDesc().stream()
                .map(QuestionListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto requestDto) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 질문이 없습니다. id="+id));
        question.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다. id = " + id));
        questionRepository.delete(question);
    }

    @Transactional
    public void commentSave(AnswerSaveRequestDto answerSaveRequestDto) {
        Answer answer;
        answer = new Answer(
                answerSaveRequestDto.getComment(),
                questionRepository.findById(answerSaveRequestDto.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("질문 찾기 실패"))
        );
        answerRepository.save(answer);
    }

    @Transactional
    public void commentDelete(Long answerId){
        answerRepository.deleteById(answerId);
    }
}
