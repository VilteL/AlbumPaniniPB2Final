package dominio;

import java.util.HashSet;

import excepciones.CodigoExistente;

public class UsuarioAdministrador extends Usuario {

	public UsuarioAdministrador(String nombre) {
		super(nombre);
	}

	@Override
	public void agregarFigurita(Sistema sistema,Figurita figurita) throws CodigoExistente {
		
		HashSet<Figurita> figuritasDelSistema = (HashSet<Figurita>) sistema.getFiguritas();
		if(sistema.verificarSiElUsuarioExiste(this)) {
			for (Figurita figurita2 : figuritasDelSistema) {
				if(figurita2.equals(figurita)) {
					throw new CodigoExistente();
				}
			}
		}
	sistema.getFiguritas().add(figurita);
	}
	

}
