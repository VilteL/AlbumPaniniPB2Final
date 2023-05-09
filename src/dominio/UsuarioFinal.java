package dominio;

import java.util.*;

import excepciones.FiguritaNoDisponible;
import excepciones.FiguritaRepetida;

public class UsuarioFinal extends Usuario{
	
	private List<Figurita> stockDisponible;
	private Set<Figurita> album;
	
	public UsuarioFinal(String nombre) {
		super(nombre);
		this.stockDisponible = new ArrayList<Figurita>();
		this.album = new HashSet<Figurita>();
	}

	public List<Figurita> getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(List<Figurita> stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	public Set<Figurita> getAlbum() {
		return album;
	}

	public void setAlbum(Set<Figurita> album) {
		this.album = album;
	}

	@Override
	public void agregarFigurita(Sistema sistema, Figurita figurita) {
		
		if(sistema.verificarSiElUsuarioExiste(this)&&sistema.verificarSiLaFiguritaEstaEnElSistema(figurita)) {
			this.stockDisponible.add(figurita);
			Collections.sort(this.stockDisponible);
		}
	}
	public void pegarFigurita(Figurita figurita) throws FiguritaNoDisponible, FiguritaRepetida {
		
		for (Figurita figurita1 : this.stockDisponible) {
			if(figurita1.equals(figurita)) { } 
			else {
				throw new FiguritaNoDisponible();
			}
			
		}
		for(Figurita figurita1 : this.album) {
			if(figurita1.equals(figurita)) {
				throw new FiguritaRepetida();
			}
		}
		this.album.add(figurita);
		this.stockDisponible.remove(figurita);
	}
	public void intercambiarFiguritas(Figurita figurita) throws FiguritaNoDisponible {
		
		if(this.stockDisponible.size()!=0) {
		for (Figurita figurita1 : this.stockDisponible) {
			if(figurita1.equals(figurita)) {
				this.stockDisponible.remove(figurita);
				break;
			}
			
			}
		}
		
		else {
			throw new FiguritaNoDisponible();
		}
	}

	
	
}
