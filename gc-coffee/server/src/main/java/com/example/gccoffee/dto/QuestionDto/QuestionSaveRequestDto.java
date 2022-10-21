package com.example.gccoffee.dto.QuestionDto;

import com.example.gccoffee.model.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionSaveRequestDto {
    private String title;
    private String content;

    @Builder
    public QuestionSaveRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Question toEntity(){
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}
