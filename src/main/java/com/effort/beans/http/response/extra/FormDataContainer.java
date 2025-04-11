package com.effort.beans.http.response.extra;





import java.util.ArrayList;
import java.util.List;

import com.effort.entity.Form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FormDataContainer {
	private List<Form> added;
	private List<Form> modified;
	private List<Long> deleted;
	
	
	

	public FormDataContainer() {
		this.added = new ArrayList<Form>();
		this.modified = new ArrayList<Form>();
		this.deleted = new ArrayList<Long>();
	}

	
	public List<Form> getAdded() {
		return added;
	}

	public void setAdded(List<Form> added) {
		this.added = added;
	}

	public List<Form> getModified() {
		return modified;
	}

	public void setModified(List<Form> modified) {
		this.modified = modified;
	}

	public List<Long> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<Long> deleted) {
		this.deleted = deleted;
	}

	
	
}
