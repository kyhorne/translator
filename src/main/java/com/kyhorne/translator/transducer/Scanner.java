package com.kyhorne.translator.transducer;

import com.kyhorne.translator.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner extends Transducer {

  private int index;

  private List<String> input;

  private String keptCharacters;

  private Token token;

  public Scanner() {
    token = null;
    input = new ArrayList<>();
    keptCharacters = "";
    index = 0;
  }

  public String getKeptCharacters() {
    return keptCharacters;
  }

  public void setKeptCharacters(String keptCharacters) {
    this.keptCharacters = keptCharacters;
  }

  public void scan(String text) {
    var tokens = new StringTokenizer(text);
    while (tokens.hasMoreTokens()) {
      input.add(tokens.nextToken());
    }
    discardToken();
  }

  public void discardToken() {
    var tableNumber = 0;
    token = null;
    while (token == null) {
      if (tableNumber != 0) {
        tableNumber -= 1;
      }
      var table = tables.get(tableNumber);
      tableNumber = table.run();
    }
  }

  public void discardInput() {
    index++;
  }

  public Object peekInput() {
    if (input.size() == index) {
      return 256;
    } else {
      return input.get(index);
    }
  }

  public Token peekToken() {
    return token;
  }

  public void buildToken(String label) {
    token = new Token(label, keptCharacters);
    keptCharacters = "";
  }
}
