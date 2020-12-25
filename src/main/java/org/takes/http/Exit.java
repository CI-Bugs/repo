/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.http;

/**
 * HTTP front exit.
 *
 * <p>All implementations of this interface must be immutable and thread-safe.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id: 28f5627a6d85fbde70ceae98986d1b32ed1532b9 $
 * @since 0.1
 */
public interface Exit {

    /**
     * Never exit.
     */
    Exit NEVER = new Exit() {
        @Override
        public boolean ready() {
            return false;
        }
    };

    /**
     * Ready to exit?
     * @return TRUE if Front should stop
     */
    boolean ready();

    /**
     * OR.
     * @since 0.28
     */
    final class Or implements Exit {
        /**
         * Left.
         */
        private final transient Exit left;
        /**
         * Right.
         */
        private final transient Exit right;
        /**
         * Ctor.
         * @param lft Left
         * @param rht Right
         */
        public Or(final Exit lft, final Exit rht) {
            this.left = lft;
            this.right = rht;
        }
        @Override
        public boolean ready() {
            return this.left.ready() || this.right.ready();
        }
    }

    /**
     * AND.
     * @since 0.28
     */
    final class And implements Exit {
        /**
         * Left.
         */
        private final transient Exit left;
        /**
         * Right.
         */
        private final transient Exit right;
        /**
         * Ctor.
         * @param lft Left
         * @param rht Right
         */
        public And(final Exit lft, final Exit rht) {
            this.left = lft;
            this.right = rht;
        }
        @Override
        public boolean ready() {
            return this.left.ready() && this.right.ready();
        }
    }

    /**
     * NOT.
     * @since 0.28
     */
    final class Not implements Exit {
        /**
         * Origin.
         */
        private final transient Exit origin;
        /**
         * Ctor.
         * @param exit Original
         */
        public Not(final Exit exit) {
            this.origin = exit;
        }
        @Override
        public boolean ready() {
            return !this.origin.ready();
        }
    }

}