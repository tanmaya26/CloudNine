package com.tanmaya.projects.cloudnine.dao;

public class Config {

	public String jdbcURL;
	public String user;
	public String password;

	public Config() {
		super();
		this.jdbcURL = "jdbc:mysql://localhost:3306/photobucket?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		this.user = "root";
		this.password = "expertiza";
	}

	public String getJdbcURL() {
		return jdbcURL;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
