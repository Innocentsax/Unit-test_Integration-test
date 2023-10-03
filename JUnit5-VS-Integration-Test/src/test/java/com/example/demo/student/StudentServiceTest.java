package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    //private static AutoCloseable autoCloseable;
    private StudentService studentService;


    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

//    @AfterAll
//    static void afterAll() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void canGetAllStudents(){
        // When
        studentService.getAllStudents();
        // Then
        verify(studentRepository).findAll();
    }
    @Test
    void addStudents(){
        // Given
        Student student = new Student(
                "INNOCENT UDO",  "Innocent@gmail.com", Gender.MALE
        );
        // when
        studentService.addStudent(student);
        // Then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student captureStudent = studentArgumentCaptor.getValue();
        assertThat(captureStudent).isEqualTo(student);
    }

    @Test
    void whenEmailIsTaken(){
        // Given
        Student student = new Student(
                "Charles", "charles@gmail.com", Gender.MALE
        );
        //given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);
        given(studentRepository.selectExistsEmail(anyString())).willReturn(true);

        // When & Then
        assertThatThrownBy(()-> studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());
    }
    @Test
    @Disabled
    void deleteStudent(){

    }
}