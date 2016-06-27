package logical;

public class User extends Login {

	private String name;
	private String cod;

	public User() {

	}
	
	public User(String cod, String name){
		this.name = name;
		this.cod = cod;
	}

	/**
	 * Metodo construtor de Usuario
	 * 
	 * @param nome
	 *            String contendo o nome
	 * @param usuario
	 *            String contendo o login
	 * @param senha
	 *            char[] contendo a Senha
	 * @param tipo
	 *            String contendo o tipo
	 */
	public User(String name, String user, char passwd[], char type) {

		super(user, passwd);
		this.name = name;
		this.userType = type;
		cod = null;
	}

	/**
	 * Metodo construtor de Usuario
	 * 
	 * @param nome
	 *            String contendo o nome
	 * @param usuario
	 *            String contendo o login
	 * @param senha
	 *            String contendo a Senha
	 * @param tipo
	 *            String contendo o tipo
	 */
	public User(String name, String user, String passwd, String type, String cod) {
		super(user, passwd, type);
		this.name = name;
		this.cod = cod;
	}

	/**
	 * Metodo de acesso ao nome
	 * 
	 * @return String que contem o nome
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo modificador de nome
	 * 
	 * @param nome
	 *            String que contem o novo nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metodo de acesso do codigo
	 * 
	 * @return String que contem o codigo
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * Metodo modificador de codigo
	 * 
	 * @param cod
	 *            String que contem o novo codigo
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}

}
