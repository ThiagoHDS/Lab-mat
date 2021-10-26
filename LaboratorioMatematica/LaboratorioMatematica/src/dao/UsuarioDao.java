package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

/****
 * Classe responsavel por gerenciar a comunicacao do sistema com o banco de
 * dados de usuarios.
 * 
 * Eh possivel cadastrar, procurar, remover, listar e alterar usuarios atraves
 * dessa classe.
 * 
 * @author thiago
 *
 */
public class UsuarioDao {

	private final String insertQuery = "insert into usuarios (nome,matricula) values (?,?)";
	private final String selectQuery = "select * from usuarios";
	private final String deleteQuery = "delete from usuarios where matricula=?";
	private final String editQuery = "update usuarios set nome=? where matricula=?";

	/***
	 * Insere um novo registro de usuario no banco de dados
	 * 
	 * @param usuario - Usuario a ser inserido
	 * @return Retorna true se a insercao ocorrer corretamente ou mosra uma mensagem
	 *         de erro caso alguma excecao for lancada
	 */

	public boolean cadastraUsuario(Usuario usuario) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getMatricula());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Edita o nome do usuario
	 * 
	 * @param usuario - Novo nome do usuario
	 * @return true se for alterado, senao lanca excecao
	 */
	public boolean editarUsuario(Usuario usuario) {
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("chegou");

		try {

			PreparedStatement stmt = con.prepareStatement(editQuery);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getMatricula());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/***
	 * Procura um usuario no banco de dados atraves da sua matricula
	 * 
	 * @param matricula - matricula do usuario
	 * @return Retorna o usuario se for encontrado, senao retorna null
	 */
	public Usuario procuraUsuario(String matricula) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				if (res.getString("matricula").equals(matricula))
					return new Usuario(res.getString("nome"), res.getString("matricula"));

			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}

	/***
	 * Remove um registro de usuario do banco de dados
	 * 
	 * @param matricula - matricula do usuario
	 * @return
	 */
	public boolean removerUsuario(String matricula) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(deleteQuery);
			stmt.setString(1, matricula);

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/***
	 * Gera uma lista contendo todos os usuarios registrados na tabela
	 * 
	 * @return List<Usuario> lista de objetos do tipo usuario
	 */
	public List<Usuario> geraLista() {
		List<Usuario> lista = new ArrayList<Usuario>();

		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				lista.add(new Usuario(res.getString("nome"), res.getString("matricula")));
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lista;

	}

}
