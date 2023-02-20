package procesamiento;

public class Ingredientes {
	private String nombre;
	private int costoAdicional; 
	private int calorias;
	
	public Ingredientes (String nombre, int costoAdicional, int calorias) {
		this.nombre= nombre;
		this.costoAdicional =costoAdicional;
		this.calorias=calorias;
		
	}
	public String getNombre() {
		return nombre;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}
	public int getCalorias() {
		return calorias;
	}
}
