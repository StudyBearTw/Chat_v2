import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class EchoServer extends Thread {

	ServerSocket svs;
	Socket s;
	static ServerGui SGui;

	EchoServer(ServerGui gui){
		super();
		this.SGui = gui;
	};
	

	public void run() {
		byte[] buf = new byte[1024];
		int n = 0;
		ServerSocket svs = null;
		Socket s = null;
		try {

			svs = new ServerSocket(4000);
			SGui.taBoard.append("Wellcome to the chat, PortNum:4000" + "\n");
			SGui.taBoard.append("Wait to connect . . ." + "\n");
			s = svs.accept();
			SGui.connected = true;
			SGui.taBoard.append("Connection Established!" + "\n");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			while (true) {
				try{
					while (in.available() == 0) {
						if (SGui.send_flag == 1) {
							String message = SGui.tfMessage.getText();
							SGui.taBoard.append("Server: " + message + "\n");
							out.write(message.getBytes( Util.charset));
							SGui.send_flag = 0;
							SGui.tfMessage.setText("");
						}
					}
					n = in.read(buf);
					String returnedMessage = new String(buf, 0, n,  Util.charset);
					SGui.taBoard.append("Client: " + returnedMessage + "\n");
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			svs.close();
		    s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
