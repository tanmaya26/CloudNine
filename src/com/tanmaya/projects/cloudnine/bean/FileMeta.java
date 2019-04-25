package com.tanmaya.projects.cloudnine.bean;

import java.util.Date; 

public class FileMeta {
	private int id;
	private int mappingId;
	private String hash;
	private Date dateModified;
	private String size;
	private String extension;
	private String owner;
	
	public FileMeta(int id, int mappingId, String hash, Date dateModified, String size, String extension,
			String owner) {
		super();
		this.id = id;
		this.mappingId = mappingId;
		this.hash = hash;
		this.dateModified = dateModified;
		this.size = size;
		this.extension = extension;
		this.owner = owner;
	}
	
	public FileMeta(int mappingId, String hash, Date dateModified, String size, String extension, String owner) {
		super();
		this.mappingId = mappingId;
		this.hash = hash;
		this.dateModified = dateModified;
		this.size = size;
		this.extension = extension;
		this.owner = owner;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMappingId() {
		return mappingId;
	}
	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
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
	@Override
	public String toString() {
		return "FileMeta [id=" + id + ", mappingId=" + mappingId + ", hash=" + hash + ", dateModified=" + dateModified
				+ ", size=" + size + ", extension=" + extension + ", owner=" + owner + "]";
	}
}
