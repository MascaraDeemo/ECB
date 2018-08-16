/**
 * This class contains the data for each phone book person and 
 * setter and getter method for them
 */
package Assignment;


import java.util.Date;

public class PhoneBookPerson {
	private String name;
	private Date birthday;
	private long phone;
	private String address;
	private String email;
	


	/**
	 * Constructor with all information
	 * @param name
	 * @param birthday
	 * @param phone
	 * @param email
	 * @param address
	 */
//	public PhoneBookPerson(String name, Date birthday, int phone, String email, String address)
//	{
//		this.name = name;
//		this.birthday = birthday;
//		this.phone = phone;
//		this.address = address;
//		this.email= email;
//	}
	

	public PhoneBookPerson(String name) {
		this.name = name;
		this.birthday = null;
		this.address = "";
		this.phone = -1;
		this.email = "";
	}

	public PhoneBookPerson() {
		this.name = "";
		this.birthday = null;
		this.address = "";
		this.phone = -1;
		this.email = "";
	}

	/**
	 * getters to get name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * getter to get birthday
	 * @return
	 */
	public Date getBirthday()
	{
		return birthday;
	}
	/**
	 * getter to get phoneNumber
	 * @return
	 */
	public long getPhone() {
		return phone;
	}
	/**
	 * getter to get address
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * getter to get email address
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter to set name
	 * @param nm
	 */
	public void SetName(String nm)
	{
		name = nm;
	}
	/**
	 * setter to set birthday
	 * @param birth
	 */
	public void SetBirthday(Date birth)
	{
		birthday = birth;
	}
	/**
	 * setter to set phoneNumber
	 * @param ph
	 */
	public void SetPhone(long ph)
	{
		phone = ph;
	}
	/**
	 * setter to set address
	 * @param addr
	 */
	public void SetAddress(String addr)
	{
		address = addr;
	}
	/**
	 * setter to set email
	 * @param ema
	 */
	public void SetEmail(String ema) {
		email = ema;
	}
	

}
