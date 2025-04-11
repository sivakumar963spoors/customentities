package com.effort.beans.http.response.extra;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomEntityData {
	
	@JsonProperty("customEntities")
	private CustomEntityRequestDataContainer customEntities;
	
	@JsonProperty("forms")
	private FormDataContainer formDataContainer = new FormDataContainer();

	public CustomEntityRequestDataContainer getCustomEntities() {
		return customEntities;
	}

	public void setCustomEntities(CustomEntityRequestDataContainer customEntities) {
		this.customEntities = customEntities;
	}

	public FormDataContainer getFormDataContainer() {
		return formDataContainer;
	}

	public void setFormDataContainer(FormDataContainer formDataContainer) {
		this.formDataContainer = formDataContainer;
	}
	
	

}
