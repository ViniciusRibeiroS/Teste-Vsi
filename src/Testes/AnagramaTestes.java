package Testes;

import Service.Anagrama;
import org.junit.Test;
import Exception.EntradaInvalidaException;

import java.util.List;

import static org.junit.Assert.*;

public class AnagramaTestes {

    @Test
    public void testGerarAnagramasComInputValida() throws EntradaInvalidaException {
        Anagrama anagrama = new Anagrama();
        List<String> anagramas = anagrama.gerarAnagramas("abc", "");

        assertEquals(6, anagramas.size());
        assertTrue(anagramas.contains("abc"));
        assertTrue(anagramas.contains("acb"));
        assertTrue(anagramas.contains("bac"));
        assertTrue(anagramas.contains("bca"));
        assertTrue(anagramas.contains("cab"));
        assertTrue(anagramas.contains("cba"));
    }

    @Test
    public void testGerarAnagramasComInputVazia(){
        Anagrama anagrama = new Anagrama();

        assertThrows(EntradaInvalidaException.class, () -> {
            anagrama.gerarAnagramas("", "");
        });
    }

    @Test
    public void testGerarAnagramasComCaractereUnico() throws EntradaInvalidaException {
        Anagrama anagrama = new Anagrama();

        List<String> anagramas = anagrama.gerarAnagramas("a", "");

        assertEquals(1, anagramas.size());
        assertTrue(anagramas.contains("a"));
    }
}
