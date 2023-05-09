package dominio;

import java.util.Objects;

import excepciones.CodigoExistente;

public abstract class Usuario {
	
	private static Integer usuarioExistentes = 0;
	
	private String nombre;
	private Integer Id;
	
	public Usuario(String nombre) {
		this.nombre= nombre;
		this.Id= usuarioExistentes;
		
		usuarioExistentes++;
	}
	
	public abstract void agregarFigurita(Sistema sistema, Figurita figurita) throws CodigoExistente;

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(Id, other.Id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	
	
}
