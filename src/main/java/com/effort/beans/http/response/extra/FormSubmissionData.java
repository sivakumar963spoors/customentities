package com.effort.beans.http.response.extra;

import java.util.List;

import com.effort.entity.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormSubmissionData {

	@JsonProperty("forms")
	private FormDataContainer formDataContainer = new FormDataContainer();
	

	private List<Form> forms;

	
	@JsonIgnore
	public FormDataContainer getFormDataContainer() {
		return formDataContainer;
	}


	
	public void setFormDataContainer(FormDataContainer formDataContainer) {
		this.formDataContainer = formDataContainer;
	}



	@JsonIgnore
	public List<Form> getForms() {
		return forms;
	}



	public void setForms(List<Form> forms) {
		this.forms = forms;
	}




	
	
	
}
