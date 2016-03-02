package com.logic.propositional.formulas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents tuple of logical values
 */
public class ValueTuple {
    private final List<Value> values;

    private ValueTuple(List<Value> values) {
        this.values = values;
    }

    public Value get(int index) {
        return values.get(index);
    }

    @Override
    public boolean equals(Object otherTuple) {
        if (otherTuple instanceof ValueTuple) {
            List<Value> otherValueList = ((ValueTuple) otherTuple).values;
            return values.equals(otherValueList);
        }
        else
            return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        int factor = 1;

        for (Value value : values) {
            hash += factor * value.toInt();
            factor *= 2;
        }

        hash += factor;

        return hash;
    }

    @Override
    public String toString() {
        return String.join("\t", values.stream().map(Value::toString).collect(Collectors.toList()));
    }

    public static List<ValueTuple> getAllTuplesOfSize(int size) {
        List<ValueTuple> valueTuples = new ArrayList<>();
        int limit = size * size - 1;

        for (int i = 0; i < limit; i++) {
            String formatString = "%" + size + "s";
            String binaryString = String.format(formatString, Integer.toBinaryString(i)).replace(' ', '0');
            valueTuples.add(ValueTuple.fromBinaryString(binaryString));
        }

        return valueTuples;
    }

    public int size() {
        return values.size();
    }

    private static ValueTuple fromBinaryString(String binaryString) {
        List<Value> values = new ArrayList<>();

        for (int i = 0; i < binaryString.length(); i++) {
            values.add(Value.fromChar(binaryString.charAt(i)));
        }

        return new ValueTuple(values);
    }
}
