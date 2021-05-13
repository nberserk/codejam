package leetcode;

public class LC702 {
    static interface ArrayReader {
        int get(int idx);
    }

    public int search(ArrayReader reader, int target) {
        int lo = 0;
        int hi = 100000;
        int max = 2147483647;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (reader.get(lo) == max && reader.get(hi) == max)
                return -1;
            if (reader.get(mid) == target)
                return mid;
            else if (reader.get(mid) > target)
                hi = mid - 1;
            else lo = mid + 1;
        }
        if (reader.get(lo) == target)
            return lo;
        return -1;
    }


}
