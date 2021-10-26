package main;

import sistema.LabMatematica;

public class Remover {

	public static void main(String[] args) {

		LabMatematica lab = new LabMatematica();

		System.out.println(lab.removerUsuario("100"));
		
		System.out.println(lab.removerObjeto(85));

		System.out.println(lab.removerFuncionario(56));

		System.out.println(lab.finalizarEmprestimo(96));

		System.out.println(lab.removerLogin(63));

	}

}
