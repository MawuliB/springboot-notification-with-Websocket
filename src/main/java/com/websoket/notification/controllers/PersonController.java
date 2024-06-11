package com.websoket.notification.controllers;

import com.websoket.notification.entities.request.PersonRequest;
import com.websoket.notification.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/save")
    public void savePerson(@RequestBody PersonRequest person){
        personService.savePerson(person);
    }
}

