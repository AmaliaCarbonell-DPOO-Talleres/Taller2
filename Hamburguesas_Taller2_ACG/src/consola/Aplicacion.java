package consola;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import procesamiento.Restaurante;
import procesamiento.Ingredientes;
import procesamiento.*; 



public class Aplicacion {
	
	public Restaurante restaurante=new Restaurante();
	public static Pedido PedidoenCurso=null;
	File ingredientes= new File("./data/ingredientes.txt");
	File combos = new File ("./data/combos.txt");
	File menu = new File ("./data/menu.txt");
	File bebidas = new File ("./data/Bebidas.txt");
	private static ArrayList<Pedido> listaPedidos= new ArrayList<Pedido>();
	
	
	
	

	public static void main(String[] args) {
		
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
		

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
	
	public void ejecutarAplicacion() {
	 boolean continuar= true;
	 System.out.println("\t\t\t\t\tBienvenido al Restaurante Taller 2!\nEn que te podemos ayudar hoy?\n");
	 restaurante.cargarInformacionRestaurante(ingredientes, menu, combos, bebidas);
	 while (continuar) {
		 
		 mostrarMenu();
		 int opcion = Integer.parseInt(input("Por favor seleccione una opción:"));
		 if (opcion == 1) {
			 menu();
		 }
		 else if (opcion ==2) {
			 nuevoPedido();
		 }
		 else if (opcion==3 && PedidoenCurso !=null) {
			 nuevoElemento();
		 }
		 else if (opcion==4 && PedidoenCurso !=null) {
			 cerrar();
		 }
		 else if (opcion ==5 ) {
			 consultarPedido();
		 }
		 else if (opcion==6) {
			 continuar=false;
			 
		 }
		 else if (opcion ==7 ) {
			 comparar(); 
		 }
		 else if ((opcion ==3 || opcion ==4 ) && PedidoenCurso ==null) {
			 System.out.println("Para poder ejecutar esta opcion, debe haber un pedido en curso. ");
		 }
		 else {
			 System.out.println("Porfavor ingrese una opcion valida: ");
		 }
		 
	 }
	}
	private void menu() {
		
		restaurante.imprimirMenu();
		restaurante.imprimirCombos();
		restaurante.imprimirBebidas();
		
		
	}
	private  void nuevoPedido() {
		System.out.println("nuevo pedido ");
		String nombreCliente = input("Porfavor Ingrese su nombre: ");
		String Direccion = input ("Porfavor ingrese su direccion: ");
		restaurante.iniciarPedido(nombreCliente, Direccion);
		PedidoenCurso = restaurante.getPedidoEnCurso();
		listaPedidos.add(PedidoenCurso);
		
		
	
	}
	
	
	private  void nuevoElemento() {
		Pedido pedidoEncurso= restaurante.getPedidoEnCurso();
		restaurante.AgregarElementosPedido(pedidoEncurso);
	}
	private  void cerrar() {
		Pedido pedidoEncurso= restaurante.getPedidoEnCurso();
		restaurante.cerrarYGuardarPedido(pedidoEncurso);
		PedidoenCurso=null;
		
		
		
	}
	private void consultarPedido() {
		System.out.println("consultar");
		int IdaConsultar = Integer.parseInt(input("Porfavor Ingrese el ID que quiere buscar: "));
		Pedido respuestaPedido;
		System.out.println(listaPedidos.get(0).getidPedido());
		for (int a=0; a<listaPedidos.size(); a++) {
			if (IdaConsultar==listaPedidos.get(a).getidPedido()) {
				respuestaPedido=listaPedidos.get(a);
				respuestaPedido.elementos();
			}
		}
		
	
		
	}
	private void comparar() {
		Pedido pedido1=listaPedidos.get(0);
		Pedido pedido2=listaPedidos.get(1);
		boolean respuesta = pedido1.equals(pedido2);
		System.out.println(respuesta);
		boolean esto = restaurante.equal(pedido1,pedido2);
		
	}
	public void mostrarMenu() {
		System.out.println("\n1. Mostrar Menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido ");
		System.out.println("5. Consultar informacion de un pedido");
		System.out.println("6. Salir\n");
		System.out.println("7. Comparar pedidos");
	}
}


