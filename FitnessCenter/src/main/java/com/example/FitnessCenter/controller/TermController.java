package com.example.FitnessCenter.controller;

import com.example.FitnessCenter.model.Term;
import com.example.FitnessCenter.model.dto.TermDTO;
import com.example.FitnessCenter.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/terms")
public class TermController {

    private final TermService termService;

    @Autowired
    public TermController(TermService termService) {
        this.termService = termService;
    }

    @GetMapping(value = "/price/{price}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TermDTO>> getTermsPrice(@PathVariable Double price){

        List<Term> termList = this.termService.findAllPrice(price);

        List<TermDTO> termDTOS = new ArrayList<>();

        for(Term term : termList){
            TermDTO termDTO = new TermDTO(term.getId(), term.getPrice(), term.getStart().toString()
                    , term.getNumber_of_applications());
            termDTOS.add(termDTO);
        }
        return new ResponseEntity<>(termDTOS, HttpStatus.OK);
    }

}
