package procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Restaurante {
	private ArrayList<Ingredientes> ingredientesLista = new ArrayList();
	private static ArrayList<ProductoMenu> menuBase = new ArrayList();
	private ArrayList<Combo> comboLista = new ArrayList<Combo>() ;
	private ArrayList<Bebidas> listaBebidas =new ArrayList<Bebidas>();
	private static Pedido Pedidoencurso;
	private String TF=("\t\t\t\t\t\t RESTAURANTE TALLER 2  \n\r ");
	
	
	public void iniciarPedido( String nombreCliente, String direccionCliente) {
		Pedido nuevo= new Pedido(nombreCliente, direccionCliente);
		Pedidoencurso =nuevo;
		TF=("\t\t\t\t\t\t RESTAURANTE TALLER 2  \n\r ");
		TF+=  "Id Pedido: "+nuevo.getidPedido()+"\n\r";
		
	
		
	}
	public void AgregarElementosPedido(Pedido nuevo){
		boolean continuar = true;
		TF=("\t\t\t\t\t\t RESTAURANTE TALLER 2  \n\r ");
		TF+=  "Id Pedido: "+nuevo.getidPedido()+"\n\r";
		while (continuar) {
			System.out.println("¿Que Menu deseas ver?\n\n1.Productos Indidivuales \n2.Combos\n3.Bebidas \n\n*Ingresa el numero del menu que desees ver*");
			 int tipoMenu = Integer.parseInt(input("Por favor seleccione una opción:"));
			 if (tipoMenu==1) {
				 imprimirMenu();
				 int productoOrdenado = Integer.parseInt(input("Por favor ingrese el numero del elemento que desea ordenar :"));
				 System.out.println("Deseas Modificar el producto seleccionado??\nSelecciona la opcion que desees");
				 nuevo.AgregarPrecioNeto(menuBase.get(productoOrdenado-1).getPrecio());
				 nuevo.AgregarCalorias(menuBase.get(productoOrdenado-1).getCalorias());
				 int uno = Integer.parseInt(input("1. Si \n2. No"));
				 if (uno == 1) {
					 ProductoAjustado productopedido = new ProductoAjustado(menuBase.get((productoOrdenado-1)));
					 AgregarIngredientes(productopedido);
				     nuevo.agregarProducto(productopedido);
				     TF+= productopedido.generarTextofactura();
					 }	
				 else {
					 nuevo.agregarProducto(menuBase.get((productoOrdenado-1)));
					 TF+= menuBase.get((productoOrdenado-1)).generarTextofactura();
				 }
				 if (productoOrdenado > 22){
					 System.out.println("Porfavor ingrese un elemento del Menu Valido ");
				 }
				 
			 }
			 else if (tipoMenu==2) {
				 imprimirCombos();
				 int productoOrdenado = Integer.parseInt(input("Por favor ingrese el numero de Combo que desea ordenar :"));
				 ArrayList<ProductoMenu> prod = comboLista.get(productoOrdenado-1).getProductos();
				 nuevo.agregarProducto(prod.get(0));
				 nuevo.agregarProducto(prod.get(1));
				 nuevo.agregarProducto(prod.get(2));
				 Combo Este = comboLista.get(productoOrdenado-1);
				 int precio = (int) comboLista.get(productoOrdenado-1).getPrecio();
				 int calorias3= comboLista.get(productoOrdenado-1).getCalorias();
				 nuevo.AgregarPrecioNeto(precio);
				 nuevo.AgregarCalorias(calorias3);
				 TF+= Este.generarTextoFactura();
				 if (productoOrdenado > 4){
					 System.out.println("Porfavor ingrese un elemento del Menu Valido ");
				 }
				 
			 }
			 else if (tipoMenu==3) {
				 imprimirBebidas();
				 int productoOrdenado = Integer.parseInt(input("Por favor ingrese el numero de Bebida que desea ordenar :"));
				 nuevo.agregarProducto(listaBebidas.get(productoOrdenado-1));
				 nuevo.AgregarPrecioNeto(listaBebidas.get(productoOrdenado-1).getPrecio());
				 nuevo.AgregarCalorias(listaBebidas.get(productoOrdenado-1).getCalorias());
				 TF+= listaBebidas.get(productoOrdenado-1).generarTextofactura();
			 }
			 int decision = Integer.parseInt(input("\nDeseas ordenar algo mas? \n Selecciona la opcion correspondiente a tu respuesta :\n1.Si\n2.No\n"));
			 if (decision == 2) {
				 continuar=false;
			 }
			 
			 
		}
	}
	public void cerrarYGuardarPedido(Pedido pedido) {
		int nombre= pedido.getidPedido();
		String Factura = TF+ pedido.generarTextoFactura();
		System.out.println(Factura);
		String nombreFactura = "Factura"+Integer.toString(nombre);
		File archivo = new File (nombreFactura);
		pedido.guardarFactura(archivo);
		
		 try {
	            PrintWriter writer = new PrintWriter(new FileWriter(archivo));
	            writer.print(Factura);
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		
	}
	public static Pedido getPedidoEnCurso() {
		
		return Pedidoencurso;
	}
	private void AgregarIngredientes(ProductoAjustado pedido){
		
		boolean seguir = true;
		while (seguir) {
			int cambio = Integer.parseInt(input("Deseas agregar o eliminar un producto?\n1. Agregar\n2. Elminar "));
			imprimirIngredientes();
			if (cambio==1) {
				int decision = Integer.parseInt(input("Ingresa el numero del ingrediente que desees agregar"));
				pedido.Agregar(ingredientesLista.get(decision-1));

			}
			else if (cambio == 2) {
				int decision = Integer.parseInt(input("Ingresa el numero del ingrediente que desees eliminar"));
				pedido.eliminar(ingredientesLista.get(decision-1));
			}
			
			int decision2 = Integer.parseInt(input("Deseas hacerle mas cambios a tu producto?\n1. Si \n2. No "));
			if (decision2==2) {
				seguir=false;
			}
		}
		
	}
	public void imprimirIngredientes() {
		for (int i =0 ; i<ingredientesLista.size();i++) {
			String intento= ingredientesLista.get(i).getNombre();
			int intento2 = ingredientesLista.get(i).getCostoAdicional();
			System.out.print((i+1)+": "+intento+ "  Precio : "+intento2 +"\n");
		}
		
	}
	public ArrayList<Combo> getCombos(){
		return comboLista;
	}
	public static ArrayList<ProductoMenu> getMenuBase(){
		return menuBase;
			}
	public ArrayList<Ingredientes> getIngredientes(){
		return ingredientesLista;
		
		
	}
	public void cargarInformacionRestaurante (File archivoIngredientes, File archivoMenu, File archivoCombos, File archivoBebidas) {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarBebidas(archivoBebidas);
	}
	
	private void cargarIngredientes(File archivoIngredientes ) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
			String linea = br.readLine();
			while (linea!= null) {
				String[] partes = linea.split(";");
				String nombre= partes[0];
				int costo = Integer.parseInt(partes[1]);
				int calorias =Integer.parseInt(partes[2]);
				Ingredientes nuevo = new Ingredientes (nombre, costo, calorias);
				ingredientesLista.add(nuevo);
				linea = br.readLine();
		}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void cargarBebidas(File archivoBebidas) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoBebidas));
			String linea = br.readLine();
			while (linea!= null) {
				String[] partes = linea.split(";");
				String nombre= partes[0];
				int costo = Integer.parseInt(partes[1]);
				int calorias = Integer.parseInt(partes[2]);
				Bebidas nuevo = new Bebidas (nombre, costo,calorias);
				listaBebidas.add(nuevo);
				linea = br.readLine();
		}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void cargarMenu (File archivoMenu) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
			String linea = br.readLine();
			while (linea!= null) {
				String[] partes = linea.split(";");
				String nombre= partes[0];
				int costo = Integer.parseInt(partes[1]);
				int calorias = Integer.parseInt(partes[2]);
				ProductoMenu nuevo = new ProductoMenu (nombre, costo,calorias);
				menuBase.add(nuevo);
				linea = br.readLine();
		}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void cargarCombos (File archivoCombo) {

		
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivoCombo));
			String linea = br.readLine();
			while (linea!= null) {
				ArrayList<String> ListaProdCombos=new ArrayList();
				String[] partes = linea.split(";");
				String nombre= partes[0];
				String descuento=partes[1];
				descuento= descuento.substring(0,descuento.length()-1);
				double descuento2 = Double.valueOf(descuento);
				String productosCombo= partes[2];
				ListaProdCombos.add(productosCombo);
				productosCombo= partes[3];
				ListaProdCombos.add(productosCombo);
				productosCombo= partes[4];
				ListaProdCombos.add(productosCombo);
				int calorias =Integer.parseInt(partes[5]);
				Combo nuevo = new Combo (nombre, descuento2, ListaProdCombos, calorias);
				comboLista.add(nuevo);
				linea = br.readLine();
				
				
				
		}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	public void imprimirMenu() {
		System.out.println("\n\r\t\t\t\t MENU\n");
		for (int i =0 ; i<menuBase.size();i++) {
			String intento= menuBase.get(i).getNombre();
			int intento2 = menuBase.get(i).getPrecio();
			System.out.print((i+1)+": " +intento+ "Precio: $"+intento2 +"\n");
			}
			
		}
	public void imprimirCombos() {
		System.out.println("\n\r\t\t\t\t COMBOS\n");
		
		for (int i=0; i<comboLista.size(); i++) {
			String intento= comboLista.get(i).getNombre();
			double intento2=comboLista.get(i).getPrecio();
			System.out.print((i+1)+": "+intento+ "          Precio: $"+intento2 +"\n");
		}
	}
	public void imprimirBebidas() {
System.out.println("\n\r\t\t\t\t BEBIDAS\n");
		
		for (int i=0; i<listaBebidas.size(); i++) {
			String intento= listaBebidas.get(i).getNombre();
			double intento2=listaBebidas.get(i).getPrecio();
			System.out.print((i+1)+": "+intento+ "          Precio: $"+intento2 +"\n");
		}
	}
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje );
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public boolean equal (Pedido pedido1, Pedido pedido2) {
		boolean respuesta=false;
		if (pedido1.intento().equals(pedido2.intento())) {
			respuesta=true;
			
		}
		return respuesta;
	}
	}
	
