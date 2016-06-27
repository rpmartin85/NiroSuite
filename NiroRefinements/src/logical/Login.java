package logical;


public class Login {

	protected String userName;
	protected String passwd;
	protected char userType;

	public Login(String userName, String passwd, String type) {
		this.userName = userName;
		this.passwd = passwd;
		this.userType = type.charAt(0);

	}

	public Login(String userName, String passwd) {
		this.userName = userName;
		this.passwd = passwd;
		

	}

	public Login() {

	}

	public Login(String user, char passwd[]) {
		this.userName = user;
		userType = 'N';
		this.passwd = "";

		for (int i = 0; i < passwd.length; i++)
			this.passwd = this.passwd + passwd[i];
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	
	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	

}
