package com.kyhorne.translator.token;

import com.kyhorne.translator.util.Identifiable;

public class Token implements Identifiable {

  // Label: ADD, SUB, etc.
  // Symbol: 1, 2, 3, etc.
  private String label, symbol;

  public Token(String label, String symbol) {
    this.label = label;
    this.symbol = symbol;
  }

  @Override
  public int hashCode() {
    return label.hashCode() + symbol.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }

    if (!(object instanceof Token)) {
      return false;
    }

    var token = (Token) object;
    return token.getLabel().equals(label) && token.getSymbol().equals(symbol);
  }

  @Override
  public String getLabel() {
    return label;
  }

  public String getSymbol() {
    return symbol;
  }
}
