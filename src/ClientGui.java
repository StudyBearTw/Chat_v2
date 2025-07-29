import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ClientGui extends JFrame {
	private JTextField tfIP;
	private JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	int send_flag;
	Socket s;
	ClientGui() {
		setTitle("Two Talkers: CLIENT");
		setBounds(100, 100, 506, 411);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setBounds(10, 21, 65, 15);
		getContentPane().add(lblNewLabel);
		tfIP = new JTextField();
		tfIP.setText("127.0.0.1");
		tfIP.setBounds(74, 18, 105, 21);
		getContentPane().add(tfIP);
		tfIP.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setBounds(211, 21, 72, 15);
		getContentPane().add(lblNewLabel_1);
		tfPort = new JTextField();
		tfPort.setText("4000");
		tfPort.setBounds(282, 18, 51, 21);
		getContentPane().add(tfPort);
		tfPort.setColumns(10);
		JButton btnConnect = new JButton("Connect");

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					s = new Socket(tfIP.getText(), Integer.parseInt(tfPort
							.getText()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				new Client(ClientGui.this).start();;
			}
		});
		btnConnect.setBounds(369, 17, 87, 23);
		getContentPane().add(btnConnect);
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(369, 47, 87, 23);
		getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);

			}
		});

		JButton btnSent = new JButton("Sent");
		btnSent.setBounds(369, 342, 87, 23);
		getContentPane().add(btnSent);
		btnSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				send_flag = 1;
			}
		});

		taBoard = new JTextArea();
		taBoard.setBounds(22, 71, 446, 247);
		taBoard.setEditable(false);

		getContentPane().add(taBoard);
		tfMessage = new JTextField();
		tfMessage.setBounds(22, 342, 350, 21);
		getContentPane().add(tfMessage);
		tfMessage.setColumns(10);

		tfMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					send_flag = 1;
			}
		});
	}
}
