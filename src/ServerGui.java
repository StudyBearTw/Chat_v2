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

public class ServerGui extends JFrame {
    private JTextField tfPort;
    JTextField tfMessage;
    JTextArea taBoard;
    int send_flag;
    Socket s;
    public boolean connected;

    ServerGui() {
        setTitle("Two Talkers: Sever");
        setBounds(100, 100, 506, 411);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel  = new JLabel("Server Port");
        lblNewLabel.setBounds(10, 21, 80, 15);
        getContentPane().add(lblNewLabel);

        tfPort = new JTextField();
        tfPort.setText("4000");
        tfPort.setBounds(74, 18, 105, 21);
        getContentPane().add(tfPort);
        tfPort.setColumns(10);

        JButton btnListen = new JButton("Listen");

        btnListen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new EchoServer(ServerGui.this).start();
            }
        });
        btnListen.setBounds(369, 17, 87, 23);
        getContentPane().add(btnListen);

        JButton btnClose = new JButton("Close");

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                try{
                    s.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        btnClose.setBounds(369, 47, 87, 23);
        getContentPane().add(btnClose);

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

        tfMessage.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    send_flag = 1;
            }
        });

    }

}
