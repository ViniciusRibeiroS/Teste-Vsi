package Service;
import Exception.EntradaInvalidaException;

import java.util.*;

public class Anagrama {

    private List<String> listaAnagramas = new ArrayList<>();

    public void validarInput(String inputValidacao) throws EntradaInvalidaException {
        if(inputValidacao.isEmpty()){
            throw new EntradaInvalidaException("Input não contém caracteres");
        }
        else if(!inputValidacao.matches("^[a-zA-Z]+$")){
            throw new EntradaInvalidaException("Input contém caracteres além de letras");
        }
    }

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
