import javax.swing.tree.TreeNode;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


class Solution {
    public int diameterOfBinaryTree(TreeNode root) {

        // Create an array to hold the diameter of the tree
        int diameter[] = new int[1];

        // Recursively calculate the height of the tree and update the diameter array
        height(root,diameter);

        // Return the diameter of the tree
        return diameter[0];
    }

    public int height(TreeNode root, int diameter[]){

        // Base case: if the root is null, the height is 0
        if(root == null){
            return 0;
        }

        // Recursively calculate the height of the left and right subtrees
        int left = height(root.left,diameter);
        int right = height(root.right,diameter);

        // Update the diameter array by taking the maximum diameter that passes through the current node
        diameter[0] = Math.max(diameter[0],left + right);

        // Return the maximum depth of the current node by adding 1 to the maximum depth of its deepest subtree
        return Math.max(left,right)+1;
    }
}

- Root is 10, diameter 1
        - Height(10,1)
        - Left = height(6,1)
        - Left = height (4,1)
        - Left = height (3,1)
        -
        - For 3 root, Left = height (null,1) —> return 0+1. Right = height (null,1) —> return 0 + 1 = 1
        - For 5 root, same nulls and 0
        - At this point both 3 and 5 nodes have diameter set to D[0] =  1 vs 0+0 = 1 and returned 0+1 = 1
        -
        - 4  node L = 1 (from 3 node) and 1 (from 5 node)
        - Diameter [0] = 1 vs 1+1 = 2 —> D[0] = 2. Return 1+1 = 2
        - Similarly 8 node will also return 2 and D[0] = 2.
        - Hypothetically, if 8 node had longer subtree, and say D[0] was 3 and returned 3 then  for 6 node….
        -
        - 6 node. L = 2 (4 node) and R = 3 (8 node). D[0] = 3 or 2+3 = 5. Return 3 (right) + 1 = 4
        - D[0] = 5, returned value from 6 node is 4 since there are 5 vertices between  lowest node of 6.right and lowest node of 6.left and the 6 node is 4th node from the bottom node

        - Finally, lets propose that the 10 node is the original root and root.righht has only null value such that left has returned value of 4 as its depth and right has returned value of 0
        - diameter[0] = 5 vs 4+0 = 5. D[0] remains the same.
        - Return (4 vs 0) + 1 = 5 = maximum depth

        - Demonstrated here that a node from subtree of root.left is has more vertices to traverse getting to another node in subtree of root.left than it does reaching the root in this unbalanced tree example.