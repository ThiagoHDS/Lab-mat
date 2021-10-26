package main;

import sistema.LabMatematica;

public class Listar {

	public static void main(String[] args) {

		LabMatematica lab = new LabMatematica();

		System.out.println("---USUARIOS---");
		System.out.println(lab.listarUsuarios());

		System.out.println("---FUNCIONARIOS---");
		System.out.println(lab.listarFuncionarios());

		System.out.println("---OBJETOS DISPONIVEIS---");
		System.out.println(lab.listarObjetosDisponiveis());

		System.out.println("---TODOS OS OBJETOS---");
		System.out.println(lab.listarObjetos());

		System.out.println("---EMPRESTIMOS---");
		System.out.println(lab.listarEmprestimos());

	}

}
