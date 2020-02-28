package com.kyhorne.translator.transducer;

import com.kyhorne.translator.table.AcceptTable;
import com.kyhorne.translator.token.Token;
import com.kyhorne.translator.tree.TreeNode;
import com.kyhorne.translator.util.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser extends Transducer {
  private final Scanner scanner;
  private final List<Token> tokenStack;
  private final List<Integer> tableNumberStack;
  private final List<Identifiable> treeNodeStack;
  private Screener screener;
  private Integer left, right, tableNumber;
  private TreeNode newTree;

  public Parser() {
    scanner = new Scanner();
    screener = new Screener();
    // Instantiate the token stack.
    tokenStack = new Stack<>();
    tokenStack.add(new Token("|-", "|-"));
    // Instantiate the tree stack.
    treeNodeStack = new Stack<>();
    treeNodeStack.add(null);
    // Instantiate the table number stack.
    tableNumberStack = new Stack<>();
    tableNumberStack.add(1);
    left = 1;
    right = 1;
  }

  public TreeNode getNewTree() {
    return newTree;
  }

  public void setNewTree(TreeNode newTree) {
    this.newTree = newTree;
  }

  public Integer getRight() {
    return right;
  }

  public void setRight(Integer right) {
    this.right = right;
  }

  public List<Identifiable> getTreeNodeStack() {
    return treeNodeStack;
  }

  public List<Integer> getTableNumberStack() {
    return tableNumberStack;
  }

  public List<Token> getTokenStack() {
    return tokenStack;
  }

  public TreeNode parse(String expression) {
    scanner.scan(expression);
    tableNumber = 1;
    var table = tables.get(tableNumber);
    while (!(table instanceof AcceptTable)) {
      tableNumber = table.run();
      table = tables.get(tableNumber);
    }
    return (TreeNode) treeNodeStack.get(treeNodeStack.size() - 1);
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScreener(Screener screener) {
    this.screener = screener;
  }

  public Integer getLeft() {
    return left;
  }

  public void setLeft(Integer left) {
    this.left = left;
  }

  public void discardScannerToken() {
    scanner.discardToken();
  }

  public Token peekScannerToken() {
    return screener.screen(scanner.peekToken());
  }

  public void buildTree(String rootNode) {
    List<Identifiable> children = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      var treeNode = treeNodeStack.get(i - 1);
      if (treeNode != null) {
        children.add(treeNode);
      }
    }
    newTree = new TreeNode(rootNode, children);
  }
}
