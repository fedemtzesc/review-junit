/*
 * CICLO DE VIDA DE LAS PRUEBAS UNITARIAS:
 * 
 * Secuencia de Ejecucion de los decoradores de ciclo de vida:
 * 
 * 	@BeforeAll -->	@BeforeEach	-->	@Test --> @AfterEach --> @AfterAll	
 * 
 *  ***	NOTAS: 
 * 		@Test viene a ser la prueba unitaria
 */

package com.fdxsoft.junit.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

class CalculadoraTest {
	Calculadora calc;

	@BeforeAll
	public static void beforeAllMethod() {
		System.out.println("Se ejecuta al PRINCIPIO de TODAS las PRUEBAS UNITARIAS, por unica vez.");
	}
	
	@AfterAll
	public static void afterAllMethod() {
		System.out.println("Se ejecuta al FINAL de TODAS las PRUEBAS UNITARIAS, por unica vez.");
	}

	@BeforeEach
	public void beforeEachMethod() {
		calc = new Calculadora();
		System.out.println("Se ejecuta al PRINCIPIO de CADA PRUEBA UNITARIA");
	}

	@AfterEach
	public void afterEachMethod() {
		calc = null;
		System.out.println("Se ejecuta al FINAL de CADA PRUEBA UNITARIA");
	}

	@Test
	public void sumarTest() {
		assertEquals(7, calc.sumar(5, 2));
		System.out.println("...SUMA...");
	}

	@Test
	@DisplayName("Prueba de restar con assertEquals y assertNotEquals")
	@Disabled("Esta prueba la ignoramos por que se usaron dos asserts. Hay que separarlas.")
	public void restarTest() {
		assertEquals(3, calc.restar(5, 2));
		assertNotEquals(4, calc.restar(5, 2));
		System.out.println("...RESTA...");
	}

	@Test
	public void dividirTest() {
		assertTrue(calc.dividir(10, 2) == 5);
		System.out.println("...DIVISION...");
	}

	@Test
	@DisplayName("Probando multiplicacion con assertfalse")
	public void multiplicarTest() {
		assertFalse(calc.multiplicar(10, 2) == 2);
		System.out.println("...MULTIPLICACION...");
	}

	@Test
	@DisplayName("Comparacion de arreglos con assertArrayEquals")
	public void arregloTest() {
		String[] arr1 = { "a", "b", "c", "d" };
		String[] arr2 = { "a", "b", "c", "d" };
		String[] arr3 = { "a", "b", "c", "e" };

		assertArrayEquals(arr1, arr2);
		// assertArrayEquals(arr1, arr3); //Con este test falla

	}

	@Test
	@DisplayName("Comparacion de objectos con assertSame y assertNotSame")
	public void compareObjects() {
		String str1 = "Hola Mundo";
		String str2 = str1;
		String str3 = "Hola Culeros!";

		assertSame(str2, str1);
		assertNotSame(str2, str3);
		// assertSame(str2, str3); //Este test falla porque no son el mismo objeto
	}

	@Test
	@DisplayName("Prueba para cuando se dispara, o no se dispara, una excepcion")
	public void divisionByZeroExceptionMathod() {
		assertThrows(ArithmeticException.class, () -> calc.dividir(7, 0));
		assertDoesNotThrow(()-> calc.dividir(10, 2));
	}

}
