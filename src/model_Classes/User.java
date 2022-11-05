package model_Classes;

public class User implements Serializable {

	private String email;
	private String passwordHashed;
	private int role;

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(int email) {
		// TODO - implement User.setEmail
		throw new UnsupportedOperationException();
	}

	public String getPasswordHashed() {
		return this.passwordHashed;
	}

	/**
	 * 
	 * @param passwordHashed
	 */
	public void setPasswordHashed(int passwordHashed) {
		// TODO - implement User.setPasswordHashed
		throw new UnsupportedOperationException();
	}

	public int getRole() {
		return this.role;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(int role) {
		this.role = role;
	}

	public boolean validatePassword() {
		// TODO - implement User.validatePassword
		throw new UnsupportedOperationException();
	}

	public void updatePassword() {
		// TODO - implement User.updatePassword
		throw new UnsupportedOperationException();
	}

	public String passwordSHA256() {
		// TODO - implement User.passwordSHA256
		throw new UnsupportedOperationException();
	}

	public String toString() {
		// TODO - implement User.toString
		throw new UnsupportedOperationException();
	}

}