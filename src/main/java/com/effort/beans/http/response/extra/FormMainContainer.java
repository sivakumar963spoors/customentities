package com.effort.beans.http.response.extra;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormMainContainer {


	@JsonProperty("data")
	private FormDataContainer formDataContainer = new FormDataContainer();

	
	
	@JsonIgnore
	public FormDataContainer getFormDataContainer() {
		return formDataContainer;
	}
	@JsonIgnore
	public void setFormDataContainer(FormDataContainer formDataContainer) {
		this.formDataContainer = formDataContainer;
	}
	
}
