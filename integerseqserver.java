import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class integerseqserver {
    public static void main( String[] args ) {
		int count = 0;
        try {
            ServerSocket listen = new ServerSocket( 0 );
            System.out.println("Server port is " + listen.getLocalPort() );

            /* handle one client at a time */
            while ( true ) {
                Socket sock = listen.accept();

                /* data from client */
                Scanner rd = new Scanner(sock.getInputStream());

                /* data to client */
                PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);

                System.out.println("Accepted connection from "
                     + sock.getInetAddress() + " at port " 
                     + sock.getPort() );

                while ( rd.hasNextLine() ) {
                    String line = rd.nextLine();
                    int num = Integer.parseInt(line);
					if (num == -1){ break;}
                    if (num != -1){
						if ((num % 13 == 0) | (num % 31 == 0)){
							count += 1;
							System.out.println(num);
						}
					}
                }
				pw.println(count);
                System.out.println( "closing" + sock );
                sock.close(); // clean up required
            }
        }
        catch( IOException e ) {
            System.out.println("error: " + e );
        }
    }
}