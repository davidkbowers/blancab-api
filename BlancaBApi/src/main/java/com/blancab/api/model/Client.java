package com.blancab.api.model;

public class Client {

	  private long id;
	  private String name;
	  private String address;
	  private String phone;
	  private String email;

	  public Client() {

	  }
	  
	  public Client(long id, String name, String address, String phone, String email) {
	    this.id = id;
	    this.name = name;
	    this.address = address;
	    this.phone = phone;
	    this.email = email; 	
	  }

	  public Client(String name, String address, String phone, String email) {
		    this.name = name;
		    this.address = address;
		    this.phone = phone;
		    this.email = email; 	
		  }

	  @Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", toString()=" + super.toString()
				+ "]";
	}

	public void setId(long id) {
	    this.id = id;
	  }
	  
	  public long getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getAddress() {
	    return address;
	  }

	  public void setAddress(String address) {
	    this.address = address;
	  }

	  public String getPhone() {
		    return phone;
		  }

	  public void setPhone(String phone) {
	    this.phone = phone;
	  }

	  public String getEmail() {
		    return email;
		  }

	  public void setEmail(String email) {
	    this.email = email;
	  }


	}