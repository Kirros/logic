package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents all Truth table of given formula
 */
public class TruthTable {
    private final Formula formula;
    private final List<AtomicFormula> atomicFormulas;

    public TruthTable(Formula formula) {
        this.formula = formula;

        List<Character> atomicFormulaNames = new ArrayList<>();
        atomicFormulaNames.addAll(formula.getAllAtomicFormulas());
        Collections.sort(atomicFormulaNames);

        this.atomicFormulas = atomicFormulaNames.stream().map(AtomicFormula::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (atomicFormulas.isEmpty())
            return "";

        StringBuilder result = new StringBuilder();

        for (AtomicFormula atomicFormula : atomicFormulas) {
            result.append(atomicFormula).append('\t');
        }

        result.append(formula.toString());

        return result.toString();
    }
}
