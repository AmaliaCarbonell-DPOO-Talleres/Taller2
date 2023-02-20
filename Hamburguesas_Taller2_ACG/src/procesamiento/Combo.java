package procesamiento;

import java.util.ArrayList;
import procesamiento.Restaurante;

public class Combo {
	private double descuento;
	private double precio;
	private String nombreCombo;
	private String textoFactura;
	private int calorias;
	private ArrayList<String> productos =new ArrayList<String>();
	ArrayList<ProductoMenu> ProductosCombo = new ArrayList();
	
	public Combo (String nombre, double descuento, ArrayList<String> listaProductos, int calorias) {
		this.nombreCombo =nombre;
		this.descuento= descuento;
		this.productos=listaProductos; 
		this.calorias=calorias;
	}
	public void agregarItemACombo(Producto itemCombo) {
		ArrayList<ProductoMenu> MenuBase = Restaurante.getMenuBase();
		
		for (int a = 0;  a < productos.size();a++) {
			for (int i =0 ; i< MenuBase.size();i++) {
				if (productos.get(a).equals(MenuBase.get(i).getNombre())) {
					ProductosCombo.add(MenuBase.get(i));
					System.out.println(MenuBase.get(i).getNombre());
				}
		}
		}
	}
	public int getCalorias() {
		return calorias;
	}
	public double getPrecio() {
		precio=0;
		ArrayList<ProductoMenu> MenuBase = Restaurante.getMenuBase();// todos los productos del menu para calcular el precio
		for (int a = 0;  a < productos.size();a++) {
			for (int i =0 ; i< MenuBase.size();i++) {
				if (productos.get(a).equals(MenuBase.get(i).getNombre())) {
					precio+= MenuBase.get(i).getPrecio();
					ProductosCombo.add(MenuBase.get(i));
				}
		}
		}
		
		precio = precio- (precio*(descuento/100));
		return precio;
	}
	public String generarTextoFactura() {
		textoFactura= nombreCombo +" Descuento :"+descuento+"%";
		textoFactura += ("");
		for (int a=0; a<productos.size();a++) {
			textoFactura+= "\n   - "+productos.get(a);
		}
		textoFactura+= "\nPrecio Combo: $"+precio+"    Calorias: "+calorias+"\n";
		
		
		return textoFactura;
	}
	public String getNombre() {
		return nombreCombo;
	}
	public ArrayList<ProductoMenu> getProductos(){
		
		return ProductosCombo;
	}
}
