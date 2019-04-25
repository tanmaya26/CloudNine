package com.tanmaya.projects.cloudnine.bean;

public class FileMapping {
	private int id;
	private String filepath;
	private String filename;

	public FileMapping(int id, String filepath, String filename) {
		super();
		this.id = id;
		this.filepath = filepath;
		this.filename = filename;
	}

	public FileMapping(String filepath, String filename) {
		super();
		this.filepath = filepath;
		this.filename = filename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
