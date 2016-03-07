package com.fms.facilityuse;

public class PersonImpl implements IPerson {
	
	private int personId;
    private String firstName;
    private String lastName;
    private String address;
    /* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#getId()
	 */
    public int getPersonId() {
		return personId;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#setId(int)
	 */
	public void setPersonId(int id) {
		this.personId = id;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#getAddress()
	 */
	public String getAddress() {
		return address;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#setAddress(java.lang.String)
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#getPhone()
	 */
	public String getPhone() {
		return phone;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#setPhone(java.lang.String)
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#getEmail()
	 */
	public String getEmail() {
		return email;
	}
	/* (non-Javadoc)
	 * @see com.fms.facilityuse.IPerson#setEmail(java.lang.String)
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	private String phone;
    private String email;

}
