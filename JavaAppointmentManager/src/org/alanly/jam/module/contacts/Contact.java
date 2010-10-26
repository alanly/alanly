/**
 * 
 */
package org.alanly.jam.module.contacts;

/**
 * @author Alan Ly
 *
 */
public class Contact implements Comparable<Contact> {
	
	// Contact properties
	private int contact_id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String company_name;
	private String address_1;
	private String address_2;
	private String city;
	private String province;
	private String country;
	private String postal_code;
	private String phone_number;
	private String mobile_number;
	private String fax_number;
	private String email_address;

	/**
     * @return the contact_id
     */
    public int getContact_id() {
    	return contact_id;
    }

	/**
     * @return the first_name
     */
    public String getFirst_name() {
    	return first_name;
    }

	/**
     * @return the middle_name
     */
    public String getMiddle_name() {
    	return middle_name;
    }

	/**
     * @return the last_name
     */
    public String getLast_name() {
    	return last_name;
    }

	/**
     * @return the company_name
     */
    public String getCompany_name() {
    	return company_name;
    }

	/**
     * @return the address_1
     */
    public String getAddress_1() {
    	return address_1;
    }

	/**
     * @return the address_2
     */
    public String getAddress_2() {
    	return address_2;
    }

	/**
     * @return the city
     */
    public String getCity() {
    	return city;
    }

	/**
     * @return the province
     */
    public String getProvince() {
    	return province;
    }

	/**
     * @return the country
     */
    public String getCountry() {
    	return country;
    }

	/**
     * @return the postal_code
     */
    public String getPostal_code() {
    	return postal_code;
    }

	/**
     * @return the phone_number
     */
    public String getPhone_number() {
    	return phone_number;
    }

	/**
     * @return the mobile_number
     */
    public String getMobile_number() {
    	return mobile_number;
    }

	/**
     * @return the fax_number
     */
    public String getFax_number() {
    	return fax_number;
    }

	/**
     * @return the email_address
     */
    public String getEmail_address() {
    	return email_address;
    }

	/**
     * @param contact_id the contact_id to set
     */
    public void setContact_id(int contact_id) {
    	this.contact_id = contact_id;
    }

	/**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
    	this.first_name = first_name;
    }

	/**
     * @param middle_name the middle_name to set
     */
    public void setMiddle_name(String middle_name) {
    	this.middle_name = middle_name;
    }

	/**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
    	this.last_name = last_name;
    }

	/**
     * @param company_name the company_name to set
     */
    public void setCompany_name(String company_name) {
    	this.company_name = company_name;
    }

	/**
     * @param address_1 the address_1 to set
     */
    public void setAddress_1(String address_1) {
    	this.address_1 = address_1;
    }

	/**
     * @param address_2 the address_2 to set
     */
    public void setAddress_2(String address_2) {
    	this.address_2 = address_2;
    }
    
    /**
     * 
     * @param address_1
     * @param address_2
     */
    public void setAddress(String address_1, String address_2) {
    	this.address_1 = address_1;
    	this.address_2 = address_2;
    }

	/**
     * @param city the city to set
     */
    public void setCity(String city) {
    	this.city = city;
    }

	/**
     * @param province the province to set
     */
    public void setProvince(String province) {
    	this.province = province;
    }

	/**
     * @param country the country to set
     */
    public void setCountry(String country) {
    	this.country = country;
    }

	/**
     * @param postal_code the postal_code to set
     */
    public void setPostal_code(String postal_code) {
    	this.postal_code = postal_code;
    }

	/**
     * @param phone_number the phone_number to set
     */
    public void setPhone_number(String phone_number) {
    	this.phone_number = phone_number;
    }

	/**
     * @param mobile_number the mobile_number to set
     */
    public void setMobile_number(String mobile_number) {
    	this.mobile_number = mobile_number;
    }

	/**
     * @param fax_number the fax_number to set
     */
    public void setFax_number(String fax_number) {
    	this.fax_number = fax_number;
    }

