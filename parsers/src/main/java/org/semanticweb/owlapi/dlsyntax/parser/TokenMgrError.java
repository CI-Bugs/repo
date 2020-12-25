/* JavaCCOptions: */
/* This file is part of the OWL API.
 * The contents of this file are subject to the LGPL License, Version 3.0.
 * Copyright 2014, The University of Manchester
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0 in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License. */
/* Generated By:JavaCC: Do not edit this line. TokenMgrError.java Version 5.0 */
package org.semanticweb.owlapi.dlsyntax.parser;

import org.semanticweb.owlapi.model.OWLRuntimeException;

class TokenMgrError extends OWLRuntimeException {

    static final int LEXICAL_ERROR = 0;
    static final int STATIC_LEXER_ERROR = 1;
    static final int INVALID_LEXICAL_STATE = 2;
    static final int LOOP_DETECTED = 3;
    int errorCode;

    protected static String addEscapes(String str) {
        StringBuffer retval = new StringBuffer();
        char ch;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 0:
                    continue;
                case '\b':
                    retval.append("\\b");
                    continue;
                case '\t':
                    retval.append("\\t");
                    continue;
                case '\n':
                    retval.append("\\n");
                    continue;
                case '\f':
                    retval.append("\\f");
                    continue;
                case '\r':
                    retval.append("\\r");
                    continue;
                case '\"':
                    retval.append("\\\"");
                    continue;
                case '\'':
                    retval.append("\\\'");
                    continue;
                case '\\':
                    retval.append("\\\\");
                    continue;
                default:
                    if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
                        String s = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u"
                                + s.substring(s.length() - 4, s.length()));
                    } else {
                        retval.append(ch);
                    }
                    continue;
            }
        }
        return retval.toString();
    }

    protected static String LexicalError(boolean EOFSeen,
            @SuppressWarnings("unused") int lexState, int errorLine,
            int errorColumn, String errorAfter, char curChar) {
        return "Lexical error at line "
                + errorLine
                + ", column "
                + errorColumn
                + ".  Encountered: "
                + (EOFSeen ? "<EOF> " : '"'
                        + addEscapes(String.valueOf(curChar)) + '"' + " ("
                        + (int) curChar + "), ") + "after : \""
                + addEscapes(errorAfter) + '"';
    }

    TokenMgrError() {}

    TokenMgrError(String message, int reason) {
        super(message);
        errorCode = reason;
    }

    TokenMgrError(boolean EOFSeen, int lexState, int errorLine,
            int errorColumn, String errorAfter, char curChar, int reason) {
        this(LexicalError(EOFSeen, lexState, errorLine, errorColumn,
                errorAfter, curChar), reason);
    }
}
/*
 * JavaCC - OriginalChecksum=b558cc77ad54f52257c688b410f4fd44 (do not edit this
 * line)
 */