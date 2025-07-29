import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class Util {
	static String charset = "UTF-16"; //some other Charsets: UTF-8, ASCII, UTF-32 
}

class Client extends Thread {
	ClientGui gui;
	Client(ClientGui gui) {
		super();
		this.gui = gui;
	}

	public void run() {
		OutputStream out = null;
		InputStream in = null;
		try {
			out = gui.s.getOutputStream();
			in = gui.s.getInputStream();

			gui.taBoard.append("Wellcome to the chat, PortNum:4000" + "\n");
			gui.taBoard.append("Connect establish" + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
		int n;
		byte[] buf = new byte[1024];
		while (true) {
			try {
				while (in.available() == 0) {
					if (gui.send_flag == 1) {
						String message = gui.tfMessage.getText();
						gui.taBoard.append("Client: " + message + "\n");
						out.write(message.getBytes( Util.charset));
						gui.send_flag = 0;
						gui.tfMessage.setText("");
					}
				}
				n = in.read(buf);
				String returnedMessage = new String(buf, 0, n,  Util.charset);
				gui.taBoard.append("Server: " + returnedMessage + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}