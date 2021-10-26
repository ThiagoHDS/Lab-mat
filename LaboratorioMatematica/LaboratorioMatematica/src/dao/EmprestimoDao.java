package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Emprestimo;

/****
 * Classe responsavel por gerenciar a comunicacao do sistema com o banco de
 * dados de emprestimos.
 * 
 * Eh possivel cadastrar, procurar, remover, listar e alterar emprestimos
 * atraves dessa classe.
 * 
 * @author thiago
 *
 */

public class EmprestimoDao {

	private final String insertQuery = "insert into emprestimos (idUsuario,idObjeto,DataInicio,DataFim,id) values (?,?,?,?,?)";
	private final String selectQuery = "select * from emprestimos";
	private final String deleteQuery = "delete from emprestimos where id=?";
	private final String editQuery = "update emprestimos set DataInicio =?,DataFim=? where id=?";

	/***
	 * Cadastra um emprestimo no banco de dados
	 * 
	 * @param emprestimo - emprestimo a ser cadastrado
	 * @return true se for cadastrado, senao lanca excecao
	 */
	public boolean cadastrarEmprestimo(Emprestimo emprestimo) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, emprestimo.getIdUsuario());
			stmt.setInt(2, emprestimo.getIdObjeto());
			stmt.setString(3, emprestimo.getDataEmprestimo());
			stmt.setString(4, emprestimo.getDataRetorno());
			stmt.setInt(5, emprestimo.getId());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Procura um emprestimo no banco de dados.
	 * 
	 * @param id id do emprestimo
	 * @return Emprestimo se for encontrado, senao retorna null
	 */
	public Emprestimo procuraEmprestimo(int id) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				if (res.getInt("id") == id) {

					return new Emprestimo(res.getString("idUsuario"), res.getInt("idObjeto"),
							res.getString("DataInicio"), res.getString("DataFim"), res.getInt("id"));

				}
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}

	/***
	 * Gera uma lista contendo todos os emprestimos em aberto
	 * 
	 * @return List contendo os emprestimos
	 */
	public List<Emprestimo> gerarLista() {
		List<Emprestimo> lista = new ArrayList<Emprestimo>();

		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				lista.add(new Emprestimo(res.getString("idUsuario"), res.getInt("idObjeto"),
						res.getString("DataInicio"), res.getString("DataFim"), res.getInt("id")));
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lista;
	}

	/***
	 * Edita as datas do emprestimo
	 * 
	 * @param emprestimo - Emprestimo a ser editado
	 * @return true se for editado, senao lanca excecao
	 */
	public boolean editarEmprestimo(Emprestimo emprestimo) {
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("chegou");

		try {

			PreparedStatement stmt = con.prepareStatement(editQuery);

			stmt.setString(1, emprestimo.getDataEmprestimo());
			stmt.setString(2, emprestimo.getDataRetorno());
			stmt.setInt(3, emprestimo.getId());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Remove um emprestimo do bd
	 * 
	 * @param emprestimo - emprestimo a ser removido
	 * @return true se for removido, senao lanca excecao
	 */
	public boolean removerEmprestimo(Emprestimo emprestimo) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(deleteQuery);
			stmt.setInt(1, emprestimo.getId());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

}
