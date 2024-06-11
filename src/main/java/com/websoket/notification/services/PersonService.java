package com.websoket.notification.services;

import com.websoket.notification.entities.Person;
import com.websoket.notification.entities.request.PersonRequest;
import com.websoket.notification.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public void savePerson(PersonRequest person){
        Person newPerson = Person.builder()
                .name(person.getName())
                .build();
        personRepository.save(newPerson);
    }
}
