package ardents.in.avaz.models;

public class LoginModel{
	private String token;

	public LoginModel(String token) {
		this.token = token;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}
