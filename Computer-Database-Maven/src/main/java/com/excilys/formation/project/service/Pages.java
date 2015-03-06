package com.excilys.formation.project.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.persistence.ComputerDAO;
import com.excilys.formation.project.persistence.IComputer;
import com.excilys.formation.project.persistence.ConnectionDAO;

public class Pages {

	private int limit;
	private int index;
	private int offset;
	private String search;

	public Pages(int limit, int offset, String search) {
		this.limit = limit;
		this.offset = offset;
		this.search = search;
	}

	public List<Computer> next() {
		List<Computer> computers = null;
		computers = ComputerDAO.INSTANCE.pages(limit, offset, search);

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

}
