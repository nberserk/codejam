import copy
from typing import List, Set
# state: [(r,c)], cur

MAX_N = 200000

def encode(r,c):
    return r*MAX_N+c
def decode(encoded):
    return (encoded/MAX_N, encoded%MAX_N)


def evalCandidate2(result: List, v: Set, r,c, cur:List):
    if (r,c) in v:
        return
    cur[1] += m[r][c]
    cur[0].add(encode(r,c))
    v.add((r,c))
    if len(cur[0])*average == cur[1] and len(cur[0])!=R*C:
        result.append(copy.deepcopy(cur[0]))
        print('candidate', cur[0])
    
    for (dx,dy) in [(-1,0), (1,0), (0, -1), (0, 1)]:
        nr = r+dx
        nc = c+dy
        if nr<0 or nc<0 or nr>=R or nc>=C:
            continue
        evalCandidate(result, v, nr, nc, cur)


def evalCandidate(result: List, v: Set, r,c, cur:List):
    if len(cur[0])*average == cur[1] and len(cur[0])!=R*C:
        result.append(copy.deepcopy(cur[0]))
        print('candidate', cur[0])
    
    for (dx,dy) in [(-1,0), (1,0), (0, -1), (0, 1)]:
        nr = r+dx
        nc = c+dy
        if nr<0 or nc<0 or nr>=R or nc>=C or (nr,nc) in v:
            continue
        cur[1] += m[r][c]
        cur[0].add(encode(r,c))
        evalCandidate(result, v, nr, nc, cur)
        cur[1] -=m[r][c]
        cur[0].pop()


def candidate():
    fragment:Set=set()
    for r in range(R):
        for c in range(C):
            visited=set()
            candi = []
            visited.add((r,c))
            cur = set()
            cur.add(encode(r,c))
            evalCandidate(candi, visited, r,c, [cur, m[r][c]])
            # for c in candi:
            #     fragment.add(c)
    print(fragment)





def solve(m, average):
    visited={}

    maxSplitCount=1

    candidate()



    return maxSplitCount
    

for i in range(2):
    C = int(input())
    R = 2
    m=[]
    sum=0
    # l1:List = [0, 1, 2]
    # l2:List = [2,0,1]
    # if l1 == l2:
    #     print('oops')
    for i in range(2):
        row = [int(i) for i in input().split()]
        for j in range(len(row)):
            sum=sum+row[j]
        m.append(row)
    average = (int)(sum/(R*C))
    #print(m, average)
    r = solve(m, average)

    print(r)





