package com.kyhorne.translator.table.with_transitions;

import com.kyhorne.translator.transducer.Parser;
import com.kyhorne.translator.transducer.Transducer;

import java.util.List;

public class ReadAheadTable extends TableWithTransitions {

  public ReadAheadTable(Transducer transducer) {
    super(transducer);
  }

  @Override
  public Integer run() {
    if (transducer instanceof Parser) {
      var parser = (Parser) transducer;
      var token = parser.peekScannerToken();
      var label = token.getLabel();
      if (!transitions.containsKey(label)) {
        reportSyntaxError();
      } else {
        var transition = transitions.get(label);
        if (transition instanceof List) {
          var attributes = (String) ((List) transition).get(0);
          var goTo = (Integer) ((List) transition).get(1);
          var isRead = attributes.contains("R");
          var isNode = attributes.contains("N");
          var isStack = attributes.contains("S");

          if (!isRead) {
            return goTo;
          }

          parser.discardScannerToken();
          if (isStack) {
            parser.getTokenStack().add(token);
            parser.getTableNumberStack().add(goTo);
            var element = isNode ? token : null;
            parser.getTreeNodeStack().add(element);
            parser.setRight(parser.getTokenStack().size());
            parser.setLeft(parser.getRight() + 1);
          }

          return goTo;
        }
      }
    }
    throw new Error(String.format("Invalid transducer type: %s", transducer));
  }

  public void reportSyntaxError() {
    // todo
  }
}
