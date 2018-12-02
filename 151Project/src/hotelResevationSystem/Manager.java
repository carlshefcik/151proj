package hotelResevationSystem;

/**
 * Manager class extends user and is a simple way to enforce permissions with polymorphism
 * @author Carl Shefcik
 *
 */
public class Manager extends User{

	public Manager(String userID, String password, String username) {
		super(userID, password, username);
	}
}
