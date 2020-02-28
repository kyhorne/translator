package com.kyhorne.translator;

import java.util.List;

public class RawTable {
  public static final String READ_AHEAD_TABLE = "ReadAheadTable";
  public static final String READ_BACK_TABLE = "ReadBackTable";
  public static final String SHIFT_BACK_TABLE = "ShiftBackTable";
  public static final String REDUCE_TABLE = "ReduceTable";
  public static final String SEMANTIC_TABLE = "SemanticTable";
  public static final String ACCEPT_TABLE = "AcceptTable";
  public static final String SCANNER_READ_AHEAD_TABLE = "ScannerReadAheadTable";
  public static final String EXPRESSION = "EXPRESSION";
  public static final String GRAMMAR = "Grammar";
  public static final String TERM = "Term";
  public static final String PRIMARY = "Primary";
  public static final String BUILD_TREE = "buildTree";
  public static final String BUILD_TOKEN = "buildToken";
  public static final String IDENTIFIER = "Identifier";
  public static final int FIRST = 0;
  public static final int SECOND = 1;
  public static final int THIRD = 2;
  public static final String WALK_IDENTIFIER = "walkIdentifier";
  protected static final String DEFAULT_EXPRESSION = "1 + 2 * 3 + 5";
  protected static final String PUSH = "PUSH";
  protected static final String MULTIPLY = "MULTIPLY";
  protected static final String MINUS = "MINUS";
  protected static final String DIVIDE = "DIVIDE";
  protected static final String ADD = "ADD";
  protected static final String PLUS_OPERATOR = "+";
  protected static final String MINUS_OPERATOR = "-";
  protected static final String MULTIPLY_OPERATOR = "*";
  protected static final String DIVIDE_OPERATOR = "/";
  protected static final String INTEGER = "Integer";
  protected static final String SEND = "send";

