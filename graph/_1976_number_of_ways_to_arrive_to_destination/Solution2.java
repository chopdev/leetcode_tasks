import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


// Not my solution: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/discuss/1417738/Simple-Java-Solution
public class Solution2 {
    int[] dist;
    long[] dp;

    public void findDis(Map<Integer, List<int[]>> graph){

        PriorityQueue<int[]> pq=new PriorityQueue<>((a, b) -> (a[1]- b[1]));
        pq.add(new int[]{0, 0});
        dist[0]=0;
        dp[0]=1;
        while(!pq.isEmpty()){
            int[] top=pq.poll();
            int u=top[0], d=top[1];
            if(dist[u]< d) // new edit
                continue;
            for(int[] v: graph.get(u)){
                if(dist[v[0]] > d + v[1]){
                    dist[v[0]] = d+v[1];
                    dp[v[0]]=dp[u];
                    pq.offer(new int[]{v[0], dist[v[0]]});
                }else{
                    if(dist[v[0]] == d+v[1]){
                        dp[v[0]]+=dp[u];
                        dp[v[0]]%= 1_000_000_007;
                    }
                }
            }
        }
    }

    public int countPaths(int n, int[][] roads) {
        if (n <= 1) return n;
        dist=new int[n];
        dp=new long[n];
        Map<Integer, List<int[]>> graph=new HashMap();
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i=0;i<n;i++){
            graph.put(i, new ArrayList());

        }
        for(int[] e: roads){
            graph.get(e[0]).add(new int[]{e[1], e[2]});
            graph.get(e[1]).add(new int[]{e[0], e[2]});
        }
        findDis(graph);

        return (int)dp[n-1]%1_000_000_007;

    }
}
