package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

import Logging.Log;

public class Test implements Runnable {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// ServerSocket server = new ServerSocket(8088);
		// while (true) {
		// Socket client = server.accept();
		// System.out.println("Connection to server accepted! " +
		// client.getInetAddress());
		// }
		Log log = new Log();
		log.tryLog(Level.FINE, "TEST START");
		Thread t = new Thread(new Test());
		t.start();
		Server server = new Server();
		server.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			new Thread().currentThread().sleep(5000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connect conn = new Connect();
		Socket socket;
		try {
			socket = new Socket(conn.getHostname(), conn.getPort());

			PrintWriter srvout = null;
			try {
				srvout = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String in;
			String tmp;
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader sysR = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				tmp = null;
				if ((tmp = sysR.readLine()) != null)
					srvout.println(tmp);
				System.out.println(tmp);
				System.out.println("--------------------");
				in = null;
				if ((in = r.readLine()) != null)

					// IO.decodeString(in);
					System.out.println(in);
				System.out.println("--------------------");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
