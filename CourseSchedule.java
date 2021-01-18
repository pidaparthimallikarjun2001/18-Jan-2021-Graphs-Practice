class Solution {
    
    static class Edge {
        int src;
        int nbr;
        public Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
    
    public boolean hasCycle(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited, boolean[] isAncestorAlreadyInStack) {
        
        visited[src] = true;
        isAncestorAlreadyInStack[src] = true;
        boolean ans = false;
        
        for(Edge edge: graph.get(src)) {
            if(isAncestorAlreadyInStack[edge.nbr] == true) {
                return true;
            }
            if(!visited[edge.nbr]) {
                ans = hasCycle(graph, edge.nbr, visited, isAncestorAlreadyInStack);
                if(ans) {
                    return true;
                }
            }
        }
        
        isAncestorAlreadyInStack[src] = false;
        return false;
        
    } 
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {     
        
        int V = numCourses;
        int E = prerequisites.length;
        
          ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        
        for(int i = 0; i < V; i++) {
            graph.add(new ArrayList<Edge>());
        }
        
        boolean[] visited = new boolean[V];
        boolean[] isAncestorAlreadyInStack = new boolean[V];
        
        for(int i = 0; i < E; i++) {
            int v1 = prerequisites[i][0];
            int v2 = prerequisites[i][1];
            
            graph.get(v1).add(new Edge(v1, v2));
        }
        
        boolean isCyclic = false;
        
        for(int i = 0; i < V; i++) {
            if(visited[i]) {
                continue;
            }
            isCyclic = hasCycle(graph, i, visited, isAncestorAlreadyInStack);
            if(isCyclic) {
                return false;
            }
        }
        
        
        return true;
    }
}
