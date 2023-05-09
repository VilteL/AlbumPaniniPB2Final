package Test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import dominio.Figurita;
import dominio.Selecciones;
import dominio.Sistema;
import dominio.Usuario;
import dominio.UsuarioAdministrador;
import dominio.UsuarioFinal;
import excepciones.CodigoExistente;
import excepciones.FiguritaNoDisponible;
import excepciones.FiguritaRepetida;

public class test {

	@Test
	public void queSePuedaCrearUnaFigurita() {
		
		Figurita figurita = new Figurita(1, 'A', Selecciones.QATAR, "Pablito merquero", 200.000);
		
		assertNotNull(figurita);
		
	}
	@Test
	public void queSePuedaCrearUnAdministrador() {
		
		Usuario usuarioAdministrador = new UsuarioAdministrador("Matias");
	
		assertNotNull(usuarioAdministrador);
	}
	@Test
	public void queSePuedaCrearUnUsuarioFinal() {
		
		Usuario usuarioFinal = new UsuarioFinal("el pepe");
		
		assertNotNull(usuarioFinal);
		
	}
	@Test
	public void queUnAdministradorPuedaAgregarUnaFigurita() {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioAdministrador = new UsuarioAdministrador("Matias");
		
		sistema.agregarUsuario(usuarioAdministrador);
		
		Figurita figurita = new Figurita(1, 'A', Selecciones.QATAR, "Pablito merquero", 200.000);
		
		try {
			usuarioAdministrador.agregarFigurita(sistema, figurita);
		} catch (CodigoExistente e) {
			e.printStackTrace();
		}
		
		Set figus = sistema.getFiguritas();
		Integer valorEsperado = 1; 
		Integer valorDevuelto = figus.size();
		
		assertEquals(valorEsperado, valorDevuelto);
	}
	@Test
	public void queLasFiguritasAgregadasDeFormaDesordenadaQuedenOrdenadas() {
		
		Sistema sistema = new Sistema ();
		Usuario usuarioFinal = new UsuarioFinal("el pepe");
		sistema.agregarUsuario(usuarioFinal);
		
		Figurita figurita = new Figurita(10, 'C', Selecciones.ARGENTINA, "Messi", 200000.000);
		Figurita figu = new Figurita(9, 'C', Selecciones.ARGENTINA, "Alvarez", 50000.00);
		Figurita figu1 = new Figurita(8, 'C', Selecciones.ARGENTINA, "Acuña", 20000.00);
		
		sistema.agregarFigurita(figurita);
		sistema.agregarFigurita(figu);
		sistema.agregarFigurita(figu1);
		
		
		sistema.agregarUsuario(usuarioFinal);
		
		try {
			usuarioFinal.agregarFigurita(sistema, figurita);
			usuarioFinal.agregarFigurita(sistema, figu);
			usuarioFinal.agregarFigurita(sistema, figu1);
		} catch (CodigoExistente e) {
			e.printStackTrace();
			}
		
		assertEquals(figu1,((UsuarioFinal)usuarioFinal).getStockDisponible().get(0));
		assertEquals(figu,((UsuarioFinal)usuarioFinal).getStockDisponible().get(1));
		assertEquals(figurita,((UsuarioFinal)usuarioFinal).getStockDisponible().get(2));
		
		
	}
	@Test	(expected = CodigoExistente.class)
	public void queUnAdministradorNoPuedaAgregarUnaFiguritaExistente() throws CodigoExistente {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioAdministrador = new UsuarioAdministrador("Matias");
		
		sistema.agregarUsuario(usuarioAdministrador);
		
		Figurita figurita = new Figurita(1, 'A', Selecciones.QATAR, "Pablito merquero", 200.000);
		sistema.agregarFigurita(figurita);
		
		
			usuarioAdministrador.agregarFigurita(sistema, figurita);
			usuarioAdministrador.agregarFigurita(sistema, figurita);
	}
	@Test 
	public void queUnUsuarioFinalSiPuedaAgregarFiguritasExistentes() {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioFinal = new UsuarioFinal("el pepe");
		sistema.agregarUsuario(usuarioFinal);
		
		Figurita figurita = new Figurita(1, 'A', Selecciones.QATAR, "Pablito merquero", 200.000);
		sistema.agregarFigurita(figurita);
		
		
		try {
			usuarioFinal.agregarFigurita(sistema, figurita);
			usuarioFinal.agregarFigurita(sistema, figurita);
			usuarioFinal.agregarFigurita(sistema, figurita);
		} catch (CodigoExistente e) {
			e.printStackTrace();
		}
		
		Integer valorEsperado = 3;
		Integer valorDevuelto = ((UsuarioFinal) usuarioFinal).getStockDisponible().size();
		
		assertEquals(valorEsperado, valorDevuelto);
		
	}
	@Test
	public void queUnUsuarioFinalPuedaPegarUnaFigurita() {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioFinal = new UsuarioFinal("el pepe");
		
		Figurita figurita = new Figurita(10, 'C', Selecciones.ARGENTINA, "Messi", 20000.000);
		
		try {
			((UsuarioFinal) usuarioFinal).pegarFigurita(figurita);
		} catch (FiguritaNoDisponible | FiguritaRepetida e) {
			e.printStackTrace();
		}
		Integer valorEsperado = 1;
		Integer valorDevuelto = ((UsuarioFinal) usuarioFinal).getAlbum().size();

		assertEquals(valorEsperado, valorDevuelto);
		
	}
	@Test
	public void queSePuedaRealizarElIntercambioDeFiguritasEntreDosUsuariosFinales() {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioFinal1 = new UsuarioFinal("usuario1");
		Usuario usuarioFinal2 = new UsuarioFinal("usuario2");
		
		sistema.agregarUsuario(usuarioFinal1);
		sistema.agregarUsuario(usuarioFinal2);
		
		Figurita figurita = new Figurita(10, 'C', Selecciones.ARGENTINA, "Messi", 20000.000);
		Figurita figu = new Figurita(9, 'C', Selecciones.ARGENTINA, "Alvarez", 50000.00);
		sistema.agregarFigurita(figurita);
		sistema.agregarFigurita(figu);
		
		((UsuarioFinal) usuarioFinal1).agregarFigurita(sistema, figurita);
		((UsuarioFinal) usuarioFinal2).agregarFigurita(sistema, figu);
		
		try {
			sistema.intercambiarFiguritas(usuarioFinal1, usuarioFinal2, figurita, figu);
		} catch (FiguritaNoDisponible | CodigoExistente e) {
			e.printStackTrace();
		}
		Figurita figuritaIntercambiada1 = ((UsuarioFinal) usuarioFinal1).getStockDisponible().get(0);
		Figurita figuritaIntercambiada2 = ((UsuarioFinal) usuarioFinal2).getStockDisponible().get(0);
		
		assertEquals(figurita, figuritaIntercambiada2);
		assertEquals(figu, figuritaIntercambiada1);
		
		
		
		
	}
	@Test	(expected = FiguritaNoDisponible.class)
	public void queNoSePuedaIntercambiarUnaFiguritaDeUnUsuarioQueNoLaTenga() throws FiguritaNoDisponible, CodigoExistente {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioFinal1 = new UsuarioFinal("usuario1");
		Usuario usuarioFinal2 = new UsuarioFinal("usuario2");
		
		sistema.agregarUsuario(usuarioFinal1);
		sistema.agregarUsuario(usuarioFinal2);
		
		Figurita figurita = new Figurita(10, 'C', Selecciones.ARGENTINA, "Messi", 20000.000);
		Figurita figu = new Figurita(9, 'C', Selecciones.ARGENTINA, "Alvarez", 50000.00);
		
		
		((UsuarioFinal) usuarioFinal1).agregarFigurita(sistema, figurita);
		
		sistema.intercambiarFiguritas(usuarioFinal1, usuarioFinal2, figurita, figu);
		
		
		
	}
	@Test (expected = FiguritaNoDisponible.class)
	public void queNoSePuedaIntercambiarUnaFiguritaDeUnUsuarioQueYaLaHayaPegado() throws FiguritaNoDisponible, CodigoExistente {
		
		Sistema sistema  = new Sistema();
		Usuario usuarioFinal1 = new UsuarioFinal("usuario1");
		Usuario usuarioFinal2 = new UsuarioFinal("usuario2");
		
		sistema.agregarUsuario(usuarioFinal1);
		sistema.agregarUsuario(usuarioFinal2);
		
		Figurita figurita = new Figurita(10, 'C', Selecciones.ARGENTINA, "Messi", 20000.000);
		Figurita figu = new Figurita(9, 'C', Selecciones.ARGENTINA, "Alvarez", 50000.00);
		
		
		((UsuarioFinal) usuarioFinal1).agregarFigurita(sistema, figurita);
		((UsuarioFinal) usuarioFinal2).agregarFigurita(sistema, figu);
		
		try {
			((UsuarioFinal) usuarioFinal1).pegarFigurita(figurita);
		} catch (FiguritaNoDisponible | FiguritaRepetida e) {
			e.printStackTrace();
		}
		
		sistema.intercambiarFiguritas(usuarioFinal1, usuarioFinal2, figurita, figu);
		
		
	}
}
