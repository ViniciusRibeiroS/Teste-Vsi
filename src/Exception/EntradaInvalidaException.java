package Exception;

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