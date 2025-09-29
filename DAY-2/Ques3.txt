class Solution {
    public boolean isPerfectSquare(int num) {
        double d = Math.sqrt(num);
        return Math.floor(d) == Math.ceil(d);
    }
}