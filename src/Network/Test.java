package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Logging.Log;

public class Test implements Runnable {
	private static Logger log = Logger.getLogger(Test.class.getName());
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// ServerSocket server = new ServerSocket(8088);
		// while (true) {
		// Socket client = server.accept();
		// System.out.println("Connection to server accepted! " +
		// client.getInetAddress());
		// }
		new Log();
		log.info(Test.class.getName() + " started!");
		Thread t = new Thread(new Test());
		log.info(t.getName() + " created!");
		t.start();
		Server server = new Server();
		server.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			new Thread().currentThread().sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connect conn = new Connect();
		Socket socket;
		try {
			socket = new Socket(conn.getHostname(), conn.getPort());
			log.info(socket.toString() + " connected to server!");
			PrintWriter srvout = null;
			try {
				srvout = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				log.log(Level.WARNING, srvout.getClass().getName(), e);
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
			log.log(Level.WARNING, Test.class.getName() + " in user method", e1);
		}
	}
}
