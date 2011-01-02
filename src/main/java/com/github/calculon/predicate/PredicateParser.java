package com.github.calculon.predicate;

import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

public class PredicateParser<TargetT> {

    private TargetT target;

    public PredicateParser(TargetT target) {
        this.target = target;
    }

    public boolean evaluateCondition(final String condition) {
        String[] words = WordUtils.capitalize(condition).split("\\s");
        if (words.length == 0) {
            throw new IllegalArgumentException("not a valid condition: " + condition);
        }

        words[0] = words[0].toLowerCase();

        // check whether the condition is a negation
        boolean negate = false;
        if (words.length > 1 && "not".equalsIgnoreCase(words[1]) || "no".equalsIgnoreCase(words[1])) {
            words = (String[]) ArrayUtils.remove(words, 1);
            negate = true;
        }

        String methodName = StringUtils.join(words);

        boolean result = false;
        try {
            Method method = target.getClass().getMethod(methodName, (Class<?>[]) null);
            result = (Boolean) method.invoke(target);
        } catch (Exception e) {
            throw new IllegalArgumentException("not a valid condition or method not accessible: '"
                    + condition + "' (translated to '" + methodName + "')");
        }

        return negate ? !result : result;
    }

}
