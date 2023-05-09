package dominio;

import java.util.*;

import excepciones.CodigoExistente;
import excepciones.FiguritaNoDisponible;

public class Sistema {
	
	private Set<Usuario> usuarios;
	private Set<Figurita> figuritas;
	
	public Sistema() {
		this.usuarios = new HashSet<Usuario>();
		this.figuritas = new HashSet<Figurita>();		
	}
	
	
	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	
	public void agregarFigurita(Figurita figurita) {
		this.figuritas.add(figurita);
	}
	public Boolean verificarSiElUsuarioExiste(Usuario usuario) {
		return this.usuarios.contains(usuario);
	}

	public void intercambiarFiguritas(Usuario usuario1, Usuario usuario2, Figurita figurita1, Figurita figurita2) throws FiguritaNoDisponible, CodigoExistente {
		
		if(verificarSiElUsuarioExiste(usuario1)&&verificarSiElUsuarioExiste(usuario2)) {
			
			((UsuarioFinal) usuario1).intercambiarFiguritas(figurita1);
			((UsuarioFinal) usuario2).intercambiarFiguritas(figurita2);
			
			usuario1.agregarFigurita(this, figurita2);
			usuario2.agregarFigurita(this, figurita1);	
		}
	}

	public Boolean verificarSiLaFiguritaEstaEnElSistema(Figurita figurita) {
		return this.figuritas.contains(figurita);
	}
	
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}


	public Set<Figurita> getFiguritas() {
		return this.figuritas;
	}
}
