package Testes;

import Service.Anagrama;
import org.junit.Test;
import Exception.EntradaInvalidaException;

import java.util.List;

import static org.junit.Assert.*;

public class AnagramaTestes {

    /*
     * Teste para garantir que o método 'gerarAnagramas' funciona corretamente com um input válido.
     * O teste verifica que todos os 6 anagramas possíveis da palavra "abc" são gerados corretamente.
     *
     * A função 'gerarAnagramas' deve retornar 6 anagramas distintos:
     * - "abc", "acb", "bac", "bca", "cab", "cba".
     */
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

    /*
     * Teste para verificar que o método 'gerarAnagramas' lança uma exceção do tipo
     * 'EntradaInvalidaException' quando a entrada fornecida está vazia.
     *
     * A exceção deve ser lançada, pois o input não contém caracteres válidos para gerar anagramas.
     */
    @Test
    public void testGerarAnagramasComInputVazia(){
        Anagrama anagrama = new Anagrama();

        assertThrows(EntradaInvalidaException.class, () -> {
            anagrama.gerarAnagramas("", "");
        });
    }

    /*
     * Teste para verificar o comportamento do método 'gerarAnagramas' quando o input contém
     * apenas um único caractere. Nesse caso, deve-se retornar um único anagrama, que é a própria
     * string fornecida.
     *
     * A função 'gerarAnagramas' deve retornar apenas 1 anagrama: "a".
     */
    @Test
    public void testGerarAnagramasComCaractereUnico() throws EntradaInvalidaException {
        Anagrama anagrama = new Anagrama();

        List<String> anagramas = anagrama.gerarAnagramas("a", "");

        assertEquals(1, anagramas.size());
        assertTrue(anagramas.contains("a"));
    }
}
