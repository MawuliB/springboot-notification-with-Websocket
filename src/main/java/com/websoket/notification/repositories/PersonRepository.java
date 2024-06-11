package com.websoket.notification.repositories;

import com.websoket.notification.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
