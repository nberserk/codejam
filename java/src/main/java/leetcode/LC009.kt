package leetcode

import org.junit.Assert
import org.junit.Test

class LC009 {
    fun reverse(x: Int): Int {
        val minus = x<0
        var org = Math.abs(x).toLong()

        var r:Long=0
        while(org>0){
            val v = org%10
            r=r*10+v
            org/=10
        }
        r = if(minus) -r else r
        if(r>Int.MAX_VALUE || r<Int.MIN_VALUE) {
            return 0
        }
        return r.toInt()
    }

    @Test
    fun test() {
        Assert.assertEquals(321, reverse(123))
        Assert.assertEquals(0, reverse(Int.MAX_VALUE))
        Assert.assertEquals(0, reverse(-1563847412))

    }
}