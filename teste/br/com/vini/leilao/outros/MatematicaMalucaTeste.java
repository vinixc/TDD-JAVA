package br.com.vini.leilao.outros;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.vini.leilao.outro.MatematicaMaluca;

public class MatematicaMalucaTeste {
	
	private MatematicaMaluca mateMaluca;
	
	@Before
	public void init() {
		mateMaluca = new MatematicaMaluca();
	}

	@Test
	public void testaNumeroMaior30() {
		
		assertEquals(50*4, mateMaluca.contaMaluca(50));
		
	}
	
	@Test
	public void testaNumeroMaior10EMenorQue30() {
		
		assertEquals(25 * 3, mateMaluca.contaMaluca(25));
	}
	
	@Test
	public void testaNumeroEmOutraCondicao() {
		
		assertEquals(5 * 2, mateMaluca.contaMaluca(5));
	}

}
