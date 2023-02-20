package procesamiento;

import java.util.ArrayList;

public class ProductoAjustado implements Producto  {
	private ArrayList<Ingredientes> ingredientesAgregados = new ArrayList();
	private ArrayList<Ingredientes> ingredientesEliminados = new ArrayList();
	public int precio;
	public int calorias;
	public String nombre;
	private String textoFactura;
	private final int precioBase;
	
	public ProductoAjustado (ProductoMenu base) {
		precio = base.getPrecio();
		nombre = base.getNombre();
		calorias= base.getCalorias();
		textoFactura=base.generarTextofactura();
		precioBase=precio;
		
	}
	public String getNombre() {
		
		return nombre ;
	}
	private int getprecioBase() {
		return precioBase;
	}
	public int getPrecio() {
		precio=precioBase;
		if (ingredientesAgregados.size()>0) {
			for (int a =0; a<ingredientesAgregados.size(); a++) {
				precio+= ingredientesAgregados.get(a).getCostoAdicional();
			}
		}
		return precio;
	}
	
		
	@Override
	public String generarTextofactura() {
		textoFactura="";
		textoFactura= (nombre+ "  -->	Precio Original " +getprecioBase()+"\n");
		for (int a =0; a<ingredientesAgregados.size();a++) {
			textoFactura+=( "Ingredientes Adiccionados: ");
			textoFactura+= ingredientesAgregados.get(a).getNombre() + "  $";
			textoFactura += ingredientesAgregados.get(a).getCostoAdicional()+ "\t";
			textoFactura += ingredientesAgregados.get(a).getCalorias()+ "\n";
		}
		for (int a =0; a<ingredientesEliminados.size();a++) {
			textoFactura+=( "Ingredientes Eliminados: ");
			textoFactura+= ingredientesEliminados.get(a).getNombre() + "  \n";
			
		}
		textoFactura += ("Precio Final de "+nombre+": $ "+getPrecio());
		textoFactura+=("\n\r");
		
		return textoFactura;
	}
	public void Agregar(Ingredientes este) {
		ingredientesAgregados.add(este);
	}
	public void eliminar(Ingredientes esto) {
		ingredientesEliminados.add(esto);
	}
	public ArrayList<Ingredientes> agregados(){
		return ingredientesAgregados;
		
	}
	public ArrayList<Ingredientes> eliminados(){
		return ingredientesEliminados;
	}
	
	public int getCalorias() {
		
		return calorias;
	}
}
