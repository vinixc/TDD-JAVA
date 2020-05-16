package br.com.vini.leilao.servico;

import java.text.DecimalFormat;

import br.com.vini.leilao.dominio.Lance;
import br.com.vini.leilao.dominio.Leilao;

public class Avaliador {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;

	public void avalia(Leilao leilao) {
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos ) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos ) menorDeTodos = lance.getValor();
		}
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
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
