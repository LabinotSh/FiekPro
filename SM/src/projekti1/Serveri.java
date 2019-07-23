package projekti1;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/*
 * The server that can be run both as a console application or a GUI
 */
public class Serveri {
	// a unique ID for each connection
	private static int uniqueId;
	// an ArrayList to keep the list of the Client
	private ArrayList<ClientThread> al;
	// if I am in a GUI
	private ServeriGUI sg;
	// to display time
	private SimpleDateFormat sdf;
	// the port number to listen for connection
	private int port;
	// the boolean that will be turned of to stop the server
	private boolean keepGoing;
	//Mixer eshte nje audio device qe i ka disa lines, zakonishte si line hyrese jane sourceDataLines, si lines dalese jane TargetDataLine
	static Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();//array i mixer info object qe paraqet bashesine e audio mixers qe jane te instaluar ne sistem
	static DataInputStream din;//stream qe i pranon te dhenat 
	static DataOutputStream dout;//stream per me i dergu te dhenat
	static ObjectInputStream oinA;//input stream per objekte
	static ServerSocket MyService;
	static Socket clientSocket = null;
	static InputStream input;
	static TargetDataLine targetDataLine;
	static OutputStream out;
	static AudioFormat audioFormat;
	static SourceDataLine sourceDataLine;// data line ne te cilen te dhenat mund te shkruhen.E ka rolin e source te mixeri.
	//ketu shkruhen audio bytes qe barten te mixer
	byte tempBuffer[] = new byte[10000];
	
	static int audioLowerBound = 10000;
	static int audioUpperBound = 10500;
	static int usedAudioPorts[] = new int[500];
	

	/*
	 *  server constructor that receive the port to listen to for connection as parameter
	 *  in console
	 */
	public Serveri(int port) {
		this(port, null);
	}
	
	public Serveri(int port, ServeriGUI sg) {
		// GUI or not
		this.sg = sg;
		// the port
		this.port = port;
		// to display hh:mm:ss
		sdf = new SimpleDateFormat("HH:mm:ss");
		// ArrayList for the Client list
		//listen e klientave
		al = new ArrayList<ClientThread>();
	}
	
	public void start() {
		keepGoing = true;
		/* create socket server and wait for connection requests */
		try 
		{
			// the socket used by the server
			ServerSocket serverSocket = new ServerSocket(port);

			// infinite loop to wait for connections
			while(keepGoing) 
			{
				// format message saying we are waiting
				display("Server waiting for Clients on port " + port + ".");
				
				Socket socket = serverSocket.accept();  	// accept connection
				// if I was asked to stop
				if(!keepGoing)
					break;
				ClientThread t = new ClientThread(socket);  // make a thread of it
				al.add(t);									// save it in the ArrayList
				t.start();
			}
			// I was asked to stop
			try {
				serverSocket.close();
				for(int i = 0; i < al.size(); ++i) {
					ClientThread tc = al.get(i);
					try {
					tc.sInput.close();
					tc.sOutput.close();
					tc.socket.close();
					}
					catch(IOException ioE) {
						// not much I can do
					}
				}
			}
			catch(Exception e) {
				display("Exception closing the server and clients: " + e);
			}
		}
		// something went bad
		catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}		
    /*
     * For the GUI to stop the server
     */
	protected void stop() {
		keepGoing = false;
		// connect to myself as Client to exit statement 
		// Socket socket = serverSocket.accept();
		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {
			// nothing I can really do
		}
	}
	/*
	 * Display an event (not a message) to the console or the GUI
	 */
	private void display(String msg) {
		String time = sdf.format(new Date()) + " " + msg;
		if(sg == null)
			System.out.println(time);
		else
			sg.appendEvent(time + "\n");
	}
	/*
	 *  to broadcast a message to all Clients
	 */
	private synchronized void broadcast(String message) {
		// add HH:mm:ss and \n to the message
		String time = sdf.format(new Date());
		String messageLf = time + " " + message + "\n";
		// display message on console or GUI
		if(sg == null)
			System.out.print(messageLf);
		else
			sg.appendRoom(messageLf);     // append in the room window
		
		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		for(int i = al.size(); --i >= 0;) {
			ClientThread ct = al.get(i);
			// try to write to the Client if it fails remove it from the list
			if(!ct.writeMsg(messageLf)) {
				al.remove(i);
				display("Disconnected Client " + ct.username + " removed from list.");
			}
		}
	}

