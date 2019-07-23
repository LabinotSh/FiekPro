package projekti1;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/*
 * The Client that can be run both as a console or a GUI
 */
public class Klienti  {

	// for I/O
	private static ObjectInputStream sInput;		// to read from the socket
	private ObjectOutputStream sOutput;		// to write on the socket
	//soketa - pike lidhjeje ne mes dy komunikimeve
	private Socket socket;
	//klasa qe na mundeson qe na me dergu ose me pranu te dhena pa pase nevoje qe me thirre cdohere sistemin per secilin bit qe e shkruajme
	static BufferedOutputStream out = null;
	static BufferedInputStream in = null;
	static ByteArrayOutputStream byteArrayOutputStream;
	static AudioFormat audioFormat;
	static TargetDataLine targetDataLine;
	static AudioInputStream audioInputStream;
	static SourceDataLine sourceDataLine;
	// if I use a GUI or not
	private KlientGUI cg;
	
	// the server, the port and the username
	private String server, username;
	private int port;
	
	private int audioPort;

	/*
	 *  Constructor called by console mode
	 *  server: the server address
	 *  port: the port number
	 *  username: the username
	 */
	Klienti(String server, int port, String username) {
		// which calls the common constructor with the GUI set to null
		this(server, port, username, null);
	}

	/*
	 * Constructor call when used from a GUI
	 * in console mode the ClienGUI parameter is null
	 */
	Klienti(String server, int port, String username, KlientGUI cg) {
		this.server = server;
		this.port = port;
		this.username = username;
		// save if we are in GUI mode or not
		this.cg = cg;
	}
	
