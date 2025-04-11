package com.effort.entity;



import java.io.Serializable;



public class Columns implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//title: "Rank", width: 100, dataType: "integer", dataIndx: "rank"
		private String title;
		private Integer width;
		private String dataType;
		private String dataIndx;
		private boolean editable=false;

		private boolean formField;
		private int rowspan;
		private boolean sortable=true;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		public String getDataIndx() {
			return dataIndx;
		}
		public void setDataIndx(String dataIndx) {
			this.dataIndx = dataIndx;
		}
		public boolean isEditable() {
			return editable;
		}
		public void setEditable(boolean editable) {
			this.editable = editable;
		}
	
		public boolean isFormField() {
			return formField;
		}
		public void setFormField(boolean formField) {
			this.formField = formField;
		}
		public int getRowspan() {
			return rowspan;
		}
		public void setRowspan(int rowspan) {
			this.rowspan = rowspan;
		}
		public boolean isSortable() {
			return sortable;
		}
		public void setSortable(boolean sortable) {
			this.sortable = sortable;
		}
		
		
		
		


}
