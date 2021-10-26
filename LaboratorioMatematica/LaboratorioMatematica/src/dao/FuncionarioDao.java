package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;

/****
 * Classe responsavel por gerenciar a comunicacao do sistema com o banco de
 * dados de funcionarios.
 * 
 * Eh possivel cadastrar, procurar, remover, listar e alterar funcionarios
 * atraves dessa classe.
 * 
 * @author thiago
 *
 */

public class FuncionarioDao {

	private final String insertQuery = "insert into funcionarios (codigo,nome) values (?,?)";
	private final String selectQuery = "select * from funcionarios";
	private final String deleteQuery = "delete from funcionarios where codigo=?";
	private final String editQuery = "update funcionarios set nome=? where codigo=?";

	/***
	 * Insere um novo registro de funcionario no banco de dados
	 * 
	 * @param funcionario - funcionario a ser inserido
	 * @return Retorna true se a insercao ocorrer corretamente ou mosra uma mensagem
	 *         de erro caso alguma excecao for lancada
	 */
	public boolean cadastrarFuncionario(Funcionario funcionario) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(insertQuery);

			stmt.setInt(1, funcionario.getCodigo());
			stmt.setString(2, funcionario.getNome());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Edita o nome de um funcionario
	 * 
	 * @param funcionario - funcionario contendo as informacoes
	 * @return true se for editado, senao lanca excecao
	 */
	public boolean editarFuncionario(Funcionario funcionario) {
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("chegou");

		try {

			PreparedStatement stmt = con.prepareStatement(editQuery);

			stmt.setString(1, funcionario.getNome());
			stmt.setInt(2, funcionario.getCodigo());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/***
	 * Procura um funcionario no banco de dados atraves do id
	 * 
	 * @param codigo - codigo do funcionario
	 * @return Funcionario se for encontrado, senao null
	 */
	public Funcionario procurarFuncionario(int codigo) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				if (res.getInt("codigo") == codigo)
					return new Funcionario(res.getString("nome"), res.getInt("codigo"));

			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}

	/***
	 * Remove um funcionario do banco de dados
	 * 
	 * @param codigo - Codigo do funcionario
	 * @return true se for removido, senao lanca excecao
	 */
	public boolean removerFuncionario(int codigo) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(deleteQuery);
			stmt.setInt(1, codigo);

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/***
	 * Gera uma lista contendo os funcionarios cadastrados
	 * 
	 * @return
	 */
	public List<Funcionario> geraLista() {
		List<Funcionario> lista = new ArrayList<Funcionario>();

		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				lista.add(new Funcionario(res.getString("nome"), res.getInt("codigo")));
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lista;

	}

}
