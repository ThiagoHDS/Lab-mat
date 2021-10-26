package exceptions;

public class ItemNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ItemNaoEncontradoException() {
		super("ITEM NAO ENCONTRADO");
	}

}
