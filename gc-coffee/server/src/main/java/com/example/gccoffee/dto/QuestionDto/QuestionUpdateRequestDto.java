package com.example.gccoffee.dto.QuestionDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public QuestionUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
