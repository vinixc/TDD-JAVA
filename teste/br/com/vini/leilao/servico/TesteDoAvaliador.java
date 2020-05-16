package br.com.vini.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.vini.leilao.dominio.Lance;
import br.com.vini.leilao.dominio.Leilao;
import br.com.vini.leilao.dominio.Usuario;

public class TesteDoAvaliador {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("Play 3 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void testaValorMedioLeilao() {
		
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("Play 3 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		leilao.propoe(new Lance(maria, 400.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		
		double valorMedioReturn = leiloeiro.getValorMedio(leilao);
		double valorMedioEsperado = 350;
		
		assertEquals(valorMedioEsperado, valorMedioReturn, 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("joao");
		Leilao leilao = new Leilao("Play 3 Novo");
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLance() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("Play 3 Novo");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		assertEquals(400.0, tresMaiores.get(0).getValor(),0.00001);
		assertEquals(300.0, tresMaiores.get(1).getValor(),0.00001);
		assertEquals(200.0, tresMaiores.get(2).getValor(),0.00001);
	}

}
