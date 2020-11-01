//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class YodafyClienteUDP {

	public static void main(String[] args) {
		
		byte []buferEnvio;
		byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		InetAddress direccion;
		DatagramPacket paqueteEnvio,paqueteRecepcion;
		DatagramSocket socket;
		
		try {
			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			buferEnvio="Al monte del volcán debes ir sin demora".getBytes();
			
			socket = new DatagramSocket();
			direccion = InetAddress.getByName(host);
			paqueteEnvio = new DatagramPacket(buferEnvio, buferEnvio.length,direccion,port);
			socket.send(paqueteEnvio);

			paqueteRecepcion = new DatagramPacket(buferRecepcion,buferRecepcion.length);
			socket.receive(paqueteRecepcion);

			System.out.println("Recibido: ");
			System.out.println(new String(paqueteRecepcion.getData(),0,paqueteRecepcion.getLength()));

			socket.close();

			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
