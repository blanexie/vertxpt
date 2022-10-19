class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var index = 0;
        for (num in nums) {
            val i = target - num
            val indexOfFirst = nums.indexOfFirst { it == i }
            if (indexOfFirst > -1 && indexOfFirst != index) {
                return intArrayOf(index, indexOfFirst)
            }
            index++
        }
        return intArrayOf()
    }
}


fun main() {
    val s = intArrayOf(3, 2, 4)
    val a = 6
    val twoSum = Solution().twoSum(s, a);
}