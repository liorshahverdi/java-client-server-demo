/**
 * TrimClient
 * Reads strings and returns similar string with whitespace omitted. 
 * 
 * Invoke as: java TrimClient [-port=<number>] [server]
 * Default port is 1200, if not specified 
 * Default host is localhost, if not specified
 * 
 * @author (Lior Shahverdi) 
 * @version (Version 1.1 12/02/2014)
 */
import java.io.*; 
import java.net.*; 
public class TrimClient
{
    public static void main (String [] args)
    {
        Socket s; //Client socket for connecting to the server 
        String server = "localhost"; //Use localhost by default
        int serverPort = 1200; //Use port 1200 by default
        InetAddress local, remote; //Local and remote addresses
        int localPort, remotePort; //Local and remote ports 
        
        //For input and output
        BufferedReader socketIn; //Socket input
        PrintWriter socketOut; //Socket output
        BufferedReader keyboardIn; //Keyboard input
        String userInput, serverResult; 
        
        //Process command line arguments
        //Error if more than two command line arguments
        if (args.length > 2) 
        {
            System.out.println("Usage: java TrimClient "
                               + "[-port=<number>] [server]");
            System.exit(-1); 
        }
        //If two arguments, only one should start with '-' 
        if (args.length == 2) 
        {
            if ((args[0].charAt(0) == '-' && 
                 args[1].charAt(0) == '-' ) ||
                (args[0].charAt(0) != '-' && 
                 args[1].charAt(0) != '-' ))
		{
                    System.out.println("Usage: "
                                   + "java TrimClient "
                                   + "[-port=<number>] [server]");
                    System.exit(-1);
                }
        }    
        //Set the server and server port suitably 
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].charAt(0) != '-') 
                server = args[i]; 
            if (args[i].startsWith("-port="))
                serverPort = Integer.parseInt(args[i].substring(6)); 

        }  
        //Set up keyboard input and print greeting 
        keyboardIn = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Hello, you have invoked a Trim Client"); 
        System.out.println("You have requested a connection to " + 
                           server + " at port " + serverPort); 
        System.out.println(); //Blank line
        //Establish socket connection and communicate
        try 
        {
            //Establish connection 
            s = new Socket(server, serverPort); 
            //Report connection status 
            local = s.getLocalAddress(); remote = s.getInetAddress(); 
            localPort = s.getLocalPort(); remotePort = s.getPort(); 
            System.out.println ("Client side"); 
            System.out.println ("   " + local.getHostName() 
                                + "[" + local.getHostAddress() + "]" 
                                + " at port " + localPort);
            System.out.println ("Server side"); 
            System.out.println ("   " + remote.getHostName()
                                + "[" + remote.getHostAddress() + "]" 
                                + " at port " + remotePort); 
            System.out.println(); //Blank line
            //TCP provides a full-duplex byte stream service
            //For UNICODE and line support, we need BufferedReader 
            //and PrintWriter objects
            socketIn = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            socketOut = new PrintWriter(s.getOutputStream(), true);
            //Interact with user
            System.out.print("Enter a string (or bye to quit): "); 
            userInput = keyboardIn.readLine(); 
            //Loop until user indicates termination 
            while (!userInput.equalsIgnoreCase("bye"))
            {
                //Send user input to server
                socketOut.println(userInput); 
                //Get server response 
                serverResult = socketIn.readLine(); 
                //Report server response 
                System.out.println(serverResult); 
                System.out.println(); //Blank line
                //Get new string
                System.out.print("Enter a string (or bye to quit): "); 
                userInput = keyboardIn.readLine(); 
            }
            //Signal the server to quit 
            socketOut.println(userInput); 
            //Close all streams and socket 
            keyboardIn.close();
            socketIn.close(); 
            socketOut.close(); 
            s.close(); 
        }
        catch (Exception e)
        {
            e.printStackTrace(); 
        }
    }    
}
