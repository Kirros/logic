package com.logic.propositional.formulas;

/**
 * Represents Formula Value
 */
public enum Value {
    TRUE,
    FALSE,
    UNASSIGNED;

    public static Value fromChar(char c) {
        if (c == '0')
            return FALSE;
        else if (c == '1')
            return TRUE;
        else
            throw new FormulaError("Unknown character to be translated to value " + c);
    }

    public int toInt() {
        switch (this.name()) {
            case "TRUE":
                return 1;
            case "FALSE":
                return 0;
            default:
                throw new FormulaError("Cannot translate UNASSIGNED to number");
        }
    }

    @Override
    public String toString() {
        switch (this.name()) {
            case "TRUE":
                return "1";
            case "FALSE":
                return "0";
            default:
                return " ";
        }
    }
}
