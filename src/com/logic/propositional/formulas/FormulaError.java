package com.logic.propositional.formulas;

/**
 * Exception thrown by formula
 */
public class FormulaError extends Error {
    public FormulaError(String message) {
        super("Formula Exception: " + message);
    }
}
