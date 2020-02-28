package com.kyhorne.translator.transducer;

import com.kyhorne.translator.SampleTranslator;
import com.kyhorne.translator.token.Token;

import java.util.ArrayList;
import java.util.List;

public class Screener extends Transducer {

  private List<String> keywords;

  public Screener(List<String> keywords) {
    this.keywords = keywords;
  }

  public Screener() {
    this.keywords = new ArrayList<>();
  }

  public Token screen(Token token) {
    if (!(token.getLabel().equals(SampleTranslator.IDENTIFIER)
        || token.getLabel().equals(SampleTranslator.WALK_IDENTIFIER))) {
      return token;
    }
    if (!keywords.stream().anyMatch(keyword -> keyword.equals(token.getSymbol()))) {
      return token;
    }
    return new Token(token.getSymbol(), token.getSymbol());
  }

  @Override
  public void setTables(List<List<?>> tables) {}
}
