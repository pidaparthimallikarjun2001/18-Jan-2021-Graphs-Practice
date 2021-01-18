/*

Approach: Every acyclic graph is bipartite. If there is no cycle, then there's no problem. Every cyclic graph of even cycles is also a bipartite. 

I am performing BFS. I made my own class with current vertex and current level(distance from start vertex). I made a visited array of int type and initialized it with -1(not visited).

If it is even-cyclic, then while performing BFS, one vertex will be visited twice. I checked if they belong to the same level. If yes, then it is bipartite.

*/


class Solution {
    
    static class VertexAndLevel {
        
        int vertex;
        int level;
        
        public VertexAndLevel(int vertex, int level) {
            
            this.vertex = vertex;
            this.level = level;
            
        }
        
        public String toString() {
            return vertex + " " + level;
        }
        
    } 
    
    
    public boolean checkForBipartite(int[][] graph, int src, int[] visited) {
        
        Queue<VertexAndLevel> queue = new LinkedList<VertexAndLevel>();
        queue.offer(new VertexAndLevel(src, 0));
        
        while(queue.size() > 0) {
            
            //System.out.println(queue);
            
            VertexAndLevel removed = queue.remove();   
            
           // System.out.println(removed);
                        
            if(visited[removed.vertex] != -1) {
                                                
                if(removed.level != visited[removed.vertex]) {
                    return false;
                }
                
            }
            
            else {
                                
                visited[removed.vertex] = removed.level; 
                
            }
            
           // System.out.println(removed);
                    
            
            for(Integer nbr: graph[removed.vertex]) {
                
                if(visited[nbr] == -1) {
                    queue.add(new VertexAndLevel(nbr, removed.level + 1));
                }
                
            }
            
        }
        
        return true;
        
        
    }
    
    public boolean isBipartite(int[][] graph) {
        
        int V = graph.length;
        
        int[] visited = new int[V];
        
        Arrays.fill(visited, -1);
        
        
        for(int i = 0; i < V; i++) {
            if(visited[i] == -1) {
                boolean isComponentBipartite = checkForBipartite(graph, i, visited);
                if(isComponentBipartite == false) {
                    return false;
                }
            }
        }
        return true;
    }
}