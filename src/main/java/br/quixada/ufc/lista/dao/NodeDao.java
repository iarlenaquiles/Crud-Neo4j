package br.quixada.ufc.lista.dao;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.exceptions.ClientException;

import br.quixada.ufc.lista.connection.ConnectionNeo4J;
import br.quixada.ufc.lista.model.Node;

public class NodeDao implements INodeDao {

	private Session session = null;

	public NodeDao() {
		ConnectionNeo4J neo4j = new ConnectionNeo4J();
		this.session = neo4j.getSession();
	}

	public void salvar(Node node) {
		Transaction transacao = session.beginTransaction();

		try {
			transacao.run("CREATE (n:Node {node: " + node.getNode() + "})");
			transacao.success();
		} finally {
			try {
				transacao.close();
			} catch (ClientException excep) {
				transacao.failure();
				transacao.close();
			}
		}

		session.close();
	}

	public List<Node> buscarTodos() {

		ArrayList<Node> allNodes = new ArrayList<Node>();

		StatementResult resultado = session.run("MATCH (n:Node) RETURN n.node AS node");

		while (resultado.hasNext()) {

			Record nodeAtual = resultado.next();

			Node node = new Node();
			node.setNode(nodeAtual.get("node").asInt());

			allNodes.add(node);

		}

		session.close();

		return allNodes;

	}

	public void criarRelacionamento(int node1, int node2, double peso) {

		Transaction transacao = session.beginTransaction();

		try {
			transacao.run("MATCH (n:Node) WHERE n.node = " + node1 + " MATCH (n2:Node) WHERE n2.node = " + node2
					+ " CREATE (n)-[:CONNECTED_TO { cost : " + peso + " }]->(n2)");
			transacao.success();

		} finally {
			try {
				transacao.close();
			} catch (ClientException excep) {
				transacao.failure();
				transacao.close();
			}
		}

		session.close();

	}

	public String caminhoMinino(int node1, int node2) {
		StatementResult resultado = session.run("MATCH (a:Node { node: " + node1 + " }),(b:Node { node: " + node2
				+ " }), r = shortestPath((a)-[*]-(b)) RETURN r");

		Record nodeAtual = null;
		while (resultado.hasNext()) {

			nodeAtual = resultado.next();

		}

		session.close();

		return nodeAtual.toString();
	}

	public String todosCaminhosMinimos(int node1, int node2) {

		// StatementResult resultado = session.run("MATCH (a:Node { node: " +
		// node1 + " }),(b:Node { node: " + node2
		// + " }), p = allShortestPaths((a)-[*]-(b)) RETURN p");

		StatementResult resultado = session
				.run("MATCH p=(a:Node { node: " + node1 + " })-[*]-(b:Node { node: " + node2 + " }) RETURN p");

		Record nodeAtual = null;
		while (resultado.hasNext()) {

			nodeAtual = resultado.next();

		}

		session.close();

		return nodeAtual.toString();
	}

}
