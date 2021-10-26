package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Objeto;

/****
 * Classe responsavel por gerenciar a comunicacao do sistema com o banco de
 * dados de objetos.
 * 
 * Eh possivel cadastrar, procurar, remover, listar e alterar objetos atraves
 * dessa classe.
 * 
 * @author thiago
 *
 */
public class ObjetoDao {

	private final String insertQuery = "insert into objetos (nome,id,dirManual,disponivel) values (?,?,?,?)";
	private final String selectQuery = "select * from objetos";
	private final String deleteQuery = "delete from objetos where id=?";
	private final String editQuery = "update objetos set nome=?,dirManual=? where id=?";
	private final String editQueryStatus = "update objetos set disponivel=? where id=?";

	/***
	 * Insere um novo registro de objeto no banco de dados
	 * 
	 * @param usuario - objeto a ser inserido
	 * @return Retorna true se a insercao ocorrer corretamente ou mosra uma mensagem
	 *         de erro caso alguma excecao for lancada
	 */
	public boolean cadastrarObjeto(Objeto objeto) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, objeto.getNome());
			stmt.setInt(2, objeto.getId());
			stmt.setString(3, objeto.getDirManual());
			stmt.setBoolean(4, objeto.isDisponivel());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Altera o status do objeto para true ou false. Usado quando um emprestimo e
	 * realizado ou finalizado
	 * 
	 * @param idObjeto - Id do objeto para busca no banco de dados
	 * @param status   - Novo status do objeto
	 * @return
	 */
	public boolean mudaStatus(int idObjeto, boolean status) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(editQueryStatus);

			stmt.setBoolean(1, status);
			stmt.setInt(2, idObjeto);

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}

	/***
	 * Edita o nome do objeto
	 * 
	 * @param objeto - Novo nome do objeto
	 * @return true se for editado, senao lanca excecao
	 */
	public boolean editarObjeto(Objeto objeto) {
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("chegou");

		try {

			PreparedStatement stmt = con.prepareStatement(editQuery);

			stmt.setString(1, objeto.getNome());
			stmt.setString(2, objeto.getDirManual());
			stmt.setInt(3, objeto.getId());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/***
	 * Procura um objeto no banco de dados atraves da sua id
	 * 
	 * @param id - id do objeto
	 * @return Retorna o objeto se for encontrado, senao retorna null
	 */
	public Objeto procuraObjeto(int id) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				if (res.getInt("id") == id)
					return new Objeto(res.getString("nome"), res.getInt("id"), res.getString("dirManual"),
							res.getBoolean("disponivel"));

			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}

	/***
	 * Remove um registro de objeto do banco de dados
	 * 
	 * @param objeto - objeto a ser removido
	 * @return true se for removido, senao lanca excecao
	 */
	public boolean removerObjeto(Objeto objeto) {
		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(deleteQuery);
			stmt.setInt(1, objeto.getId());

			stmt.execute();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;

	}

	/***
	 * Retorna uma Lista de objetos
	 * 
	 * @return Lista contendo todos os objetos cadastrados
	 */
	public List<Objeto> geraLista() {
		List<Objeto> lista = new ArrayList<Objeto>();

		Connection con = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = con.prepareStatement(selectQuery);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				lista.add(new Objeto(res.getString("nome"), res.getInt("id"), res.getString("dirManual"),
						res.getBoolean("disponivel")));
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lista;

	}

}
