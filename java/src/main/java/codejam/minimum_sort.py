t, n = [int(s) for s in input().split(" ")] # read a list of integers, 2 in this case

def sort2(start, end):
    for i in range(start, end):
        Output("M {} {}".format(i, end))
        minIdx = int(input())
        if(minIdx!=i):
            Output("S {} {}".format(i, minIdx))
            minIndex = int(input())

def Output(line):
    try:
        print(line)
        sys.stdout.flush()
    except:
        # If we let stdout be closed by the end of the program, then an unraisable
        # broken pipe exception will happen, and we won't be able to finish
        # normally.
        try:
            sys.stdout.close()
        except:
            pass

for i in range(1, t + 1):
    sort2(1, n)
    Output("D")
    ret= int(input())

