package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class practicesTest {
    Calculate calculate = new Calculate();

    @Test
    void CheckAdd(){
        // Given
        int a = 10;
        int b = 20;

        // when
        int result = calculate.add(a, b);

        // Then
        int expected = 30;
        assertThat(result).isEqualTo(expected);
    }
    class Calculate{
        int add(int a, int b){
            return a + b;
        }
    }
}
