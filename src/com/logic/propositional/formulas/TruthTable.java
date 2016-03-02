package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents all Truth table of given formula
 */
public class TruthTable {
    private final Formula formula;
    private final List<AtomicFormula> atomicFormulas;
    private final Map<ValueTuple, Value> truthTable;

    public TruthTable(Formula formula) {
        this.formula = formula;

        this.atomicFormulas = formula.getAllAtomicFormulas();

        List<ValueTuple> valueTuples = ValueTuple.getAllTuplesOfSize(atomicFormulas.size());
        Map<ValueTuple, Value> truthTable = new LinkedHashMap<>();

        for (ValueTuple valueTuple : valueTuples) {
            truthTable.put(valueTuple, formula.evaluate(createValueMap(atomicFormulas, valueTuple)));
        }

        this.truthTable = truthTable;
    }

    private Map<AtomicFormula, Value> createValueMap(List<AtomicFormula> atomicFormulas, ValueTuple valueTuple) {
        assert atomicFormulas.size() == valueTuple.size();

        Map<AtomicFormula, Value> characterValueMap = new HashMap<>();

        for (int i = 0; i < atomicFormulas.size(); i++) {
            characterValueMap.put(atomicFormulas.get(i), valueTuple.get(i));
        }

        return characterValueMap;
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
        
        for (ValueTuple valueTuple : truthTable.keySet()) {
            result.append("\n").append(valueTuple.toString()).append('\t').append(truthTable.get(valueTuple));
        }

        return result.toString();
    }
}
