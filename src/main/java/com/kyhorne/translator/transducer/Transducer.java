package com.kyhorne.translator.transducer;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.table.AcceptTable;
import com.kyhorne.translator.table.SemanticTable;
import com.kyhorne.translator.table.ShiftBackTable;
import com.kyhorne.translator.table.Table;
import com.kyhorne.translator.table.with_transitions.ReadAheadTable;
import com.kyhorne.translator.table.with_transitions.ReadBackTable;
import com.kyhorne.translator.table.with_transitions.ReduceTable;
import com.kyhorne.translator.table.with_transitions.ScannerReadAheadTable;
import com.kyhorne.translator.util.Sponsor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Transducer {

  protected List<Table> tables;

  private Sponsor sponsor;

  public Transducer() {
    tables = new ArrayList<>();
  }

  public void setSponsor(Sponsor sponsor) {
    this.sponsor = sponsor;
  }

  public void setTables(List<List<?>> tables) {
    this.tables =
        tables.stream()
            .map(
                row -> {
                  var subTable = row.subList(SampleTranslator.THIRD, row.size());
                  Table table = null;
                  switch ((String) row.get(SampleTranslator.FIRST)) {
                    case SampleTranslator.READ_AHEAD_TABLE:
                      table = new ReadAheadTable(this);
                      break;
                    case SampleTranslator.READ_BACK_TABLE:
                      table = new ReadBackTable(this);
                      break;
                    case SampleTranslator.SHIFT_BACK_TABLE:
                      table = new ShiftBackTable(this);
                      break;
                    case SampleTranslator.REDUCE_TABLE:
                      table = new ReduceTable(this);
                      break;
                    case SampleTranslator.SEMANTIC_TABLE:
                      table = new SemanticTable(this);
                      break;
                    case SampleTranslator.ACCEPT_TABLE:
                      table = new AcceptTable(this);
                      break;
                    case SampleTranslator.SCANNER_READ_AHEAD_TABLE:
                      table = new ScannerReadAheadTable(this);
                      break;
                    default:
                      throw new UnsupportedOperationException(
                          String.format("Unknown table type: %s", row.get(SampleTranslator.FIRST)));
                  }
                  table.setTable(subTable);
                  return table;
                })
            .collect(Collectors.toList());
  }
}
