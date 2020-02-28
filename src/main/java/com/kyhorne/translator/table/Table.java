package com.kyhorne.translator.table;

import com.kyhorne.translator.transducer.Transducer;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
  protected Transducer transducer;

  protected List<?> table;

  public Table(Transducer transducer) {
    this.transducer = transducer;
    this.table = new ArrayList<>();
  }

  public abstract Integer run();

  public abstract void setTable(List<?> table);
}
