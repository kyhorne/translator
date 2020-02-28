package com.kyhorne.translator.table.with_transitions;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.table.Table;
import com.kyhorne.translator.transducer.Transducer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableWithTransitions extends Table {

  protected Map<Object, Object> transitions;

  public TableWithTransitions(Transducer transducer) {
    super(transducer);
  }

  @Override
  public void setTable(List<?> table) {
    transitions = new HashMap<>();
    table.stream()
        .map(row -> (List<?>) row)
        .forEach(
            triple -> {
              transitions.put(triple.get(SampleTranslator.FIRST), triple.subList(1, triple.size()));
            });
  }
}
