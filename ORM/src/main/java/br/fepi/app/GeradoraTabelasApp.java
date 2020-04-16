package br.fepi.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeradoraTabelasApp {

	private static EntityManagerFactory emf;	
	
	public static void main(String[] args) {
		
		try {
			emf = 	Persistence.createEntityManagerFactory("BebidasPU");			
			System.out.println("Tabelas Criadas com Sucesso.");
		} catch (Exception e) {
			System.out.println("Erro: "+e.getMessage());
		}
		finally {

			emf.close();//Fecha a conexão com o banco.	
		}

	}

}
