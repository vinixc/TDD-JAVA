package br.com.vini.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.vini.leilao.dominio.Lance;
import br.com.vini.leilao.dominio.Leilao;
import br.com.vini.leilao.dominio.Usuario;

public class TesteDoAvaliador {
	
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	private Leilao leilaoPs3;
	private Avaliador leiloeiro;

	@Before
	public void init() {
		joao = new Usuario("joao");
		jose = new Usuario("jose");
		maria = new Usuario("maria");
		leilaoPs3 = new Leilao("Play 3 Novo");
		leiloeiro = new Avaliador();
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		leilaoPs3.propoe(new Lance(joao, 250.0));
		leilaoPs3.propoe(new Lance(jose, 300.0));
		leilaoPs3.propoe(new Lance(maria, 400.0));
		
		leiloeiro.avalia(leilaoPs3);
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void testaValorMedioLeilao() {
		
		leilaoPs3.propoe(new Lance(joao, 250.0));
		leilaoPs3.propoe(new Lance(jose, 300.0));
		leilaoPs3.propoe(new Lance(maria, 400.0));
		leilaoPs3.propoe(new Lance(maria, 400.0));
		leilaoPs3.propoe(new Lance(maria, 400.0));
		
		double valorMedioReturn = leiloeiro.getValorMedio(leilaoPs3);
		double valorMedioEsperado = 350;
		
		assertEquals(valorMedioEsperado, valorMedioReturn, 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		leilaoPs3.propoe(new Lance(joao, 1000.0));
		
		leiloeiro.avalia(leilaoPs3);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLance() {
		
		leilaoPs3.propoe(new Lance(joao, 100.0));
		leilaoPs3.propoe(new Lance(maria, 200.0));
		leilaoPs3.propoe(new Lance(joao, 300.0));
		leilaoPs3.propoe(new Lance(maria, 400.0));
		
		leiloeiro.avalia(leilaoPs3);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		assertEquals(400.0, tresMaiores.get(0).getValor(),0.00001);
		assertEquals(300.0, tresMaiores.get(1).getValor(),0.00001);
		assertEquals(200.0, tresMaiores.get(2).getValor(),0.00001);
	}
}
