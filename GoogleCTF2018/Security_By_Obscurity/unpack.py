import zipfile
import shutil
import os

cnt = 3
current_path = 'archive{}.zip'.format(cnt)
while True:
    try:
        zip_ref = zipfile.ZipFile(current_path, 'r')
        zip_ref.extractall('.')
        file = zip_ref.namelist()[0]
        print file
        zip_ref.close()
        cnt += 1
        current_path = 'archive{}.zip'.format(cnt)
        shutil.move(file, current_path)
    except:
        break 

print current_path, cnt

shutil.move(current_path, current_path.replace('zip', '7z'))
current_path = current_path.replace('zip', '7z')

while True:
    try:
        os.system('"C:\\Program Files\\7-Zip\\7z.exe" x {}'.format(current_path))
        file = 'archive{}'.format(cnt)
        print 'file = ', file
        cnt += 1
        current_path = 'archive{}.7z'.format(cnt)
        print 'current = ', current_path
        shutil.move(file, current_path)
    except Exception, e:
        print repr(e)
        break 
