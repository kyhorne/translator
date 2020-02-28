package com.kyhorne.translator.table;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.transducer.Parser;
import com.kyhorne.translator.transducer.Scanner;
import com.kyhorne.translator.transducer.Transducer;

import java.util.List;

public class SemanticTable extends Table {

  private String action;

  private List<?> parameters;

  private Integer goTo;

  public SemanticTable(Transducer transducer) {
    super(transducer);
  }

  @Override
  public Integer run() {
    switch (action) {
      case SampleTranslator.BUILD_TOKEN:
        if (transducer instanceof Scanner) {
          ((Scanner) transducer).buildToken((String) parameters.get(SampleTranslator.FIRST));
        }
        break;
      case SampleTranslator.BUILD_TREE:
        if (transducer instanceof Parser) {
          ((Parser) transducer).buildTree((String) parameters.get(SampleTranslator.FIRST));
        }
        break;
      default:
        throw new UnsupportedOperationException(String.format("Invalid action: %s", action));
    }
    return goTo;
  }

  @Override
  public void setTable(List<?> table) {
    action = (String) table.get(0);
    parameters = table.subList(1, table.size() - 1);
    goTo = (Integer) table.get(table.size() - 1);
  }
}
