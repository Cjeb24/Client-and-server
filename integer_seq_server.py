import socket
import sys
# with closes the socket automatically
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind( ('127.0.0.1', 3001) )
    s.listen()

    while True:
        conn, addr = s.accept()
        print('connection open', addr)
        print('peer is', conn.getpeername() )
        f = conn.makefile('rw')
        count = 0
        with conn :
            while True:
                l = f.readline()
                if not l: break
                num = int(l.rstrip("\n"))
                if num != -1:
                    if (num % 13 == 0) or (num % 31 == 0):
                        print( l )
                        count +=1
                else:
                    f.write(str(count) + "\n")
                    f.flush()
                    break
                f.flush()
        print('connection closed')
 
