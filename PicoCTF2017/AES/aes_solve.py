from hashlib import md5
from base64 import b64decode
from base64 import b64encode
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
backend = default_backend()
key = b64decode('T5uVzYtuBNv6vwjohslV4w==')
data = b64decode('t1h0qbcOhRQF5E46bsNLimfbcI6egrKP4LHtKR3lT4UdWjhssM8RQSBT7S/8rcRy')

cipher = Cipher(algorithms.AES( ), modes.ECB(), backend=backend)
dec = cipher.decryptor()
ct = dec.update(data) + dec.finalize()
print ct