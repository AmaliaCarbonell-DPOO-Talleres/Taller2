package procesamiento;

public class ProductoMenu implements Producto {
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public ProductoMenu(String nombre, int precioBase, int calorias){
		this.nombre= nombre;
		this.precioBase=precioBase;
		this.calorias=calorias;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getPrecio() {
		return precioBase;
	}
	public int getCalorias() {
		return calorias;
	}
	@Override
	public String generarTextofactura() {
		String textoFactura="";
		textoFactura+= nombre + "---> Precio: $"+ precioBase+"      Calorias:"+calorias;
		textoFactura+=("\n\r");
		return textoFactura;
	}
	
}
