package com.yaser.controller;

import com.yaser.entities.People;
import com.yaser.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/people")
@AllArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping("/list")
    public ResponseEntity<List<People>> getList() {
        return ResponseEntity.ok(peopleService.getList());
    }

    @PostMapping("/save")
    public ResponseEntity<People> save(@RequestBody People people) {
        return ResponseEntity.ok(peopleService.save(people));
    }

    @GetMapping("/list/{age}")
    public ResponseEntity<List<People>> getListSpecific(@PathVariable(name = "age") Integer age) {
        return ResponseEntity.ok(peopleService.getListSpecificAge(age));
    }
}
