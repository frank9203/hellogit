#coding=utf-8
import os
import sys
srcfiles = os.listdir('E:\\dir\dir') 
for name in srcfiles:
    a = os.path.splitext(name)
    
    if a[1] != '.apk':
        newname = a[0]+'.apk'
        print name
        print newname
        os.chdir('E:\\dir\dir')
        os.rename(name,newname)
        print ("success")
    else:
        print ('all apk files')

