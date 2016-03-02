package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.*;

import java.util.ArrayList;
import java.util.List;

/**
 * For creating Formulas from string
 */
public class FormulaParser {

    private static final String NOT_WELL_FORMED_ERROR = "Not a well formed formula";
    private static final String MISSING_BRACKETS_ERROR = "Formula is missing brackets.";
    private final String originalFormulaString;

    public FormulaParser(String formulaString) {
        this.originalFormulaString = formulaString.replaceAll("!([a-zA-Z])", "!($1)").replaceAll("\\s", "");
    }

    /**
     * @return Formula object represented by given description
     */
    public Formula getFormula() {
        return process(originalFormulaString);
    }

    private Formula process(String formulaString) {
        if (formulaString.isEmpty())
            return null;

        int nestingLevel = 0;
        StringBuilder part = new StringBuilder();
        List<String> parts = new ArrayList<>();

        for (char symbol : formulaString.toCharArray()) {
            if (symbol == '(')
                nestingLevel++;
            else if (symbol == ')')
                nestingLevel--;

            part.append(symbol);

            if (nestingLevel == 0) {
                parts.add(part.toString());
                part = new StringBuilder();
            }
        }

        if (nestingLevel != 0)
            throw new FormulaError(MISSING_BRACKETS_ERROR);

        if ((parts.contains("&") || parts.contains("|") || parts.contains("-") || parts.contains("=")) && parts.size() <= 5)
            return processBinaryFunction(parts);
        else if (parts.get(0).equals("!") && parts.size() == 2)
            return processNegation(parts);
        else if (parts.size() == 1) {
            if (parts.get(0).length() == 1)
                return processAtomic(parts);
            else
                return processBrackets(parts);
        }

        throw new FormulaError(NOT_WELL_FORMED_ERROR);
    }

    private Formula processBrackets(List<String> parts) {
        String inBrackets = parts.get(0);
        return process(inBrackets.substring(1, inBrackets.length() - 1));
    }

    private Formula processAtomic(List<String> parts) {
        return new AtomicFormula(parts.get(0).charAt(0));
    }

    private Formula processNegation(List<String> parts) {
        return new Negation(process(parts.get(1)));
    }

    private Formula processBinaryFunction(List<String> parts) {
        int currentPart = 0;
        StringBuilder[] subformula = { new StringBuilder(), new StringBuilder() };

        FormulaType formulaType = null;

        for (String part : parts) {
            switch (part) {
                case "&":
                    currentPart++;
                    formulaType = FormulaType.CONJUNCTION;
                    break;
                case "|":
                    currentPart++;
                    formulaType = FormulaType.DISJUNCTION;
                    break;
                case "-":
                    currentPart++;
                    formulaType = FormulaType.IMPLICATION;
                    break;
                case "=":
                    currentPart++;
                    formulaType = FormulaType.EQUIVALENCE;
                    break;
                default:
                    subformula[currentPart].append(part);
                    break;
            }
        }

        if (formulaType == null)
            throw new FormulaError("No type of formula detected!");

        switch (formulaType) {
            case CONJUNCTION:
                return new Conjunction(process(subformula[0].toString()), process(subformula[1].toString()));
            case DISJUNCTION:
                return new Disjunction(process(subformula[0].toString()), process(subformula[1].toString()));
            case IMPLICATION:
                return new Implication(process(subformula[0].toString()), process(subformula[1].toString()));
            case EQUIVALENCE:
                return new Equivalence(process(subformula[0].toString()), process(subformula[1].toString()));
            default:
                throw new FormulaError("No type of formula detected!");
        }
    }
}
