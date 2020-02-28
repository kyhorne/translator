package com.kyhorne.translator.table;

import com.kyhorne.translator.transducer.Transducer;

import java.util.List;

public class AcceptTable extends Table {

  public AcceptTable(Transducer transducer) {
    super(transducer);
  }

  @Override
  public Integer run() {
    // Ensure the parsing does not continue.
    return null;
  }

  @Override
  public void setTable(List<?> table) {}
}
