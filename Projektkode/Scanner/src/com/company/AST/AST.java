package com.company.AST;

public class AST
{
    public Node Root;
    public AST(Node aRoot)
    {
        Root = aRoot;
    }

    public void UnvisitNodes()
    {
        unvisitNode(this.Root);
    }

    private void unvisitNode(Node node)
    {
        node.visited = false;
        for (Node child : node.GetChildren() )
        {
            unvisitNode(child);
        }
    }
}