	// for a client who logoff using the LOGOUT message
	synchronized void remove(int id) {
		// scan the array list until we found the Id
		for(int i = 0; i < al.size(); ++i) {
			ClientThread ct = al.get(i);
			// found it
			if(ct.id == id) {
				al.remove(i);
				return;
			}
		}
	}
	
	/*
	 *  To run as a console application just open a console window and: 
	 * > java Server
	 * > java Server portNumber
	 * If the port number is not specified 1500 is used
	 */ 
	
	private static AudioFormat getAudioFormat() {
	    float sampleRate = 80000.0F;//sa mostra te zerit merren per 1 sekond per kanal
	    int sampleSizeInBits = 8;//sa bita perdoren per me rujt secilin snapshot (moster),zakonisht 8,16
	    int channels = 1;//1-mono,2-stero
	    boolean signed = true;
	    boolean bigEndian = false;
	    return new AudioFormat(
	            sampleRate,
	            sampleSizeInBits,
	            channels,
	            signed,
	            bigEndian);
	}
	
	private static int audioPort() {
		Random r = new Random();
		
		int randomPort = r.nextInt(audioUpperBound-audioLowerBound) + audioLowerBound;
		System.out.println("Random audio port chosen: " + randomPort);
//		boolean searching = true;
//		while(searching) {
//			for(int i = 0; i < usedAudioPorts.length; i++) {
//				if(usedAudioPorts[i] == randomPort) {
//					searching = false;
//					break;
//				}
//			}
//		}
		
		return randomPort;
	}
	
