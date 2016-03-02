package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;
import com.logic.ui.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents all Truth table of given formula
 */
public class TruthTable extends Table<Formula, Value> {

    public TruthTable(Formula formula) {
        List<AtomicFormula> atomicFormulas = formula.getAllAtomicFormulas();
        List<Formula> subformulas = formula.getAllSubformulas();

        List<Evaluation> evaluations = createAtomicValueColumns(atomicFormulas);

        for (Formula subformula : subformulas) {
            List<Value> valueColumn = evaluations.stream().map(subformula::evaluate).collect(Collectors.toList());
            addColumn(subformula, valueColumn);
        }

        List<Value> valueColumn = evaluations.stream().map(formula::evaluate).collect(Collectors.toList());
        addColumn(formula, valueColumn);
    }

    private List<Evaluation> createAtomicValueColumns(List<AtomicFormula> atomicFormulas) {
        int numberOfLines = new Double(Math.pow(2, atomicFormulas.size())).intValue();
        int alternation = numberOfLines;

        List<Evaluation> evaluations = new ArrayList<>();

        for (int i = 0; i < numberOfLines; i++) {
            evaluations.add(new Evaluation());
        }

        for (AtomicFormula atomicFormula : atomicFormulas) {
            alternation /= 2;
            Value currentValue = Value.TRUE;
            List<Value> values = new ArrayList<>();

            for (int i = 0; i < numberOfLines; i++) {
                if (i % alternation == 0)
                    currentValue = currentValue.flip();

                values.add(currentValue);
                evaluations.get(i).add(atomicFormula, currentValue);
            }

            addColumn(atomicFormula, values);
        }

        return evaluations;
    }
}
