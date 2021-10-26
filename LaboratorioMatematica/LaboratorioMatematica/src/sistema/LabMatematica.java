package sistema;

import java.util.List;

import controller.ControllerEmprestimo;
import controller.ControllerFuncionario;
import controller.ControllerLogin;
import controller.ControllerObjeto;
import controller.ControllerUsuario;
import exceptions.ObjetoIndisponivelException;
import model.Emprestimo;
import model.Funcionario;
import model.Login;
import model.Objeto;
import model.Usuario;

public class LabMatematica {

	private ControllerUsuario genUser;
	private ControllerObjeto genObj;
	private ControllerLogin genLogin;
	private ControllerFuncionario genFun;
	private ControllerEmprestimo genEmp;

	public LabMatematica() {
		this.genEmp = new ControllerEmprestimo();
		this.genUser = new ControllerUsuario();
		this.genObj = new ControllerObjeto();
		this.genLogin = new ControllerLogin();
		this.genFun = new ControllerFuncionario();

	}

	/***
	 * Cria um login para um funcionario previamente cadastrado. Se o id estiver
	 * incorreto ou nao cadastrado eh lancada uma excecao
	 * 
	 * @param username      - Login do funcionario
	 * @param senha         - Senha do funcionario
	 * @param idFuncionario - Codigo do funcionario
	 * @return true se o login for criado com sucesso, senao lanca excecao
	 */
	public boolean criarLogin(String username, String senha, int idFuncionario) {
		try {

			genLogin.cadastrarLogin(new Login(username, senha, idFuncionario));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Remove o login de um funcionario.
	 * 
	 * @param id - Codigo do funcionario
	 * @return true se a operacao for concluida, senao lanca excecao
	 */
	public boolean removerLogin(int id) {
		try {

			genLogin.removerLogin(genLogin.procurarLogin(id));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Garante acesso do funcionario ao sistema do laboratorio de matematica
	 * 
	 * @param username - Login do funcionario
	 * @param senha    - Senha do funcionario
	 * @return true se o login estiver cadastrado, senao false
	 * @throws Exception - Lanca excecao caso os campos senha ou username forem
	 *                   invalidos
	 */
	public boolean autenticarFuncionario(String username, String senha) throws Exception {
		if (username == null || senha == null)
			throw new NullPointerException();

		return genLogin.realizarlogin(username, senha);
	}

	public boolean editarSenha(String senha, int id) {
		try {

			genLogin.editarSenha(new Login("", senha, id));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Cadastra o funcionario que sera responsavel pelas operacoes do sistema
	 * 
	 * @param nome - Nome do funcionario
	 * @param id   - Codigo do funcionario
	 * @return true se for cadastrado com sucesso, senao lanca excecao
	 */
	public boolean cadastrarFuncionario(String nome, int id) {
		try {

			genFun.cadastrarFuncionario(new Funcionario(nome, id));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Altera o nome do funcionario (o codigo eh imutavel)
	 * 
	 * @param nome   - Novo nome do funcionario
	 * @param codigo - Codigo para localização no banco de dados
	 * @return true se for alterado, senao lanca excecao
	 * 
	 */
	public boolean editarFuncionario(String nome, int codigo) {
		try {

			genFun.editarFuncionario(new Funcionario(nome, codigo));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Remove um funcionario do bd. Quando um funcionario eh removido e o mesmo
	 * possui um login, ele tambem eh excluido.
	 * 
	 * @param id - Codigo do funcionario
	 * @return true se for removido, senao lanca excecao
	 */
	public boolean removerFuncionario(int id) {
		try {

			Funcionario fun = genFun.procurarFuncionario(id);
			Login aux = genLogin.procurarLogin(id);

			if (fun != null)
				genLogin.removerLogin(aux);

			genFun.removerFuncionario(fun);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Gera uma string contendo a lista de funcionarios cadastrados
	 * 
	 * @return String com a lista de funcionarios
	 */
	public String listarFuncionarios() {
		String str = "";
		List<Funcionario> aux = genFun.listarFuncionarios();

		for (Funcionario funcionario : aux) {
			str += funcionario.toString() + "\n";
		}

		return str;
	}

	/***
	 * Cadastra um objeto no sistema
	 * 
	 * @param nome       - Nome do objeto
	 * @param id         - id do objeto
	 * @param manual     - Diretorio de onde se contra o manual. A string sera vazia
	 *                   caso nao exista manual
	 * @param disponivel - Status do objeto. O status ao cadastrar o objeto sempre
	 *                   sera true
	 * @return true se for cadastrado, senao lanca excecao
	 */
	public boolean cadastrarObjeto(String nome, int id, String manual, boolean disponivel) {
		try {

			genObj.cadastrarObjeto(new Objeto(nome, id, manual, disponivel));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Edita o nome e o manual do objeto. O status eh modificado em um metodo
	 * exclusivo para ele.
	 * 
	 * @param nome      - Novo nome do objeto
	 * @param dirManual - Novo diretorio do manual
	 * @param id        - Id para localizacao no banco de dados
	 * @return true se for editado, senao lanca exececao
	 */
	public boolean editarObjeto(String nome, String dirManual, int id) {
		try {

			genObj.editarObjeto(new Objeto(nome, id, dirManual, true));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	// FALTA IMPLEMENTAR
	public boolean reservarObjeto(Usuario usuario, Objeto objeto) {
		try {
			genObj.reservarObjeto(usuario);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	/***
	 * Remove um objeto do sistema. Caso o objeto esteja presente em algum
	 * emprestimo, a operacao nao eh executada
	 * 
	 * @param id - Id do objeto a ser removido
	 * @return true se for excluido, senao lanca excecao
	 */
	public boolean removerObjeto(int id) {
		try {

			genObj.removerObjeto(genObj.procurarObjeto(id));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Lista todos os objetos disponiveis para aluguel
	 * 
	 * @return String contendo a lista de objetos disponiveis
	 */
	public String listarObjetosDisponiveis() {
		String str = "";
		List<Objeto> aux = genObj.listarObjetos();

		for (Objeto objeto : aux) {
			if (objeto.isDisponivel())
				str += objeto.toString() + "\n";

		}

		return str;
	}

	/***
	 * Lista todos os objetos cadastrados no sistema
	 * 
	 * @return String contendo todos os objetos cadastrados
	 */
	public String listarObjetos() {
		String str = "";
		List<Objeto> aux = genObj.listarObjetos();

		for (Objeto objeto : aux) {
			str += objeto.toString() + "\n";

		}

		return str;
	}

	/***
	 * Cadastra um usuario no sistema
	 * 
	 * @param nome      - Nome do usuario
	 * @param matricula - matricula do usuario
	 * @return true se for cadastrado, senao lanca excecao
	 */
	public boolean cadastrarUsuario(String nome, String matricula) {
		try {

			genUser.cadastrarUsuario(new Usuario(nome, matricula));

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return true;
	}

	/***
	 * Edita o nome de um usuario
	 * 
	 * @param nome      - Novo nome do usuario
	 * @param matricula - Matricula para localizar o usuario no banco de dados
	 * @return true se for editado, senao lanca excecao
	 */
	public boolean editarUsuario(String nome, String matricula) {
		try {

			genUser.editarUsuario(new Usuario(nome, matricula));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Remove um usuario do sistema. Caso o usuario possuir emprestimo em aberto, a
	 * exclusao nao eh executada.
	 * 
	 * @param matricula - matricula para busca no banco de dados
	 * @return true se for removido, senao lanca excecao
	 */
	public boolean removerUsuario(String matricula) {
		try {

			genUser.removerUsuario(genUser.procurarUsuario(matricula));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Lista os usuarios cadastrados no sistema
	 * 
	 * @return String contendo os usuarios cadastrados
	 */
	public String listarUsuarios() {
		String str = "";
		List<Usuario> aux = genUser.listarUsuarios();

		for (Usuario usuario : aux) {
			str += usuario.toString() + "\n";
		}

		return str;
	}

	/***
	 * Realiza um emprestimo no sistema. É necessário que o usuario e o objeto
	 * estejam cadastrados no sistema. O objeto tambem precisa estar disponivel
	 * 
	 * @param idUsuario - Matricula do usuario
	 * @param idObjeto  - id do objeto
	 * @param inicio    - Data que foi realizado o emprestimo
	 * @param retorno   - Data para entrega do objeto
	 * @param id        - Id do emprestimo
	 * @return true se for cadastrado, senao lanca excecao
	 * 
	 */
	public boolean realizarEmprestimo(String idUsuario, int idObjeto, String inicio, String retorno, int id) {
		try {
			Objeto obj = genObj.procurarObjeto(idObjeto);

			if (!obj.isDisponivel())
				throw new ObjetoIndisponivelException();

			genEmp.realizaEmprestimo(new Emprestimo(idUsuario, idObjeto, inicio, retorno, id), genObj);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Edita as datas de um emprestimo
	 * 
	 * @param dataInicial - Nova data inicial
	 * @param dataFinal   - Nova data final
	 * @param id          - Id do emprestimo para consulta no banco de dados
	 * @return true se for editado, senao lanca excecao
	 */
	public boolean editarEmprestimo(String dataInicial, String dataFinal, int id) {
		try {

			genEmp.editarEmprestimo(new Emprestimo("", -1, dataInicial, dataFinal, id));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}

	/***
	 * Finaliza um emprestimo.
	 * 
	 * @param id - Id do emprestimo
	 * @return double contendo o valor da multa
	 */
	public double finalizarEmprestimo(int id) {
		try {

			return genEmp.finalizarEmprestimo(genEmp.procurarEmprestimo(id), genObj);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return 0.0;
	}

	/**
	 * Lista os emprestimos cadastrados no sistema
	 * 
	 * @return String contendo os emprestimos
	 */
	public String listarEmprestimos() {
		String str = "";
		List<Emprestimo> aux = genEmp.listarEmprestimos();

		for (Emprestimo emp : aux) {
			str += emp.toString() + "\n";

		}

		return str;

	}

}
