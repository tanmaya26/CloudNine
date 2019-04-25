package com.tanmaya.projects.cloudnine.bean;

import java.util.Date;

public class FileList {
	private int metaId;
	private int mappingId;
	private Date dateModified;
	private String size;
	private String extension;
	private String owner;
	private String filepath;
	private String filename;
	
	public FileList(int metaId, int mappingId, Date dateModified, String size, String extension, String owner,
			String filepath, String filename) {
		super();
		this.metaId = metaId;
		this.mappingId = mappingId;
		this.dateModified = dateModified;
		this.size = size;
		this.extension = extension;
		this.owner = owner;
		this.filepath = filepath;
		this.filename = filename;
	}

	public int getMetaId() {
		return metaId;
	}

	public void setMetaId(int metaId) {
		this.metaId = metaId;
	}

	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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
