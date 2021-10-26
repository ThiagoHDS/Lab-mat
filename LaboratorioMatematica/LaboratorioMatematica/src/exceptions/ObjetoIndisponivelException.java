package exceptions;

public class ObjetoIndisponivelException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjetoIndisponivelException() {
		super("O objeto nao esta disponivel para aluguel");
	}

}
