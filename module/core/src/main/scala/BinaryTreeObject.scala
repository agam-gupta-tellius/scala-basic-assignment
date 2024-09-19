package core

object BinaryTreeObject {
  sealed trait BinaryTree[+T]
  case class Node[T](value: T, left: BinaryTree[T], right: BinaryTree[T]) extends BinaryTree[T]
  case object EmptyNode extends BinaryTree[Nothing]

  def inorder[T](root: BinaryTree[T]): List[T] = root match {
    case EmptyNode => Nil
    case Node(value, left, right) => (inorder(left) :+ value) ++ inorder(right)
  }

  def preorder[T](root: BinaryTree[T]): List[T] = root match {
    case EmptyNode => Nil
    case Node(value, left, right) => (value +: preorder(left)) ++ preorder(right)
  }

  def heightOfTree[T](root: BinaryTree[T]): Int = root match {
    case EmptyNode => 0
    case Node(_, left, right) => 1 + Math.max(heightOfTree(left), heightOfTree(right))
  }

  def leafNodes[T](root: BinaryTree[T]): Int = root match {
    case EmptyNode => 0
    case Node(_, EmptyNode, EmptyNode) => 1
    case Node(_, left, right) => leafNodes(left) + leafNodes(right)
  }
}
