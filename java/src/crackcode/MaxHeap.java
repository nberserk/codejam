package crackcode;

import codejam.lib.CheckUtil;


public class MaxHeap {
    private final int[] mA = new int[1000];
    private int mSize;

    public int peek() {
        if (mSize == 0) {
            return -1;
        }
        return mA[0];
    }

    public int poll() {
        if (mSize == 0) {
            return -1;
        }

        int ret = mA[0];
        mA[0] = mA[mSize - 1];
        mSize--;
        maxHeapify(0);
        return ret;
    }

    public void add(int n) {
        mA[mSize++] = n;

        int curIdx = mSize - 1;
        while (curIdx > 0) {
            int p = getParentIndex(curIdx);
            if (mA[p] < mA[curIdx]) {
                int temp = mA[p];
                mA[p] = mA[curIdx];
                mA[curIdx] = temp;
                curIdx = p;
            } else {
                break;
            }
        }
    }

    private int getParentIndex(int index) {
        if (index < 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return (i * 2 + 1);
    }

    private int getRightIndex(int i) {
        return i * 2 + 2;
    }

    private void maxHeapify(int idx) {
        int largest = idx;
        int left = getLeftIndex(idx);
        if (left < mSize && mA[left] > mA[largest]) {
            largest = left;
        }

        int right = getRightIndex(idx);
        if (right < mSize && mA[right] > mA[largest])
            largest = right;
        if (largest != idx) {
            int temp = mA[largest];
            mA[largest] = mA[idx];
            mA[idx] = temp;
            maxHeapify(largest);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mSize; i++) {
            sb.append(mA[i]);
            sb.append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MaxHeap h = new MaxHeap();
        h.add(50);
        h.add(100);
        h.add(1000);
        h.add(1);
        h.add(55);
        h.add(101);
        h.add(567);

        System.out.println(h.toString());
        CheckUtil.check(1000, h.poll());
        CheckUtil.check(567, h.peek());
    }

}
