package main;

import sistema.LabMatematica;

public class Editar {

	public static void main(String[] args) {

		LabMatematica lab = new LabMatematica();

		lab.editarUsuario("Thiago Henrique", "matricula1");

		lab.editarObjeto("Regua", "C:/Lab/Reguas", 3);

		lab.editarFuncionario("Jose geraldo", 56);

		lab.editarSenha("332415", 56);

		lab.editarEmprestimo("2019-12-01", "2019-12-04", 96);

	}

}
