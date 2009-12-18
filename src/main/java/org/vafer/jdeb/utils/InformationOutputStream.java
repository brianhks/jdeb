/*
 * Copyright 2010 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vafer.jdeb.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;

/**
 * Convinience class to provide MD5 and length of a stream.
 * 
 * ATTENTION: don't use outside of jdeb
 *  
 * @author Torsten Curdt <tcurdt@vafer.org>
 */
public class InformationOutputStream extends DigestOutputStream {

    private final MessageDigest digest;
    private long size;
    
    public InformationOutputStream(OutputStream pStream, MessageDigest pDigest) {
        super(pStream, pDigest);
        digest = pDigest;
        size = 0;
    }
    
    public String getMd5() {
        return Utils.toHex(digest.digest());
    }
    
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        size += len;
    }

    public void write(int b) throws IOException {
        super.write(b);
        size++;
    }

    public long getSize() {
        return size;
    }
}