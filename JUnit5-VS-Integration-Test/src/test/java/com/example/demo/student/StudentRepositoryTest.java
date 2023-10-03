package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }


    @Test
    void itShouldCheckIfStudentExistsEmail() {
        // Given
        String email = "Innocent@gmail.com";
        Student student = new Student(
                "Innocent", email, Gender.MALE
        );
        studentRepository.save(student);

        //  When
        boolean expected = studentRepository.selectExistsEmail(email);

        // Then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist(){
        // Given
        String email = "udo@gmail.com";

        // when
        boolean exp = studentRepository.selectExistsEmail(email);

        // Then
        assertThat(exp).isFalse();

    }
}