package com.example.FitnessCenter.model.dto;

import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class ApplyDTO {

    private Long id;
    private Integer grade;
    private Boolean done;

    private User sports_man;
    private Term term;

    public ApplyDTO() {
    }

    public ApplyDTO(Long id, Integer grade, Boolean done) {
        this.id = id;
        this.grade = grade;
        this.done = done;
    }

    public ApplyDTO(Long id, Integer grade, Boolean done, User sports_man, Term term) {
        this.id = id;
        this.grade = grade;
        this.done = done;
        this.sports_man = sports_man;
        this.term = term;
    }
}
