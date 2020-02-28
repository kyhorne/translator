package com.kyhorne.translator;

import com.kyhorne.translator.token.Token;
import com.kyhorne.translator.transducer.Parser;
import com.kyhorne.translator.transducer.Screener;
import com.kyhorne.translator.tree.TreeNode;
import com.kyhorne.translator.util.Identifiable;
import com.kyhorne.translator.util.Sponsor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SampleTranslator extends RawTable implements Sponsor {

  private static final Logger LOGGER = LogManager.getLogger(SampleTranslator.class);
  private final Parser parser;
  private final List<String> codeIfCompiler;
  private TreeNode treeNode;

  public SampleTranslator() {
    parser = new Parser();
    setupSponsorAndTables();
    codeIfCompiler = new ArrayList<>();
  }

  public void setupSponsorAndTables() {
    parser.setSponsor(this);
    parser.getScanner().setSponsor(this);
    var parsingTables = getParserTables();
    parser.setTables(parsingTables.subList(1, parsingTables.size()));
    var firstRow = parsingTables.get(RawTable.FIRST);
    var keywords = (List<String>) firstRow.subList(1, firstRow.size());
    parser.setScreener(new Screener(keywords));
    parser.getScanner().setTables(getScannerTables());
  }

  public static void promptForCompilation(String expression) {
    new SampleTranslator().compile(expression);
  }

  public static void promptForCompilation() {
    new SampleTranslator().compile(RawTable.DEFAULT_EXPRESSION);
  }

  public static void promptForEvaluation(String expression) {
    new SampleTranslator().evaluate(expression);
  }

  public static void promptForEvaluation() {
    new SampleTranslator().evaluate(RawTable.DEFAULT_EXPRESSION);
  }

  public void compile(String expression) {
    LOGGER.info(String.format("Compiling the expression: %s", expression));
    treeNode = parser.parse(expression);
    compileExpressionFor(treeNode);
    LOGGER.info(String.format("Compilation result: %s", codeIfCompiler));
  }

  public void compileExpressionFor(Identifiable treeNodeOrToken) {
    switch (treeNodeOrToken.getLabel()) {
      case PLUS_OPERATOR:
        compilePlus((TreeNode) treeNodeOrToken);
        break;
      case MINUS_OPERATOR:
        compileMinus((TreeNode) treeNodeOrToken);
        break;
      case MULTIPLY_OPERATOR:
        compileMultiply((TreeNode) treeNodeOrToken);
        break;
      case DIVIDE_OPERATOR:
        compileDivide((TreeNode) treeNodeOrToken);
        break;
      case INTEGER:
        compileInteger((Token) treeNodeOrToken);
        break;
      default:
        throw new UnsupportedOperationException(
            String.format("%s is an unimplemented operation", treeNodeOrToken.getLabel()));
    }
  }

  public void compilePlus(TreeNode treeNode) {
    compileExpressionFor(treeNode.getChild(FIRST));
    compileExpressionFor(treeNode.getChild(SECOND));
    generate(ADD);
  }

  public void compileMinus(TreeNode treeNode) {
    compileExpressionFor(treeNode.getChild(FIRST));
    compileExpressionFor(treeNode.getChild(SECOND));
    generate(MINUS);
  }

  public void compileMultiply(TreeNode treeNode) {
    compileExpressionFor(treeNode.getChild(FIRST));
    compileExpressionFor(treeNode.getChild(SECOND));
    generate(MULTIPLY);
  }

  public void compileDivide(TreeNode treeNode) {
    compileExpressionFor(treeNode.getChild(FIRST));
    compileExpressionFor(treeNode.getChild(SECOND));
    generate(DIVIDE);
  }

  public void generate(String instruction) {
    codeIfCompiler.add(instruction);
  }

  public void compileInteger(Token token) {
    generate(String.format("%s %s", PUSH, token.getSymbol()));
  }

  public void evaluate(String expression) {
    LOGGER.info(String.format("Evaluating the expression: %s", expression));
    treeNode = parser.parse(expression);
    var result = evaluateExpressionFor(treeNode);
    LOGGER.info(String.format("Evaluating result: %s", result));
  }

  public Integer evaluateExpressionFor(Identifiable treeNodeOrToken) {
    switch (treeNodeOrToken.getLabel()) {
      case PLUS_OPERATOR:
        return evaluatePlus((TreeNode) treeNodeOrToken);
      case MINUS_OPERATOR:
        return evaluateMinus((TreeNode) treeNodeOrToken);
      case MULTIPLY_OPERATOR:
        return evaluateMultiply((TreeNode) treeNodeOrToken);
      case DIVIDE_OPERATOR:
        return evaluateDivide((TreeNode) treeNodeOrToken);
      case INTEGER:
        return evaluateInteger((Token) treeNodeOrToken);
      default:
        throw new UnsupportedOperationException(
            String.format("%s is an unimplemented operation", treeNodeOrToken.getLabel()));
    }
  }

  public Integer evaluatePlus(TreeNode treeNode) {
    var expression1 = evaluateExpressionFor(treeNode.getChild(FIRST));
    var expression2 = evaluateExpressionFor(treeNode.getChild(SECOND));
    return expression1 + expression2;
  }

  public Integer evaluateMinus(TreeNode treeNode) {
    var expression1 = evaluateExpressionFor(treeNode.getChild(FIRST));
    var expression2 = evaluateExpressionFor(treeNode.getChild(SECOND));
    return expression1 - expression2;
  }

  public Integer evaluateMultiply(TreeNode treeNode) {
    var expression1 = evaluateExpressionFor(treeNode.getChild(FIRST));
    var expression2 = evaluateExpressionFor(treeNode.getChild(SECOND));
    return expression1 * expression2;
  }

  public Integer evaluateDivide(TreeNode treeNode) {
    var expression1 = (Integer) evaluateExpressionFor(treeNode.getChild(FIRST));
    var expression2 = (Integer) evaluateExpressionFor(treeNode.getChild(SECOND));
    return expression1 / expression2;
  }

  public Integer evaluateInteger(Token token) {
    return Integer.parseInt(token.getSymbol());
  }
}
