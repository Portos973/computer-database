package com.excilys.formation.project.service;

import java.util.List;

import com.excilys.formation.project.beans.Computer;
import com.excilys.formation.project.controller.Controller;

public class Pages {
	
	private int limit=0;
	private int offset=1;
	
	public void previous(int limit, int Offset){
		try {
			Controller ctrl = new Controller();
			ctrl.pages(limit, Offset);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Computer>  next(int limit, int Offset){
	List<Computer> list=null;
		try {
			Controller ctrl = new Controller();
			list=ctrl.pages(limit, Offset);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void first(int limit, int Offset){
		try {
			Controller ctrl = new Controller();
			ctrl.pages(limit, Offset);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void last(int limit, int Offset){
		try {
			Controller ctrl = new Controller();
			ctrl.pages(limit, Offset);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
