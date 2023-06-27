package helper;


	public class UserFoundException extends Exception{


	    public UserFoundException(){
	        super("User with this Username is already there in DB!! , Try another username");
	    }


	    public UserFoundException(String msg)
	    {
	        super(msg);
	    }
	}

