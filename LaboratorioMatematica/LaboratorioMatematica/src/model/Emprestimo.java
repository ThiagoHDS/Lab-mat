package model;

import java.util.Calendar;

import exceptions.idInvalidoException;

public class Emprestimo {

	public static final double VALOR_MULTA_DIA = 0.50;
	private String dataEmprestimo;
	private String dataRetorno;
	private String idUsuario;
	private int id, idObjeto;

	public Emprestimo(String idUsuario, int idObjeto, String inicio, String retorno, int id) throws Exception {
		if (retorno == null || inicio == null || idUsuario == null)
			throw new NullPointerException();

		if (idObjeto == 0 || id == 0)
			throw new idInvalidoException();

		this.idObjeto = idObjeto;
		this.idUsuario = idUsuario;
		this.dataEmprestimo = inicio;
		this.dataRetorno = retorno;
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public double calcularMulta() {
		int diaAtual = getDiaAtual();
		int diaFim = getDiaFimEmprestimo();

		if (diaFim < diaAtual)
			return (diaAtual - diaFim) * Emprestimo.VALOR_MULTA_DIA;

		return 0.0;
	}

	public int getDiaAtual() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);

	}

	public int getDiaFimEmprestimo() {
		String fim[] = this.dataRetorno.split("-");
		return Integer.parseInt(fim[2]);

	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(String dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	@Override
	public String toString() {
		return "Usuario: " + this.idUsuario + "\nObjeto: " + this.idObjeto + "\nData de inicio: "
				+ this.getDataEmprestimo() + "\nData de encerramento: " + this.getDataRetorno() + "\nId emprestimo: "
				+ this.id;
	}

}
