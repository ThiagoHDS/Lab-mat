package main;

import sistema.LabMatematica;

public class Cadastrar {

	public static void main(String[] args) {
		try {
			LabMatematica lab = new LabMatematica();

			lab.cadastrarUsuario("user1", "matricula1");
			lab.cadastrarUsuario("USUARIO", "mat");
			lab.cadastrarUsuario("sem emprestimo", "100");

			lab.cadastrarObjeto("obj1", 3, "c/adsad", true);
			lab.cadastrarObjeto("asdsa", 85, "", true);
			lab.cadastrarObjeto("objeto disp", 100, "", true);

			lab.cadastrarFuncionario("asdasf", 63);
			lab.cadastrarFuncionario("func", 56);

			lab.criarLogin("login", "senha", 63);
			lab.criarLogin("loginadasd", "asd662", 56);

			lab.realizarEmprestimo("matricula1", 3, "2019-12-01", "2019-12-02", 2);
			lab.realizarEmprestimo("mat", 85, "2019-12-01", "2019-12-02", 96);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
