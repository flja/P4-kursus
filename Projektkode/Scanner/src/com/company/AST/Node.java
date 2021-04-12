package com.company.AST;

import com.company.Tokens.Token;

public class Node
{
    public Node parent = null;
    public Node leftMostChild = null;
    public Node rightSib = null;
    public Node leftMostSib = this;
    public boolean visited = false;

    public Node MakeSiblings(Node node)
    {
        Node xsibs = this;
        while (xsibs.rightSib != null)
        {
            xsibs = xsibs.rightSib;
        }
        Node ysibs = node.leftMostSib;
        xsibs.rightSib = ysibs;
        ysibs.leftMostSib = xsibs.leftMostSib;
        ysibs.parent = xsibs.parent;
        while (ysibs.rightSib != null)
        {
            ysibs = ysibs.rightSib;
            ysibs.leftMostSib = xsibs.leftMostSib;
            ysibs.parent = xsibs.parent;
        }
        return ysibs;
    }

    public void AdoptChildren(Node node)
    {
        if (this.leftMostChild != null)
        {
            this.leftMostChild.MakeSiblings(node);
        }
        else
        {
            Node ysibs = node.leftMostSib;
            this.leftMostChild = ysibs;
            while (ysibs != null)
            {
                ysibs.parent = this;
                ysibs = ysibs.rightSib;
            }
        }
    }
}
