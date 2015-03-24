package com.excilys.formation.project.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport{
	private int limit;
	private int index;
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getSize() {
		return index;
	}

	public void setSize(int size) {
		this.index = size;
	}

	public void doTag() {
		JspWriter out = getJspContext().getOut();
		
	}
	
}
