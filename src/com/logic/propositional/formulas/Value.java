package com.logic.propositional.formulas;

/**
 * Represents Formula Value
 */
public enum Value {
    TRUE,
    FALSE;

    /**
     * Flips TRUE to FALSE and vice versa
     */
    public Value flip() {
        if ("TRUE".equals(this.name()))
            return FALSE;
        else if ("FALSE".equals(this.name()))
            return TRUE;
        else
            return this;
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
