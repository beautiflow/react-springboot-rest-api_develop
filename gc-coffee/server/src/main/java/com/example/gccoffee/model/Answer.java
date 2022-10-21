package com.example.gccoffee.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Entity
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    public Answer(String comment, Question question){
        this.comment = comment;
        this.question = question;
    }

    @Builder
    public Answer(String comment){
        this.comment = comment;
    }
}
