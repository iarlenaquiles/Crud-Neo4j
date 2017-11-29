package br.quixada.ufc.lista.connection;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class ConnectionNeo4J {

	Driver driver = null;
	Session session = null;

	public ConnectionNeo4J() {
		criarDriver();
		criarSession();
	}
	
	public void criarDriver() {

		try {
			this.driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "super123"));
		} catch (Exception e) {
			System.out.println("Erro ao criar ao estabelecer conexao.");
		}

	}

	public void criarSession() {

		try {
			this.session = driver.session();
		} catch (Exception e) {
			System.out.println("Erro ao criar Session.");
			e.printStackTrace();
		}

	}

	public Session getSession() {
		return session;
	}

}
