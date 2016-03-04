package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    Formula process(String formulaString) {
        Stack<FormulaBuilder> formula = new Stack<>();
        formula.push(new FormulaBuilder());

        char[] chars = formulaString.toCharArray();

        for (Character input : chars) {
            if (Character.isLetter(input))
                formula.push(formula.pop().addSubformula(new AtomicFormula(input)));
            else if (input == '(')
                formula.push(new FormulaBuilder());
            else if (input == ')') {
                Formula processedFormula = formula.pop().build();
                formula.push(formula.pop().addSubformula(processedFormula));

                if (formula.peek().getFormulaType() == FormulaType.NEGATION) {
                    processedFormula = formula.pop().build();
                    formula.push(formula.pop().addSubformula(processedFormula));
                }
            }
            else if (input == '!')
                formula.push(new FormulaBuilder().setFormulaType(FormulaType.NEGATION));
            else if (input == '&')
                formula.push(formula.pop().setFormulaType(FormulaType.CONJUNCTION));
            else if (input == '|')
                formula.push(formula.pop().setFormulaType(FormulaType.DISJUNCTION));
            else if (input == '-')
                formula.push(formula.pop().setFormulaType(FormulaType.IMPLICATION));
            else if (input == '=')
                formula.push(formula.pop().setFormulaType(FormulaType.EQUIVALENCE));
        }

        return formula.pop().build();
    }

    private class FormulaBuilder {
        private FormulaType formulaType;
        private List<Formula> subformulas = new ArrayList<>();

        FormulaBuilder setFormulaType(FormulaType formulaType) {
            if (this.formulaType == null)
                this.formulaType = formulaType;
            else if (formulaType != this.formulaType)
                throw new FormulaError(MISSING_BRACKETS_ERROR);

            return this;
        }

        FormulaBuilder addSubformula(Formula subformula) {
            subformulas.add(subformula);
            return this;
        }

        FormulaType getFormulaType() {
            return formulaType;
        }

        Formula build() {
            if (formulaType == null && subformulas.size() == 1)
                return subformulas.get(0);
            else if (formulaType == FormulaType.NEGATION && subformulas.size() == 1)
                return subformulas.get(0).not();
            else if (subformulas.size() == 0 && formulaType == null)
                return null;
            else if (subformulas.size() > 1) {
                Formula currentFormula = null;

                for (Formula subformula : subformulas) {
                    if (currentFormula == null)
                        currentFormula = subformula;
                    else if (formulaType == FormulaType.CONJUNCTION)
                        currentFormula = currentFormula.and(subformula);
                    else if (formulaType == FormulaType.DISJUNCTION)
                        currentFormula = currentFormula.or(subformula);
                    else if (formulaType == FormulaType.IMPLICATION)
                        currentFormula = currentFormula.imply(subformula);
                    else if (formulaType == FormulaType.EQUIVALENCE)
                        currentFormula = currentFormula.equal(subformula);
                }

                return currentFormula;
            }
            else
                throw new FormulaError(NOT_WELL_FORMED_ERROR);
        }
    }
}
