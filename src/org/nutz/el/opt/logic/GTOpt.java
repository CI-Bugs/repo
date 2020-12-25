package org.nutz.el.opt.logic;

/**
 * 大于
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class GTOpt extends AbstractCompareOpt {
    
    public int fetchPriority() {
        return 6;
    }

    public Object calculate() {
        return compare() > 0;
    }

    public String fetchSelf() {
        return ">";
    }

}