  public static List<List<?>> getParserTables() {
    return List.of(
        List.of("keywords", "where"),
        List.of(
            READ_AHEAD_TABLE,
            1,
            List.of(INTEGER, "RSN", 28),
            List.of(IDENTIFIER, "RSN", 4),
            List.of("(", "RS", 5)),
        List.of(
            READ_AHEAD_TABLE,
            1,
            List.of(INTEGER, "RSN", 28),
            List.of(IDENTIFIER, "RSN", 4),
            List.of("(", "RS", 5)),
        List.of(READ_AHEAD_TABLE, 2, List.of("+", "RS", 6), List.of("-|", "L", 25)),
        List.of(
            READ_AHEAD_TABLE,
            3,
            List.of("*", "RS", 7),
            List.of("+", "L", 26),
            List.of("-|", "L", 26),
            List.of(";", "L", 26),
            List.of(")", "L", 26),
            List.of(",", "L", 26)),
        List.of(
            READ_AHEAD_TABLE,
            4,
            List.of("(", "RS", 8),
            List.of("=", "RS", 9),
            List.of("+", "L", 28),
            List.of("*", "L", 28),
            List.of("-|", "L", 28),
            List.of(";", "L", 28),
            List.of(")", "L", 28),
            List.of(",", "L", 28)),
        List.of(
            READ_AHEAD_TABLE,
            5,
            List.of(INTEGER, "RSN", 28),
            List.of(IDENTIFIER, "RSN", 11),
            List.of("(", "RS", 5)),
        List.of(
            READ_AHEAD_TABLE,
            6,
            List.of(IDENTIFIER, "RSN", 11),
            List.of("(", "RS", 5),
            List.of(INTEGER, "RSN", 28)),
        List.of(
            READ_AHEAD_TABLE,
            7,
            List.of(IDENTIFIER, "RSN", 11),
            List.of("(", "RS", 5),
            List.of(INTEGER, "RSN", 28)),
        List.of(
            READ_AHEAD_TABLE,
            8,
            List.of(")", "RS", 32),
            List.of(INTEGER, "RSN", 28),
            List.of(IDENTIFIER, "RSN", 11),
            List.of("(", "RS", 5)),
        List.of(
            READ_AHEAD_TABLE,
            9,
            List.of(INTEGER, "RSN", 28),
            List.of(IDENTIFIER, "RSN", 11),
            List.of("(", "RS", 5)),
        List.of(READ_AHEAD_TABLE, 10, List.of(")", "RS", 29), List.of("+", "RS", 6)),
        List.of(
            READ_AHEAD_TABLE,
            11,
            List.of("(", "RS", 8),
            List.of("+", "L", 28),
            List.of("*", "L", 28),
            List.of("-|", "L", 28),
            List.of(";", "L", 28),
            List.of(")", "L", 28),
            List.of(",", "L", 28)),
        List.of(
            READ_AHEAD_TABLE,
            12,
            List.of("*", "RS", 7),
            List.of("+", "L", 30),
            List.of("-|", "L", 30),
            List.of(";", "L", 30),
            List.of(")", "L", 30),
            List.of(",", "L", 30)),
        List.of(
            READ_AHEAD_TABLE,
            13,
            List.of("+", "RS", 6),
            List.of(",", "RS", 15),
            List.of(")", "RS", 32)),
        List.of(READ_AHEAD_TABLE, 14, List.of("+", "RS", 6), List.of(";", "RS", 16)),
        List.of(
            READ_AHEAD_TABLE,
            15,
            List.of(INTEGER, "RSN", 28),
            List.of(IDENTIFIER, "RSN", 11),
            List.of("(", "RS", 5)),
        List.of(READ_AHEAD_TABLE, 16, List.of(IDENTIFIER, "RSN", 18), List.of("-|", "L", 33)),
        List.of(
            READ_AHEAD_TABLE,
            17,
            List.of("+", "RS", 6),
            List.of(",", "RS", 15),
            List.of(")", "RS", 32)),
        List.of(READ_AHEAD_TABLE, 18, List.of("=", "RS", 9)),
        List.of(
            READ_BACK_TABLE,
            19,
            List.of(
                List.of("(", 8, "RS", 22),
                List.of(List.of(EXPRESSION, 13), "RSN", 34),
                List.of(List.of(EXPRESSION, 17), "RSN", 35))),
        List.of(
            READ_BACK_TABLE,
            20,
            List.of(List.of(EXPRESSION, 10), "RSN", 40),
            List.of(List.of(EXPRESSION, 17), "RSN", 40),
            List.of(List.of(EXPRESSION, 2), "RSN", 40),
            List.of(List.of(EXPRESSION, 13), "RSN", 40),
            List.of(List.of(EXPRESSION, 14), "RSN", 40)),
        List.of(
            READ_BACK_TABLE,
            21,
            List.of(List.of(TERM, 12), "RSN", 41),
            List.of(List.of(TERM, 3), "RSN", 41)),
        List.of(
            READ_BACK_TABLE,
            22,
            List.of(List.of(IDENTIFIER, 4), "RSN", 42),
            List.of(List.of(IDENTIFIER, 11), "RSN", 42)),
        List.of(
            READ_BACK_TABLE,
            23,
            List.of(List.of(EXPRESSION, 13), "RSN", 34),
            List.of(List.of(EXPRESSION, 17), "RSN", 35)),
        List.of(
            READ_BACK_TABLE,
            24,
            List.of(List.of(IDENTIFIER, 4), "RSN", 43),
            List.of(List.of(IDENTIFIER, 18), "RSN", 33)),
        List.of(SHIFT_BACK_TABLE, 25, 1, 37),
        List.of(SHIFT_BACK_TABLE, 26, 1, 36),
        List.of(SHIFT_BACK_TABLE, 27, 1, 38),
        List.of(SHIFT_BACK_TABLE, 28, 1, 39),
        List.of(SHIFT_BACK_TABLE, 29, 3, 39),
        List.of(SHIFT_BACK_TABLE, 30, 2, 20),
        List.of(SHIFT_BACK_TABLE, 31, 2, 21),
        List.of(SHIFT_BACK_TABLE, 32, 1, 19),
        List.of(SHIFT_BACK_TABLE, 33, 3, 24),
        List.of(SHIFT_BACK_TABLE, 34, 1, 22),
        List.of(SHIFT_BACK_TABLE, 35, 1, 23),
        List.of(
            REDUCE_TABLE,
            36,
            EXPRESSION,
            List.of(1, "RSN", 2),
            List.of(5, "RSN", 10),
            List.of(8, "RSN", 13),
            List.of(9, "RSN", 14),
            List.of(15, "RSN", 17)),
        List.of(REDUCE_TABLE, 37, GRAMMAR, List.of(1, "RSN", 44)),
        List.of(
            REDUCE_TABLE,
            38,
            TERM,
            List.of(1, "RSN", 3),
            List.of(5, "RSN", 3),
            List.of(6, "RSN", 12),
            List.of(8, "RSN", 3),
            List.of(9, "RSN", 3),
            List.of(15, "RSN", 3)),
        List.of(
            REDUCE_TABLE,
            39,
            PRIMARY,
            List.of(1, "RSN", 27),
            List.of(5, "RSN", 27),
            List.of(6, "RSN", 27),
            List.of(7, "RSN", 31),
            List.of(8, "RSN", 27),
            List.of(9, "RSN", 27),
            List.of(15, "RSN", 27)),
        List.of(SEMANTIC_TABLE, 40, BUILD_TREE, "+", 36),
        List.of(SEMANTIC_TABLE, 41, BUILD_TREE, "*", 38),
        List.of(SEMANTIC_TABLE, 42, BUILD_TREE, SEND, 39),
        List.of(SEMANTIC_TABLE, 43, BUILD_TREE, "<-", 37),
        List.of(ACCEPT_TABLE, 44));
  }

