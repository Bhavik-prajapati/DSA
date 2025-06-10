import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Edge{
    int src;
    int dest;
    public  Edge(int s,int d){
        this.src=s;
        this.dest=d;
    }
}

public class BFS {

    public static void  bfs(ArrayList<Edge>[] graph,int v){

        Queue<Integer> queue=new LinkedList<>();
        boolean vis[]=new boolean[v];

        queue.add(0);

        while (!queue.isEmpty()){
            int curr=queue.remove();

            if(vis[curr]==false){
                System.out.print(curr+" ");
                vis[curr]=true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e=graph[curr].get(i);
                    queue.add(e.dest);
                }
            }

        }


    }

    public static  void dfs(ArrayList<Edge> graph[],int curr,boolean vis[]){
        System.out.print(curr+" ");
        vis[curr]=true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e=graph[curr].get(i);
            if(vis[e.dest]==false){
                dfs(graph,e.dest,vis);
            }
        }
    }

    public  static void printallpaths(ArrayList<Edge> graph[],boolean vis[],int curr,String path,int target){

        if(curr==target){
            System.out.println(path);
            return;
        }
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                vis[curr]=true;
                printallpaths(graph,vis,e.dest,path+e.dest,target);
                vis[curr]=false;
            }
        }
    }

    public  static  int numIslands(char[][] grid){
        if(grid==null || grid.length==0){
            return 0;
        }

        int count=0;
        int m=grid.length;
        int n=grid[0].length;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if(grid[i][j]=='1'){
                    Dfs(grid,i,j);
                    count++;
                }

            }

        }
        return count;
    }
    private static void Dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if(i<0|| j<0 || i>=m || j>=n || grid[i][j]=='0'){
            return;
        }
        grid[i][j] = '0';
        Dfs(grid, i + 1, j);
        Dfs(grid, i - 1, j);
        Dfs(grid, i, j + 1);
        Dfs(grid, i, j - 1);
    }


        public int orangesRotting(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            int freshCount = 0;

            // Step 1: Add all rotten oranges to the queue & count fresh oranges
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) {
                        queue.add(new int[]{i, j});
                    } else if (grid[i][j] == 1) {
                        freshCount++;
                    }
                }
            }

            // If no fresh oranges, return 0
            if (freshCount == 0) return 0;

            int minutes = 0;
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            // Step 2: BFS
            while (!queue.isEmpty()) {
                int size = queue.size();
                boolean rotted = false;

                for (int i = 0; i < size; i++) {
                    int[] cell = queue.poll();
                    int x = cell[0], y = cell[1];

                    for (int[] dir : directions) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];

                        // Check bounds and if the neighbor is a fresh orange
                        if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] == 1) {
                            grid[nx][ny] = 2; // rot the orange
                            queue.add(new int[]{nx, ny});
                            freshCount--;
                            rotted = true;
                        }
                    }
                }

                if (rotted) minutes++;
            }

            return freshCount == 0 ? minutes : -1;
        }




    public static void main(String[] args) {

        int[][] grid={
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
//        System.out.println(orangesRotting(grid));


//        char[][] grid = {
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}
//        };


//        int numberOfIslands = numIslands(grid);
//        System.out.println("Number of Islands: " + numberOfIslands);
//

//        int V=7;
//        ArrayList<Edge> graph[]=new ArrayList[V];
//        boolean[] vis=new boolean[V];
//        creategraph(graph);
//
//        printallpaths(graph,vis,0,"0",5);

//        bfs(graph,V);

//        for (int i = 0; i < V; i++) {
//            if(vis[i]==false){
//                dfs(graph,i,vis);
//            }
//        }




    }



    private static void creategraph(ArrayList<Edge>[] graph) {

        for (int i = 0; i < graph.length; i++) {
            graph[i]=new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));

        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));
    }
}
