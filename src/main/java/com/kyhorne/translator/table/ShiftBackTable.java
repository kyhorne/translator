package com.kyhorne.translator.table;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.transducer.Parser;
import com.kyhorne.translator.transducer.Transducer;

import java.util.List;

public class ShiftBackTable extends Table {

  private Integer goTo, shift;

  public ShiftBackTable(Transducer transducer) {
    super(transducer);
  }

  @Override
  public Integer run() {
    if (transducer instanceof Parser) {
      var parser = (Parser) transducer;
      parser.setLeft(parser.getLeft() - shift);
    }
    return goTo;
  }

  @Override
  public void setTable(List<?> table) {
    shift = (Integer) table.get(SampleTranslator.FIRST);
    goTo = (Integer) table.get(SampleTranslator.SECOND);
  }
}
