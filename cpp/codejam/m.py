#!/usr/bin/python

import os, sys
import subprocess

def main():
    binName = sys.argv[1]
    if binName == None:
        print 'name missing'
        return    
    if '-' in binName:
         name = binName.split('-')[0]
    else:
        name = binName    
    
    args = "gcc %s -o bin/%s" % (name+".cpp", binName)
    subprocess.check_call(args.split(" "))
    os.system("time(bin/%s)" % ( binName))

if __name__ == "__main__":
    main()

