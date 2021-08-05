class Node 
{ 
    int height; 
    String key;
    Node left, right, nextRight; 
  
    Node(String d) 
    { 
        key = d; 
        height = 1;      
        left = right = nextRight = null; 
    }  
} 