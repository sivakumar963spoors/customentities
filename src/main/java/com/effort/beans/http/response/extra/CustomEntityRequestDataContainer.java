package com.effort.beans.http.response.extra;



import java.util.ArrayList;
import java.util.List;

import com.effort.entity.CustomEntity;



public class CustomEntityRequestDataContainer 
{
	private List<CustomEntity> added;
	private List<CustomEntity> modified;
	
	public CustomEntityRequestDataContainer()
	{
		this.added = new ArrayList<CustomEntity>();
		this.modified = new ArrayList<CustomEntity>();
	}

	public List<CustomEntity> getAdded() {
		return added;
	}

	public void setAdded(List<CustomEntity> added) {
		this.added = added;
	}

	public List<CustomEntity> getModified() {
		return modified;
	}

	public void setModified(List<CustomEntity> modified) {
		this.modified = modified;
	}
	
	

}
