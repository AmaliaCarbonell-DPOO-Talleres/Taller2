package procesamiento;

import java.io.File;
import java.util.ArrayList;

public class Pedido {
	private static int numeroPedidos=0;
	private static int idPedido=0;
	private String nombreCliente;
	private String direccionCliente; 
	private ArrayList<Producto> prodPedidos=new ArrayList();
	private double Neto=0;
	private int calorias=0;
	
	
	
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente= nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos =+1;
	}
	public int getidPedido() {
		idPedido +=1;
		return idPedido;
	}
	public void agregarProducto(Producto nuevoitem) {
		
		prodPedidos.add(nuevoitem);
		
	}
	public void elementos () 
	{
		for (int i=0; i< prodPedidos.size(); i++) {
			System.out.println(prodPedidos.get(i).getNombre());
		}
	}
	public ArrayList<Producto> intento(){
		return prodPedidos;
	}
	public void AgregarCalorias (int calorias2) {
		calorias+=calorias2;
	}
	public void AgregarPrecioNeto(int precio) {
		Neto+=precio;
		
	}
	private int getCaloriasTotales() {
		return calorias;
	}
	private double getPrecioNetoPedido() {
		
		return Neto;
	}
	private double getPrecioTotalPedido() {
		double total = getPrecioNetoPedido() + getPrecioIVAPedido();
		return total;
	}
	private double getPrecioIVAPedido() {
		double iva= getPrecioNetoPedido()*0.19;
		return iva;
	}
	public String generarTextoFactura() {
		String textoFactura= "\n\rPrecio Neto Productos : $"+getPrecioNetoPedido();
		textoFactura += "\n\rPrecio Iva Pedido: $"+ getPrecioIVAPedido();
		textoFactura+= "\n\rPrecio Total Pedido: $"+getPrecioTotalPedido();
		textoFactura+= "\n\rCalorias Totales Pedido: "+getCaloriasTotales();
		textoFactura+= "\n\r______________________________________________________________________";
		
		
		return textoFactura ;
	}
	public void guardarFactura(File archivo) {
		
	}
	public boolean equals(Object o) {
		
		return false;
	}
	public boolean equal (Pedido pedido1, Pedido pedido2) {
		boolean respuesta=false;
		if (pedido1.intento().equals(pedido2.intento())) {
			respuesta=true;
			
		}
		return respuesta;
	}

}