  public static List<List<?>> getScannerTables() {
    return List.of(
        List.of(
            SCANNER_READ_AHEAD_TABLE,
            1,
            List.of(List.of(256), "L", 5),
            List.of(")", "RK", 7),
            List.of("*", "RK", 8),
            List.of("+", "RK", 9),
            List.of(",", "RK", 10),
            List.of("0123456789", "RK", 2),
            List.of("(", "RK", 6),
            List.of(";", "RK", 12),
            List.of("=", "RK", 13),
            List.of("ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz", "RK", 3),
            List.of(List.of(9, 10, 12, 13), "R", 4),
            List.of(" ", "R", 4)),
        List.of(
            SCANNER_READ_AHEAD_TABLE,
            2,
            List.of(List.of(9, 10, 12, 13, 256), "L", 11),
            List.of(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_+*=[]{}()^;#:.$ ", "L", 11),
            List.of("0123456789", "RK", 2)),
        List.of(
            SCANNER_READ_AHEAD_TABLE,
            3,
            List.of(List.of(9, 10, 12, 13, 256), "L", 14),
            List.of("+*=[]{}()^;#:.$ ", "L", 14),
            List.of("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz", "RK", 3)),
        List.of(
            SCANNER_READ_AHEAD_TABLE,
            4,
            List.of(List.of(256), "L", 1),
            List.of(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_0123456789+*=[]{}()^;#:.$",
                "L",
                1),
            List.of(List.of(9, 10, 12, 13), "R", 4),
            List.of(" ", "R", 4)),
        List.of(SEMANTIC_TABLE, 5, BUILD_TOKEN, "-|", 1),
        List.of(SEMANTIC_TABLE, 6, BUILD_TOKEN, "(", 1),
        List.of(SEMANTIC_TABLE, 7, BUILD_TOKEN, ")", 1),
        List.of(SEMANTIC_TABLE, 8, BUILD_TOKEN, "*", 1),
        List.of(SEMANTIC_TABLE, 9, BUILD_TOKEN, "+", 1),
        List.of(SEMANTIC_TABLE, 10, BUILD_TOKEN, ",", 1),
        List.of(SEMANTIC_TABLE, 11, BUILD_TOKEN, INTEGER, 1),
        List.of(SEMANTIC_TABLE, 12, BUILD_TOKEN, ";", 1),
        List.of(SEMANTIC_TABLE, 13, BUILD_TOKEN, "=", 1),
        List.of(SEMANTIC_TABLE, 14, BUILD_TOKEN, IDENTIFIER, 1));
  }
}
