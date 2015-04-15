package com.excilys.formation.project.service;


import java.util.List;

import com.excilys.formation.project.models.Computer;
import com.excilys.formation.project.persistence.impl.ComputerDAO;


public class Pages {

	private int limit=0;
	private int index=0;
	private int offset=0;
	private String search=null;
	private ComputerDAO computerDAO;
	private String sort;
	private String order;
	
	public Pages() {
		// TODO Auto-generated constructor stub
	}

	public Pages(int limit, int index, int offset, String search, String sort,
			String order) {
		super();
		this.limit = limit;
		this.index = index;
		this.offset = offset;
		this.search = search;
		this.sort = sort;
		this.order = order;
	}



	public List<Computer> next() {
		List<Computer> computers = null;
		computers = computerDAO.pages(limit, offset, search, order, sort);

		return computers;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	

}
