package com.kyhorne.translator.table.with_transitions;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.transducer.Scanner;
import com.kyhorne.translator.transducer.Transducer;

import java.util.HashMap;
import java.util.List;

public class ScannerReadAheadTable extends TableWithTransitions {
  public ScannerReadAheadTable(Transducer transducer) {
    super(transducer);
  }

  @Override
  public Integer run() {
    if (transducer instanceof Scanner) {
      var scanner = (Scanner) transducer;
      var character = scanner.peekInput();
      var key = character;
      if (character instanceof String) {
        key = (int) ((String) character).charAt(0);
      }
      List<?> pair;
      if (transitions.containsKey(key)) {
        pair = (List<?>) transitions.get(key);
        var attributes = (String) pair.get(SampleTranslator.FIRST);
        var goTo = (Integer) pair.get(SampleTranslator.SECOND);
        var isRead = attributes.contains("R");
        var isKeep = attributes.contains("K");
        if (!isRead) {
          return goTo;
        }
        if (isKeep) {
          scanner.setKeptCharacters(((Scanner) transducer).getKeptCharacters() + character);
        }
        scanner.discardInput();
        return goTo;
      }
    }
    // reportLexicalError();
    throw new RuntimeException(String.format("The Transducer is not an instance of Scanner"));
  }

  public void reportLexicalError() {}

  @Override
  public void setTable(List<?> table) {
    transitions = new HashMap<>();
    table.stream()
        .map(row -> (List<?>) row)
        .forEach(
            triple -> {
              var characterOrInteger = triple.get(SampleTranslator.FIRST);
              // A list of integers.
              if (characterOrInteger instanceof List) {
                ((List<Integer>) characterOrInteger)
                    .stream()
                        .forEach(
                            item -> {
                              transitions.put(item, triple.subList(1, triple.size()));
                            });
              } else {
                // A string of characters.
                ((String) characterOrInteger)
                    .codePoints()
                    .mapToObj(c -> (int) ((char) c))
                    .forEach(ascii -> transitions.put(ascii, triple.subList(1, triple.size())));
              }
            });
  }
}
