package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Login;

/****
 * Classe responsavel por gerenciar a comunicacao do sistema com o banco de
 * dados de logins.
 * 
 * Eh possivel cadastrar, procurar, remover, listar e alterar logins atraves
 * dessa classe.
 * 
 * @author thiago
 *
 */
public class LoginDao {

	private final String insertQuery = "insert into logins (username,senha,idFuncionario) values (?,?,?)";
	private final String deleteQuery = "delete from logins where idFuncionario=?";
	private final String selectQuery = "select * from logins";
	private final String editQuery = "update logins set senha=? where idFuncionario=?";

	/***
	 * Verifica se o username e senha estao devidamente cadastrado no banco de dados
	 * para validacao do acesso
	 * 
	 * @param username - Login a ser verificado
	 * @param senha    - senha a ser verificada
	 * @return true se o acesso for permitido, senao false
	 */
	public boolean autorizarLogin(String username, String senha) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				if (res.getString("username").equalsIgnoreCase(username)
						&& res.getString("senha").equalsIgnoreCase(senha)) {
					return true;
				}

			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;

	}

	/***
	 * Procura um login no banco de dados atraves da id do funcionario
	 * 
	 * @param id - codigo do funcionario
	 * @return Retorna o login se for encontrado, senao retorna null
	 */
	public Login procurarLogin(int id) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				if (res.getInt("idFuncionario") == id)
					return new Login(res.getString("username"), res.getString("senha"), res.getInt("idFuncionario"));

			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}

	/***
	 * Altera a senha do usuario. Nao eh possivel alterar o login
	 * 
	 * @param login - Login contendo as informacoes de login
	 * @return true se for alterado, senao lanca excecao
	 */
	public boolean editarSenha(Login login) {
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("chegou");

		try {

			PreparedStatement stmt = con.prepareStatement(editQuery);

			stmt.setString(1, login.getSenha());
			stmt.setInt(2, login.getIdFuncionario());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Cria um novo login no sistema
	 * 
	 * @param login - Login a ser inserido no sistema
	 */
	public void cadastrarLogin(Login login) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, login.getUsename());
			stmt.setString(2, login.getSenha());
			stmt.setInt(3, login.getIdFuncionario());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/***
	 * Remove um registro de login do banco de dados
	 * 
	 * @param id - Codigo do funcionario associado a esse login
	 * @return true se for excluido, senao lanca excecao
	 */
	public boolean removerLogin(int idFuncionario) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(deleteQuery);
			stmt.setInt(1, idFuncionario);

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/**
	 * public void alterarSenha(String novaSenha, int idFuncionario) { Connection
	 * con = new ConnectionFactory().getConnection();
	 * 
	 * try {
	 * 
	 * PreparedStatement stmt = con.prepareStatement(editQuery); stmt.setString(1,
	 * novaSenha); stmt.setInt(2, idFuncionario);
	 * 
	 * stmt.execute(); stmt.close(); con.close();
	 * 
	 * } catch (Exception e) { System.out.println(e.toString()); }
	 * 
	 * }
	 **/

	/***
	 * Gera uma lista contendo os logins
	 * 
	 * @return List de logins
	 */
	public List<Login> geraLista() {
		List<Login> lista = new ArrayList<Login>();

		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				lista.add(new Login(res.getString("username"), res.getString("senha"), res.getInt("idFuncionario")));
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lista;

	}

}
