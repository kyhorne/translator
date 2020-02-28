package com.kyhorne.translator.table.with_transitions;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.token.Token;
import com.kyhorne.translator.transducer.Parser;
import com.kyhorne.translator.transducer.Transducer;
import com.kyhorne.translator.util.Identifiable;

import java.util.Collections;
import java.util.List;

public class ReduceTable extends TableWithTransitions {
  private String nonTerminal;

  public ReduceTable(Transducer transducer) {
    super(transducer);
  }

  public void reportDesignError() {
    // todo
  }

  @Override
  public Integer run() {
    Identifiable tree = null;
    if (transducer instanceof Parser) {
      var parser = (Parser) transducer;
      if (parser.getNewTree() != null) {
        tree = parser.getNewTree();
        parser.setNewTree(null);
      } else {
        var children = parser.getTreeNodeStack().subList(parser.getLeft() - 1, parser.getRight());
        children.removeAll(Collections.singleton(null));
        if (children.isEmpty()) {
          tree = null;
        } else if (children.size() == 1) {
          tree = children.get(SampleTranslator.FIRST);
        } else {
          // Error
          System.exit(1);
        }
      }
      for (int i = parser.getLeft(); i <= parser.getRight(); i++) {
        parser.getTokenStack().remove(parser.getTokenStack().size() - 1);
        parser.getTableNumberStack().remove(parser.getTableNumberStack().size() - 1);
        parser.getTreeNodeStack().remove(parser.getTreeNodeStack().size() - 1);
      }
      var tableNumber = parser.getTableNumberStack().get(parser.getTableNumberStack().size() - 1);
      if (!transitions.containsKey(tableNumber)) {
        // reportDesignError()
        throw new RuntimeException(
            String.format("The transitions table does not contain the key: %s", tableNumber));
      }
      var transition = (List<?>) transitions.get(tableNumber);
      var attributes = (String) transition.get(0);
      var goTo = (Integer) transition.get(1);
      var isStack = attributes.contains("S");
      if (isStack) {
        parser.getTokenStack().add(new Token(nonTerminal, nonTerminal));
        parser.getTableNumberStack().add(goTo);
        parser.getTreeNodeStack().add(tree);
        parser.setRight(parser.getTreeNodeStack().size());
        parser.setLeft(parser.getRight() + 1);
      }
      return goTo;
    }
    throw new RuntimeException(String.format("The Transducer is not an instance of Parser"));
  }

  @Override
  public void setTable(List<?> table) {
    nonTerminal = (String) table.get(SampleTranslator.FIRST);
    super.setTable(table.subList(1, table.size()));
  }
}
