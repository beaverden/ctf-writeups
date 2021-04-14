#http://www.pythonchallenge.com/pc/return/disproportional.html

import requests
import xmlrpclib

pr = xmlrpclib.ServerProxy("http://www.pythonchallenge.com/pc/phonebook.php")

print pr.phone("Bert")