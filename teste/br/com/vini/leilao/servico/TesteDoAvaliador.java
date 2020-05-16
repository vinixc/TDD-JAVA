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
		leilaoPs3.propoe(new Lance(joao, 400.0));
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
		leilaoPs3.propoe(new Lance(joao, 500.0));
		
		leiloeiro.avalia(leilaoPs3);
		
		List<Lance> tresMaiores = leiloeiro.getMaiores();
		assertEquals(3, tresMaiores.size());
		assertEquals(500.0, tresMaiores.get(0).getValor(),0.00001);
		assertEquals(400.0, tresMaiores.get(1).getValor(),0.00001);
		assertEquals(300.0, tresMaiores.get(2).getValor(),0.00001);
	}
	
	@Test
	public void deveRetornarDoisLances() {
		leilaoPs3.propoe(new Lance(maria, 200));
		leilaoPs3.propoe(new Lance(joao,  300));
		
		leiloeiro.avalia(leilaoPs3);
		
		List<Lance> maiores = leiloeiro.getMaiores();
		assertEquals(2, maiores.size());
		
		assertEquals(300.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void deveRetornarListaVazia() {
		
		leiloeiro.avalia(leilaoPs3);
		
		List<Lance> list = leiloeiro.getMaiores();
		
		assertEquals(0, list.size());
	}
	
	@Test
    public void deveReceberUmLance() {
        assertEquals(0, leilaoPs3.getLances().size());

        leilaoPs3.propoe(new Lance(joao, 2000));

        assertEquals(1, leilaoPs3.getLances().size());
        assertEquals(2000.0, leilaoPs3.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
    	leilaoPs3.propoe(new Lance(maria, 2000));
    	leilaoPs3.propoe(new Lance(joao, 3000));

        assertEquals(2, leilaoPs3.getLances().size());
        assertEquals(2000.0, leilaoPs3.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilaoPs3.getLances().get(1).getValor(), 0.00001);
    }
    
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {

    	leilaoPs3.propoe(new Lance(joao, 2000.0));
    	leilaoPs3.propoe(new Lance(joao, 3000.0));

        assertEquals(1, leilaoPs3.getLances().size());
        assertEquals(2000.0, leilaoPs3.getLances().get(0).getValor(), 0.00001);
    }
}