	/*
	 * Here goes the call function
	 */
	Thread Audio = new Thread(new Runnable() {
		
		@Override
		public void run() {
			//try per me dheze audion
		try {
	        socket = new Socket(server, audioPort);
	        out = new BufferedOutputStream(socket.getOutputStream());
	        in = new BufferedInputStream(socket.getInputStream());
	        //mixer - klase qe reprezenton informacione per audio mikserin si emri i produktit, shitesi, versioni edhe nje description ne menyre tekstuale
	        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
	        System.out.println("Available mixers:");
	        for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
	            System.out.println(mixerInfo[cnt].getName());
	        }
	        //the audio format specifies the kind of the data that can be read from the line - 
	        //pra audio format na tregon se cfare formati jane marre te dhenat
	        audioFormat = getAudioFormat();
	        
	        //perfshine te dhena qe perbajne informata si :
	        //the audio formats supported by the data line
	        //the minimum and maximum sizes of its internal buffer
	        DataLine.Info dataLineInfo = new DataLine.Info(
	                TargetDataLine.class, audioFormat);

	        Mixer mixer = AudioSystem.getMixer(mixerInfo[2]);

	        targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);

	        targetDataLine.open(audioFormat);
	        targetDataLine.start();

	        Thread captureThread = new CaptureThread();

	        captureThread.start();

	        DataLine.Info dataLineInfo1 = new DataLine.Info(
	                SourceDataLine.class, audioFormat);
	        sourceDataLine = (SourceDataLine) AudioSystem
	                .getLine(dataLineInfo1);
	        
	        
	        
	        sourceDataLine.open(audioFormat);
	        sourceDataLine.start();

	        Thread playThread = new PlayThread();
	        playThread.start();

	    } catch (Exception e) {
	        System.out.println(e);
	        System.exit(0);
	    }
	}});
	
	/*
	 * To start the dialog
	 */
	public boolean start() {
		// try to connect to the server
		try {
			socket = new Socket(server, port);
		} 
		// if it failed not much I can so
		catch(Exception ec) {
			display("Error connectiong to server:" + ec);
			return false;
		}
		//getInetAddress e merr ipat e remote machine
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		display(msg);
	
		/* Creating both Data Stream */
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}
		
		String sAudioPort = "0";
		System.out.println("Waiting for port number...");
		
		try {
			sAudioPort = (String)sInput.readObject();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		audioPort = Integer.parseInt(sAudioPort);
		System.out.println("Audio port: " + audioPort);
		
		// creates the Thread to listen from the server 
		new ListenFromServer().start();
		// Send our username to the server this is the only message that we
		// will send as a String. All other messages will be ChatMessage objects
		try
		{
			sOutput.writeObject(username);
		}
		catch (IOException eIO) {
			display("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true;
	}

	/*
	 * To send a message to the console or the GUI
	 */
	private void display(String msg) {
		if(cg == null)
			System.out.println(msg);      // println in console mode
		else
			cg.append(msg + "\n");		// append to the ClientGUI JTextArea (or whatever)
	}
	
	/*
	 * To send a message to the server
	 */
	void sendMessage(Chat msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
			display("Exception writing to server: " + e);
		}
	}
	//Play thread
	
	public static class PlayThread extends Thread {
		//buffer eshte si nje lloj me morie e perkohshme qe i ruan te dhenat perkohessiht kur ka hsume te dhena
	    byte tempBuffer[] = new byte[10000];
//***************************duhet m ekshyre qetu
	    @Override
	    public void run() {
	    	System.out.println("PlayThread...");
	        try {
	            while (in.read(tempBuffer) != -1) {
	            	System.out.println("Temp buffer: ");
	            	System.out.println(tempBuffer);
	                sourceDataLine.write(tempBuffer, 0, 10000);
	            }
	            sourceDataLine.drain();
	            sourceDataLine.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  }
	
	
	private static AudioFormat getAudioFormat() {
	    float sampleRate = 80000.0F;

	    int sampleSizeInBits = 8;

	    int channels = 1;

	    boolean signed = true;

	    boolean bigEndian = false;

	    return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,
	            bigEndian);
	}
	public static class CaptureThread extends Thread {

	    byte tempBuffer[] = new byte[10000];

	    @Override
	    public void run() {
	        byteArrayOutputStream = new ByteArrayOutputStream();
	        boolean stopCapture = false;
	        try {
	            while (!stopCapture) {
	            	System.out.println("0 TDL: " + targetDataLine);
	                int cnt = targetDataLine.read(tempBuffer, 0,
	                        tempBuffer.length);

	                out.write(tempBuffer);

	                if (cnt > 0) {

	                    byteArrayOutputStream.write(tempBuffer, 0, cnt);

	                }
	            }
	            byteArrayOutputStream.close();
	        } catch (IOException e) {
	            System.out.println(e);
	            System.exit(0);
	        }
	    }
	}
	/*
	 * When something goes wrong
	 * Close the Input/Output streams and disconnect not much to do in the catch clause
	 */
	private void disconnect() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {} // not much else I can do
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {} // not much else I can do
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // not much else I can do
		
		// inform the GUI
		if(cg != null)
			cg.connectionFailed();
			
	}
	/*
	 * To start the Client in console mode use one of the following command
	 * > java Client
	 * > java Client username
	 * > java Client username portNumber
	 * > java Client username portNumber serverAddress
	 * at the console prompt
	 * If the portNumber is not specified 1500 is used
	 * If the serverAddress is not specified "localHost" is used
	 * If the username is not specified "Anonymous" is used
	 * > java Client 
	 * is equivalent to
	 * > java Client Anonymous 1500 localhost 
	 * are eqquivalent
	 * 
	 * In console mode, if an error occurs the program simply stops
	 * when a GUI id used, the GUI is informed of the disconnection
	 */
	public static void main(String[] args) {
		// default values
		int portNumber = 1500;
		String serverAddress = "localhost";
		String userName = "Anonymous";

		// depending of the number of arguments provided we fall through
		switch(args.length) {
			// > javac Client username portNumber serverAddr
			case 3:
				serverAddress = args[2];
			// > javac Client username portNumber
			case 2:
				try {
					portNumber = Integer.parseInt(args[1]);
				}
				catch(Exception e) {
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
					return;
				}
			// > javac Client username
			case 1: 
				userName = args[0];
			// > java Client
			case 0:
				break;
			// invalid number of arguments
			default:
				System.out.println("Usage is: > java Client [username] [portNumber] {serverAddress]");
			return;
		}
		// create the Client object
		Klienti client = new Klienti(serverAddress, portNumber, userName);
		// test if we can start the connection to the Server
		// if it failed nothing we can do
		if(!client.start())
			return;
		
		// wait for messages from user
		//scanner eshte nje klase qe perdoret per me marre te dhena te tipeve primitive
		Scanner scan = new Scanner(System.in);
		// loop forever for message from the user
		while(true) {
			System.out.print("> ");
			// read message from user
			String msg = scan.nextLine();
			// logout if message is LOGOUT
			if(msg.equalsIgnoreCase("LOGOUT")) {
				client.sendMessage(new Chat(Chat.LOGOUT, ""));
				// break to do the disconnect
				break;
			}
			// message WhoIsIn
			else if(msg.equalsIgnoreCase("WHOISIN")) {
				client.sendMessage(new Chat(Chat.WHOISIN, ""));				
			}
			else {				// default to ordinary message
				client.sendMessage(new Chat(Chat.MESSAGE, msg));
			}
		}
		// done disconnect
		client.disconnect();	
	}

	/*
	 * a class that waits for the message from the server and append them to the JTextArea
	 * if we have a GUI or simply System.out.println() it in console mode
	 */
	class ListenFromServer extends Thread {

		public void run() {			
			while(true) {
				try {
					String msg = (String) sInput.readObject();
					// if console mode print the message and add back the prompt
					if(cg == null) {
						System.out.println(msg);
						System.out.print("> ");
					}
					else {
						cg.append(msg);
					}
				}
				catch(IOException e) {
					display("Server has close the connection: " + e);
					if(cg != null) 
						cg.connectionFailed();
					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}
}
