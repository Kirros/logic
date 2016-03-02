package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Conjunction;
import com.logic.propositional.formulas.types.Formula;
import com.logic.propositional.formulas.types.Negation;

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

        if (parts.contains("&") && parts.size() <= 5)
            return processConjunction(parts);
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

    private Formula processConjunction(List<String> parts) {
        int currentPart = 0;
        StringBuilder[] subformula = { new StringBuilder(), new StringBuilder() };

        for (String part : parts) {
            if (part.equals("&"))
                currentPart++;
            else
                subformula[currentPart].append(part);
        }

        return new Conjunction(process(subformula[0].toString()), process(subformula[1].toString()));
    }
}
