package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Conversion;

public class ConversionLogView extends AbstractView {

	private String choice;
	
	@Override
	public void showResults(Request request) {
		
		if (request != null) {
			System.out.println("\n-------------------Conversion Log History----------------\n");
			System.out.println("idUser\tidConversion\tsourceTypeIn\toutputType");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<Conversion> conversion = (List<Conversion>) request.get("Log");
			for (Conversion c: conversion) {
				//System.out.println(c); stampa tutte le informazioni
				System.out.println(c.getIdUser()+"\t\t" + c.getIdConversion() +"\t\t" + c.getSourceType() +"\t\t" + c.getOutputType());
			}
			System.out.println();
		}
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("[e] exit");
		choice = getInput();
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		switch(choice) {
		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		default:
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;
		}
			
		
	}
	
	

}
