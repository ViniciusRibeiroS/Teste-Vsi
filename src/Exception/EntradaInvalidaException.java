package Exception;

/*
 * Essa exceção foi criada para sinalizar quando o input fornecido é inválido para o processo
 * de criar anagramas, não sendo permitidas entradas vazias ou que contêm caracteres não alfabéticos.
 *
 * A classe contém dois construtores:
 * - Um construtor padrão que exibe uma mensagem de erro padrão no console.
 * - Um construtor que permite fornecer uma mensagem personalizada para descrever o erro, utilizada no service.
 */
public class EntradaInvalidaException extends Exception
{
    public EntradaInvalidaException() {
        System.out.println("Entrada inválida, incapaz de gerar anagrama");
    }

    public EntradaInvalidaException(String message)
    {
        super(message);
    }
}