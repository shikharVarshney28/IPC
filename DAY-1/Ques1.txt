class Solution {
    public int findMinArrowShots(int[][] points) {
        //sort the array 
        Arrays.sort(points,(a,b)->(a[1]<=b[1])?-1:1);
        int arrows = 1,last=points[0][1];
        for(int i=1;i<points.length;i++){
            if(!(last>=points[i][0] && last<=points[i][1])){   //xstart <= x <= xend
                arrows++;
                last = points[i][1];
            }
        }
        return arrows;
    }
}