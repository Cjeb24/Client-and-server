import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/*
 * The client program can send text lines to a server program.
 */
class integerseqclient {
    public static void main( String[] args ) {
        if ( args.length != 3 ) {
            System.out.println("usage: java LineClient host port");
            System.exit(1);
        }
        int port = 0;
        String host = null;
        try {
            host = args[0];
            port = Integer.parseInt( args[1] );
        }
        catch( NumberFormatException e ) {
            System.out.println("bad port number");
            System.exit(1);
        }

        try {
            /* determine the address of the server and connect to it */
            InetAddress server = InetAddress.getByName( host );
            Socket sock = new Socket( server, port );
            System.out.println( "connected: " + sock );
            /* get the input stream */
            InputStream in = sock.getInputStream();
            /* get the output stream */
            OutputStream out = sock.getOutputStream();
            /* attach it to a print writer */
            PrintWriter wr = new PrintWriter( out, true );
            Scanner sc = new Scanner( in );
            /* get an input reader */
            //Scanner rd = new Scanner(System.in);
			int number = Integer.parseInt(args[2]);

            for(int i = 1;i<=number;i++) {
                String line = Integer.toString(i);
                wr.println( i );
                wr.flush(); // ask it to be sent
            }
			wr.println(Integer.toString(-1));
            System.out.println( "count:" + (sc.nextLine()) );
            /* terminate the connection */
            sock.close();
        }
        catch( UnknownHostException e ) {
            System.out.println("bad host name");
            System.exit(0);
        }
        catch( IOException e ) {
            System.out.println("io error:" + e);
            System.exit(0);
        }
    }
}
