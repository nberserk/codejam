
from typing import List

class Part:
    def __init__(self, v: int, start:int, end:int) -> None:
        self.v = v
        self.start=start
        self.end=end
        self.si=0    
    def __str__(self) -> str:
        return f"{self.v} {self.start} {self.end}"
    def __repr__(self) -> str:
        return str(self)

def split(a: List):
    part:List = []
    i=1
    si = 0
    while(i<N):
        if a[si]!=a[i]:            
            part.append( Part(a[si], si, i-1))
            si=i
        i+=1
    part.append( Part(a[si], si, i-1))
    return part

def solve():
    destPart = split(dest)
    srcPart = split(src)
    #print(srcPart, destPart)

    di=0    
    for i in range(len(srcPart)):
        if destPart[di].v == srcPart[i].v:
            destPart[di].si = i
            di+=1
            if di==len(destPart):
                break

    if di ==len(destPart):
        print("YES")
    else:
        print("NO")
        return

    swipe=[]
    # swipe left
    for i in range(len(destPart)):        
        (ds,de,si) = (destPart[i].start, destPart[i].end, destPart[i].si)
        (ss,se) = (srcPart[si].start, srcPart[si].end)
        if ss<=ds and de<=se:
            # no need to swipe
            continue
        if ds<se:
            swipe.append(f"L {ds} {se}")
    # swipe right
    for i in range( len(destPart)-1, -1, -1):
        (ds,de,si) = (destPart[i].start, destPart[i].end, destPart[i].si)
        (ss,se) = (srcPart[si].start, srcPart[si].end)
        if ss<=ds and de<=se:
            # no need to swipe
            continue
        if se<de:
            swipe.append(f"R {se} {de}")

    print(f"{len(swipe)}")
    for i in swipe:
        print(i)    
    
#for i in range(5):
N = int(input())
src = [int(i) for i in input().split()]
dest = [int(i) for i in input().split()]
#print(src, dest)
solve()





