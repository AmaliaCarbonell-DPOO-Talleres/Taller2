package procesamiento;

public class Bebidas implements Producto {
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public Bebidas(String nombre, int precioBase, int calorias){
		this.nombre= nombre;
		this.precioBase=precioBase;
		this.calorias= calorias;
	}
	@Override
	public int getPrecio() {
		return precioBase;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String generarTextofactura() {
		String textoFactura= nombre + "---> Precio: $"+ precioBase + "     Calorias : "+calorias;
		textoFactura+=("\n\r");
		return textoFactura;
	}
	@Override
	public int getCalorias() {
		
		return calorias;
	}

}
