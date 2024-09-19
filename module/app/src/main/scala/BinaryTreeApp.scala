package app

import core.BinaryTreeObject
import core.BinaryTreeObject.{BinaryTree, EmptyNode, Node}

object BinaryTreeApp {
  def main(args: Array[String]): Unit = {
    val root: BinaryTree[Int] = Node(1,
      Node(2, Node(4, EmptyNode, EmptyNode), Node(5, EmptyNode, EmptyNode)),
      Node(3, EmptyNode, Node(6, Node(7, EmptyNode, EmptyNode), EmptyNode))
    );
    
    val preorder: List[Int] = BinaryTreeObject.preorder(root);
    println(preorder);
    val inorder: List[Int] = BinaryTreeObject.inorder(root);
    println(inorder)
    
    val height: Int = BinaryTreeObject.heightOfTree(root);
    println(height)
    
    val leafCount: Int = BinaryTreeObject.leafNodes(root);
    println(leafCount)
  }
}
