
def solve(cache, n):
    if n<3:
        return 0
    if n==3:
        return 1
    if cache[n] != -1:
        return cache[n]
    ret = 0
    for i in range(2, int(n/2)):
        if n%i ==0:
            if i>=3 or n/i >=3:
                ret = max(ret, 1+solve(cache, n-i))

    cache[n] = ret
    return ret


def main():
    T = int(input())
    max_n = 1000
    cache = [-1]*(max_n+1)
    for base in range(3, max_n+1):
        cur=base
        count = 1
        while cur<=max_n:
            if cache[cur] == -1:
                cache[cur]=count
            cur=cur + cur*2
            count+=1

    for i in range(T):
        N = int(input())
        Output("Case #{}: {}".format(i, cache[N]))

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


if __name__ == "__main__":
    main()
