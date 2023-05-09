package dominio;

import java.util.Objects;

public class Figurita implements Comparable<Figurita>{
	
	
	private Integer nro; 
	private Character letraGrupo;
	private Selecciones seleccion;
	private String nombreJugador;
	private Double valorJugadorEnElMercado;
	
	public Figurita(Integer numero, Character grupo, Selecciones seleccion, String nombreJugador, Double valor) {
		this.letraGrupo= grupo;
		this.seleccion= seleccion;
		this.nombreJugador= nombreJugador;
		this.valorJugadorEnElMercado = valor;
		this.nro = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(letraGrupo, nombreJugador, nro, seleccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figurita other = (Figurita) obj;
		return Objects.equals(letraGrupo, other.letraGrupo) && Objects.equals(nombreJugador, other.nombreJugador)
				&& Objects.equals(nro, other.nro) && seleccion == other.seleccion;
	}

	@Override
	public int compareTo(Figurita o) {
		if(this.letraGrupo.equals(o.letraGrupo)){
			if(this.seleccion.equals(o.seleccion)) {
					return this.nro.compareTo(o.nro);
				}
				return this.seleccion.compareTo(o.seleccion);
			}
		return this.letraGrupo.compareTo(o.letraGrupo);
		
		}
}
