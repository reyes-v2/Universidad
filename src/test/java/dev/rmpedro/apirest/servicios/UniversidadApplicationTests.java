package dev.rmpedro.apirest.servicios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UniversidadApplicationTests {

    Calculadora calculadora = new Calculadora();

    @Test
    @DisplayName("Test: Suma de valores a y b")
    void sumarValores() {
        Integer valorA = 72;
        Integer valorB = 18;

        Integer got = calculadora.sumar(valorA, valorB);

        Integer expected = 90;

        assertThat(expected).isEqualTo(got);
    }

    class Calculadora {

        Integer sumar(Integer valorA, Integer valorB) {
            return valorA + valorB;
        }

    }

}
