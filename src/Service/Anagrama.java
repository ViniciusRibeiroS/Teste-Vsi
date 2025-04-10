package Service;
import Exception.EntradaInvalidaException;

import java.util.*;

public class Anagrama {

    private List<String> listaAnagramas = new ArrayList<>();

    /*
     * Método responsável por validar a entrada para garantir que ela atenda aos requisitos
     * necessários para gerar anagramas.
     *
     * A validação é feita a maneira de não considerar strings vazias ou strings que possuam
     * caracteres diferentes de A-Z e a-z
     *
     * @param inputValidacao A string de entrada que será validada.
     * @throws EntradaInvalidaException Se a entrada não atender aos requisitos estabelecidos.
     */
    public void validarInput(String inputValidacao) throws EntradaInvalidaException {
        if(inputValidacao.isEmpty()){
            throw new EntradaInvalidaException("Input não contém caracteres");
        }
        else if(!inputValidacao.matches("^[a-zA-Z]+$")){
            throw new EntradaInvalidaException("Input contém caracteres além de letras");
        }
    }

    /*
     * Método responsável por gerar anagrama, por usar recursão a validação é feita na primeira condicional
     * Na segunda é considerada que quando a input não possui mais caracteres um anagrama é formado
     * O laço 'for' percorre cada caractere da string considerada input e, para cada caractere, gera
     * uma nova chamada recursiva com o caractere removido da string original. Esse processo
     * é repetido até que todos os anagramas possíveis sejam formados.
     *
     * @param input A string de entrada da qual os anagramas serão gerados.
     * @param anagrama O anagrama parcialmente formado que é preenchido à medida que a recursão avança.
     * @return Lista contendo todos os anagramas possíveis.
     * @throws EntradaInvalidaException Se a entrada for inválida, será lançada uma exceção
     * */
    public List<String> gerarAnagramas(String input, String anagrama) throws EntradaInvalidaException {

        if(anagrama.isEmpty()){
            validarInput(input);
        }

        if (input.isEmpty()) {
            this.listaAnagramas.add(anagrama);
        }

        for (int i = 0; i < input.length(); i++) {
            char letra = input.charAt(i);
            String restoInput = input.substring(0, i) + input.substring(i + 1);
            gerarAnagramas(restoInput, anagrama + letra);
        }

        return listaAnagramas;
    }
}
