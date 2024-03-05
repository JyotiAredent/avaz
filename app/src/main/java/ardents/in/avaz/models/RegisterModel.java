package ardents.in.avaz.models;

public class RegisterModel{
	private String message;
	private User user;

	public RegisterModel(String message, User user) {
		this.message = message;
		this.user = user;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}
