class Solution {
    
    static class Edge {
        int src;
        int nbr;
        public Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
    
    public boolean hasCycle(ArrayList<ArrayList<Edge> >graph, int src, boolean[] visited, boolean[] ancestors) {
        visited[src] = true;
        ancestors[src] = true;
        boolean ans = false;
        for(Edge edge: graph.get(src)) {
            if(ancestors[edge.nbr]) {
                return true;
            }
            if(!visited[edge.nbr]) {
                ans = hasCycle(graph, edge.nbr, visited, ancestors);
            }
            if(ans) {
                return true;
            }
        }
        ancestors[src] = false;
        return false;
    }

    
    public void topologicalSort(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited, Stack<Integer> stack) {
        visited[src] = true;
        for(Edge edge: graph.get(src)) {
            if(!visited[edge.nbr]) {
                topologicalSort(graph, edge.nbr, visited, stack);
            }
        }
        stack.push(src);
    }
 
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        int V = numCourses;
        int E = prerequisites.length;
        
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>(V);
        
        for(int i = 0; i < V; i++) {
            graph.add(new ArrayList<Edge>());
        }
        
        boolean[] visited = new boolean[V];
        boolean[] ancestors = new boolean[V];
        
        
        for(int i = 0; i < E; i++) {
            
            int v1 = prerequisites[i][0];
            int v2 = prerequisites[i][1];
            
            graph.get(v1).add(new Edge(v1, v2));
            
            
        }
        
        boolean presenceOfCycle = false;
        for(int i = 0; i < V; i++) {
            if(visited[i]) {
                continue;
            }
            presenceOfCycle = hasCycle(graph, i, visited, ancestors);
            if(presenceOfCycle) {
                return new int[] {};
            }
        }
        
        for(int i = 0; i < V; i++) {
            visited[i] = false;
        }
        

        

        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < V; i++) {
            if(!visited[i]) {
                topologicalSort(graph, i, visited, stack);
            }
        }
        
        
        //System.out.println(stack);
        
        Stack<Integer> rev = new Stack<>();
        
        while(stack.size() > 0) {
            rev.push(stack.pop());
        }
        
        
        int[] arr = new int[V];
        int index = 0;
        while(rev.size() > 0) {
            arr[index++] = rev.pop();
        }
        
        return arr;
        
    }
}