	private static void captureAudio() {
	    try {

	        audioFormat = getAudioFormat();
	        DataLine.Info dataLineInfo = new DataLine.Info(
	                TargetDataLine.class, audioFormat);
	        Mixer mixer = null;
	        System.out.println("Available mixers:");
	        for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
	            mixer = AudioSystem.getMixer(mixerInfo[3]);
	            if (mixer.isLineSupported(dataLineInfo)) {
	                System.out.println(mixerInfo[cnt].getName());
	                targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
	            }
	        }
	        targetDataLine.open(audioFormat);
	        targetDataLine.start();

	        Thread captureThread = new CaptureThread();
	        captureThread.start();
	       
	    } catch (Exception e) {
	        System.out.println(e);
	        System.exit(0);
	        
	    }
	}
	
	
	public static class CaptureThread extends Thread {

	    byte tempBuffer[] = new byte[1024];

	    @Override
	    public void run() {
	        try {
	            while (true) {
	                int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
	                out.write(tempBuffer);
	                out.flush();

	            }

	        } catch (Exception e) {
	            System.out.println(e);
	            System.exit(0);
	        }
	    }
	}
	public static void main(String[] args) {
		// start server on port 1500 unless a PortNumber is specified 
		int portNumber = 1500;
		switch(args.length) {
			case 1:
				try {
					portNumber = Integer.parseInt(args[0]);
				}
				catch(Exception e) {
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java Server [portNumber]");
					return;
				}
			case 0:
				break;
			default:
				System.out.println("Usage is: > java Server [portNumber]");
				return;
				
		}
		// create a server object and start it
		Serveri server = new Serveri(portNumber);
		server.start();
	}

	/** One instance of this thread will run for each client */
	class ClientThread extends Thread {
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		// my unique id (easier for deconnection)
		int id;
		// the Username of the Client
		String username;
		// the only type of message a will receive
		Chat cm;
		// the date I connect
		String date;
		
		int audioPort;

		// Constructore
		ClientThread(Socket socket) {
			// a unique id
			id = ++uniqueId;
			this.socket = socket;
			/* Creating both Data Stream */
			System.out.println("Thread trying to create Object Input/Output Streams");
			try
			{
				// create output first
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				
				int AudioPort = audioPort();
				
				this.audioPort = AudioPort;
				
				sOutput.writeObject("" + AudioPort);
				
				sInput  = new ObjectInputStream(socket.getInputStream());
				// read the username
				username = (String) sInput.readObject();
				display(username + " just connected.");
			}
			catch (IOException e) {
				display("Exception creating new Input/output Streams: " + e);
				return;
			}
			// have to catch ClassNotFoundException
			// but I read a String, I am sure it will work
			catch (ClassNotFoundException e) {
			}
            date = new Date().toString() + "\n";
		}

		// what will run forever
		public void run() {
			// to loop until LOGOUT
			//boolean keepGoing = true;
			
			Thread AudioStart=new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
				        Mixer mixer = AudioSystem.getMixer(mixerInfo[0]);//i marrim informata per mixerInfo[0]
				        System.out.println(mixerInfo[0]);//ne rastin tem sistemi i ka 6 mixers i pari[0] eshte Primary Sound Driver, version Unknown Version
				        audioFormat = getAudioFormat();//per me i reprezentu bitat ne te dhena te zerit
				        
				        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
				        // what kind of data the data line expects to receive for output. 
				        //interface SourceDataLine supporting format PCM_SIGNED 80000.0 Hz, 16 bit, mono, 2 bytes/frame, little-endian
				        //Pulse-code modulation (PCM) is a method used to digitally represent sampled analog signals. It is the standard form of digital audio in computers,
				        
				        //sourceDataLine eshet nje varg qe permbane te dhena te audio formatit
				        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
				        sourceDataLine.open(audioFormat);
				        sourceDataLine.start();
				        MyService = new ServerSocket(audioPort);
				        clientSocket = MyService.accept();
				        
				        captureAudio();
				        input = new BufferedInputStream(clientSocket.getInputStream());
				        out = new BufferedOutputStream(clientSocket.getOutputStream());
				        System.out.print(tempBuffer);
				        int tempb = input.read(tempBuffer);
				        while (tempb != -1 && keepGoing) {
				        	tempb = input.read(tempBuffer);
				        	System.out.println("");
				            sourceDataLine.write(tempBuffer, 0, 10000);

				        } 

				    } catch (IOException e) {
				        e.printStackTrace();
				    } catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			
			AudioStart.start();
			
			while(keepGoing) {
				// read a String (which is an object)
				try {
					cm = (Chat) sInput.readObject();
				}
				catch (IOException e) {
					display(username + " Exception reading Streams: " + e);
					break;				
				}
				catch(ClassNotFoundException e2) {
					break;
				}
				// the messaage part of the ChatMessage
				String message = cm.getMessage();

				// Switch on the type of message receive
				switch(cm.getType()) {

				case Chat.MESSAGE:
					broadcast(username + ": " + message);
					break;
				case Chat.LOGOUT:
					display(username + " disconnected with a LOGOUT message.");
					
					keepGoing = false;
					break;
				case Chat.WHOISIN:
					writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
					// scan al the users connected
					for(int i = 0; i < al.size(); ++i) {
						ClientThread ct = al.get(i);
						writeMsg((i+1) + ") " + ct.username + " since " + ct.date);
					}
					break;
				}
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}
		
		// try to close everything
		private void close() {
			// try to close the connection
			try {
				if(sOutput != null) sOutput.close();
			}
			catch(Exception e) {}
			try {
				if(sInput != null) sInput.close();
			}
			catch(Exception e) {};
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		}

		/*
		 * Write a String to the Client output stream
		 */
		private boolean writeMsg(String msg) {
			// if Client is still connected send the message to it
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				sOutput.writeObject(msg);
			}
			// if an error occurs, do not abort just inform the user
			catch(IOException e) {
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}
}

