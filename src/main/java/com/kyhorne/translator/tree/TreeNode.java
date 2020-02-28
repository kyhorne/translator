package com.kyhorne.translator.tree;

import com.kyhorne.translator.util.Identifiable;

import java.util.List;

public class TreeNode implements Identifiable {

  private String label;

  // Can either be a token or tree node.
  private List<Identifiable> children;

  public TreeNode(String label, List<Identifiable> children) {
    this.label = label;
    this.children = children;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public Identifiable getChild(int index) {
    return children.get(index);
  }
}
