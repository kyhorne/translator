package com.kyhorne.translator.table.with_transitions;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.transducer.Parser;
import com.kyhorne.translator.transducer.Transducer;

import java.util.List;

public class ReadBackTable extends TableWithTransitions {

  public ReadBackTable(Transducer transducer) {
    super(transducer);
  }

  @Override
  public Integer run() {
    if (transducer instanceof Parser) {
      var parser = (Parser) transducer;
      var pair =
          List.of(
              parser.getTokenStack().get(parser.getLeft() - 2).getLabel(),
              parser.getTableNumberStack().get(parser.getLeft() - 2));
      if (!transitions.containsKey(pair)) {
        reportDesignError();
      }
      var transition = (List<?>) transitions.get(pair);
      var attributes = (String) transition.get(SampleTranslator.FIRST);
      var goTo = (Integer) transition.get(SampleTranslator.SECOND);
      if (attributes.contains("R")) {
        parser.setLeft(parser.getLeft() - 1);
      }
      return goTo;
    }
    throw new Error(String.format("Invalid transducer type: %s", transducer));
  }

  public void reportDesignError() {
    throw new Error(
        "Design error: reached the end of the Read Back Table without finding a suitable transition");
  }
}
