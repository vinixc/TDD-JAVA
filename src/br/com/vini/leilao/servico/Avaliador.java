package br.com.vini.leilao.servico;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.vini.leilao.dominio.Lance;
import br.com.vini.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos ) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos ) menorDeTodos = lance.getValor();
		}
		
		pegaOsTresMaiores(leilao);
	}

	private void pegaOsTresMaiores(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			
			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
	public double getValorMedio(Leilao leilao) {
		double valorTotalLeiao = leilao.getLances().stream()
			.map(m -> m.getValor())
			.reduce(0.0, (x,y) -> x + y).doubleValue();
		
		double valorMedio = valorTotalLeiao / leilao.getLances().size();
		DecimalFormat format = new DecimalFormat("0.##");
		return Double.parseDouble(format.format(valorMedio).replace(",", "."));
	}
}