	/**
     * @param email_address the email_address to set
     */
    public void setEmail_address(String email_address) {
    	this.email_address = email_address;
    }

	@Override
    public int compareTo(Contact other) {
	    
		
	    return 0;
    }

	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    
	    result = prime * result
	            + ((address_1 == null) ? 0 : address_1.hashCode());
	    result = prime * result
	            + ((address_2 == null) ? 0 : address_2.hashCode());
	    result = prime * result + ((city == null) ? 0 : city.hashCode());
	    result = prime * result
	            + ((company_name == null) ? 0 : company_name.hashCode());
	    result = prime * result + contact_id;
	    result = prime * result + ((country == null) ? 0 : country.hashCode());
	    result = prime * result
	            + ((email_address == null) ? 0 : email_address.hashCode());
	    result = prime * result
	            + ((fax_number == null) ? 0 : fax_number.hashCode());
	    result = prime * result
	            + ((first_name == null) ? 0 : first_name.hashCode());
	    result = prime * result
	            + ((last_name == null) ? 0 : last_name.hashCode());
	    result = prime * result
	            + ((middle_name == null) ? 0 : middle_name.hashCode());
	    result = prime * result
	            + ((mobile_number == null) ? 0 : mobile_number.hashCode());
	    result = prime * result
	            + ((phone_number == null) ? 0 : phone_number.hashCode());
	    result = prime * result
	            + ((postal_code == null) ? 0 : postal_code.hashCode());
	    result = prime * result
	            + ((province == null) ? 0 : province.hashCode());
	    
	    return result;
    }

	/* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    
	    if (obj == null)
		    return false;
	    
	    if (!(obj instanceof Contact))
		    return false;
	    
	    Contact other = (Contact) obj;
	    
	    if (address_1 == null) {
		    if (other.address_1 != null)
			    return false;
	    } else if (!address_1.equals(other.address_1))
		    return false;
	    if (address_2 == null) {
		    if (other.address_2 != null)
			    return false;
	    } else if (!address_2.equals(other.address_2))
		    return false;
	    if (city == null) {
		    if (other.city != null)
			    return false;
	    } else if (!city.equals(other.city))
		    return false;
	    if (company_name == null) {
		    if (other.company_name != null)
			    return false;
	    } else if (!company_name.equals(other.company_name))
		    return false;
	    if (contact_id != other.contact_id)
		    return false;
	    if (country == null) {
		    if (other.country != null)
			    return false;
	    } else if (!country.equals(other.country))
		    return false;
	    if (email_address == null) {
		    if (other.email_address != null)
			    return false;
	    } else if (!email_address.equals(other.email_address))
		    return false;
	    if (fax_number == null) {
		    if (other.fax_number != null)
			    return false;
	    } else if (!fax_number.equals(other.fax_number))
		    return false;
	    if (first_name == null) {
		    if (other.first_name != null)
			    return false;
	    } else if (!first_name.equals(other.first_name))
		    return false;
	    if (last_name == null) {
		    if (other.last_name != null)
			    return false;
	    } else if (!last_name.equals(other.last_name))
		    return false;
	    if (middle_name == null) {
		    if (other.middle_name != null)
			    return false;
	    } else if (!middle_name.equals(other.middle_name))
		    return false;
	    if (mobile_number == null) {
		    if (other.mobile_number != null)
			    return false;
	    } else if (!mobile_number.equals(other.mobile_number))
		    return false;
	    if (phone_number == null) {
		    if (other.phone_number != null)
			    return false;
	    } else if (!phone_number.equals(other.phone_number))
		    return false;
	    if (postal_code == null) {
		    if (other.postal_code != null)
			    return false;
	    } else if (!postal_code.equals(other.postal_code))
		    return false;
	    if (province == null) {
		    if (other.province != null)
			    return false;
	    } else if (!province.equals(other.province))
		    return false;
	    
	    return true;
    }
	
}
