k = open("wr", "r").read()
c = list(filter(lambda x: k.count(x) == 1, k))
print c
