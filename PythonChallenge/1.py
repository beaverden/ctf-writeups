s = "g fmnc wms bgblr rpylqjyrc gr zw fylb. rfyrq ufyr amknsrcpq ypc dmp. bmgle gr gl zw fylb gq glcddgagclr ylb rfyr'q ufw rfgq rcvr gq qm jmle. sqgle qrpgle.kyicrpylq() gq pcamkkclbcb. lmu ynnjw ml rfc spj map."
from string import maketrans

intab = "abcdefghijklmnopqrstuvwxyz"
outtab = "cdefghijklmnopqrstuvwxyzab"

open("wr", "w").write(s.translate(maketrans(intab, outtab)))