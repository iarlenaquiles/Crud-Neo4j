package br.quixada.ufc.lista.ui;

import java.util.Scanner;

import br.quixada.ufc.lista.dao.NodeDao;

public class CaminhoMinimo {
	public static void main(String[] args) {
		NodeDao n = new NodeDao();
		Scanner sc = new Scanner(System.in);
		System.out.println(n.caminhoMinino(sc.nextInt(), sc.nextInt()));

	}
}
