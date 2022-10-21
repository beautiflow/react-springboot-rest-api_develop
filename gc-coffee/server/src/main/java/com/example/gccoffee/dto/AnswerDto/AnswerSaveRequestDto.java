package com.example.gccoffee.dto.AnswerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerSaveRequestDto {
    private Long userId;
    private Long questionId;
    private String comment;
}
