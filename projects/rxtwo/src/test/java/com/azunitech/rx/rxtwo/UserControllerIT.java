package com.azunitech.rx.rxtwo;


import com.azunitech.rx.rxtwo.model.User;
import com.azunitech.rx.rxtwo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIT {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void setup(){

        userRepository.save(User.builder().id(1L).firstName("James").lastName("Bond").build());
        userRepository.save(User.builder().id(2L).firstName("James").lastName("Farley").build());
        userRepository.save(User.builder().id(3L).firstName("Marley").lastName("Hemp").build());
        userRepository.save(User.builder().id(4L).firstName("James").lastName("Bond").build());

    }

    @Test
    public void test_getById_successfull() throws Exception {
        Assertions.assertEquals("James", userRepository.findByFirstName("James").get(0).getFirstName());
    }

}