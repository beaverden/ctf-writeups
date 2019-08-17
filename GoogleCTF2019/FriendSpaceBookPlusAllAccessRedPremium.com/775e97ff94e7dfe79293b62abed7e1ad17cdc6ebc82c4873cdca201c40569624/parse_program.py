import codecs

with codecs.open('program', 'rb', encoding='utf-8') as f:
    data = f.read()
    print(len(data))
    with codecs.open('res.bin', 'w', encoding='utf-8') as g:
        g.write(unicode(data))